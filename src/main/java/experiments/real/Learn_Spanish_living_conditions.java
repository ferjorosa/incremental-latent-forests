package experiments.real;

import eu.amidst.core.datastream.DataInstance;
import eu.amidst.core.datastream.DataOnMemory;
import eu.amidst.core.io.DataStreamLoader;
import eu.amidst.core.models.BayesianNetwork;
import eu.amidst.extension.data.DataUtils;
import eu.amidst.extension.io.GenieWriter;
import eu.amidst.extension.learn.structure.typelocalvbem.SimpleLocalVBEM;
import eu.amidst.extension.util.LogUtils;
import eu.amidst.extension.util.PriorsFromData;
import eu.amidst.extension.util.Tuple3;
import methods.ConstrainedIncrementalLearner;
import methods.IncrementalLearner;
import methods.VariationalLCM;

import java.io.File;
import java.util.Map;

/* The purpose of this script is to learn the models of LCM, CIL and IL for their analysis with Genie. In this case we dont
 * use cross-validation because this is not a comparative study, we simply want the learned models using all the data
 * */
public class Learn_Spanish_living_conditions {

    public static void main(String[] args) throws Exception {
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("-------------------- SPANISH_LIVING_CONDITIONS -------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");

        String dataName = "spanish_living_conditions";
        String filename = "data/mixed/spanish_living_conditions/spanish_living_conditions.arff";
        DataOnMemory<DataInstance> data = DataStreamLoader.open(filename).toDataOnMemory();
        DataUtils.defineAttributesMaxMinValues(data);

        long seed = 0;

        /* By default, generate priors from data using the empirical Bayes method */
        final Map<String, double[]> priors = PriorsFromData.generate(data, 1);

        /* Establish the prior parameters of variables using the ECH data
            - home_ownership
            - home_rooms
            - family_members

            Prior parameters have been obtained in the python file "generate_priors_from_ech.ipynb"
         */
        double[] home_ownership_prior_params = {0.53, 0.27, 0.15, 0.05};
        double[] family_members_prior_params = {2.52, 1, 0.5, 0.63};
        double[] home_rooms_prior_params = {5.44, 1, 0.5, 0.68};

        priors.put("home_ownership", home_ownership_prior_params);
        priors.put("family_members", family_members_prior_params);
        priors.put("home_rooms", home_rooms_prior_params);


        /* LCM */
        Tuple3<BayesianNetwork, Double, Long> resultLCM = VariationalLCM.learnModel(data, seed, priors,
                LogUtils.LogLevel.NONE, true);
        exportGenieModel(resultLCM.getFirst(), "results/spanish_living_conditions", "spanish_living_conditions", "LCM");

        /* Incremental learner */
        Tuple3<BayesianNetwork, Double, Long> resultIL = IncrementalLearner.learnModel(data, priors, seed,
                true, true, true, new SimpleLocalVBEM(),
                LogUtils.LogLevel.NONE, true);
        exportGenieModel(resultIL.getFirst(), "results/spanish_living_conditions", "spanish_living_conditions", "IL");

        /* Constrained incremental learner (alpha = 1)*/
        Tuple3<BayesianNetwork, Double, Long> resultCIL_1 = ConstrainedIncrementalLearner.learnModel(data, priors, seed,
                1, true, true, true, 3,
                false, false, new SimpleLocalVBEM(), LogUtils.LogLevel.NONE, true);
        exportGenieModel(resultCIL_1.getFirst(), "results/spanish_living_conditions", "spanish_living_conditions", "CIL_1");

        /* Constrained incremental learner (alpha = 10)*/
        Tuple3<BayesianNetwork, Double, Long> resultCIL_10 = ConstrainedIncrementalLearner.learnModel(data, priors, seed,
                10, true, true, true, 3,
                false, false, new SimpleLocalVBEM(), LogUtils.LogLevel.NONE, true);
        exportGenieModel(resultCIL_10.getFirst(), "results/spanish_living_conditions", "spanish_living_conditions", "CIL_10");
    }

    private static void exportGenieModel(BayesianNetwork bayesianNetwork,
                                         String directoryPath,
                                         String dataName,
                                         String methodName) throws Exception {

        new File(directoryPath).mkdirs();

        GenieWriter genieWriter = new GenieWriter();
        genieWriter.write(bayesianNetwork, new File(directoryPath + "/" + dataName + "_" +methodName + ".xdsl"));
    }
}
