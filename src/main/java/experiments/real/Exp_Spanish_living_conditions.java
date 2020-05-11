package experiments.real;

import eu.amidst.core.datastream.DataInstance;
import eu.amidst.core.datastream.DataOnMemory;
import eu.amidst.core.io.DataStreamLoader;
import eu.amidst.extension.data.DataUtils;
import eu.amidst.extension.learn.structure.typelocalvbem.SimpleLocalVBEM;
import eu.amidst.extension.util.LogUtils;
import eu.amidst.extension.util.PriorsFromData;
import experiments.CrossValidationExperiment;
import experiments.MixedDataExperiment;
import experiments.util.Kfold;
import methods.HybridMethod;
import methods.VariationalIncrementalLearner;
import methods.VariationalIncrementalLearnerMax;
import methods.VariationalLCM;
import voltric.util.Tuple;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exp_Spanish_living_conditions extends MixedDataExperiment implements CrossValidationExperiment {

    public Exp_Spanish_living_conditions(Set<HybridMethod> methods) { super(methods); }

    public static void main(String[] args) throws Exception {
        long seed = 0;
        int kFolds = 10;
        int run = 1;
        LogUtils.LogLevel logLevel = LogUtils.LogLevel.INFO;

        Set<HybridMethod> methods = new LinkedHashSet<>();
        methods.add(new VariationalLCM(seed));
        methods.add(new VariationalIncrementalLearner(seed, 1, true, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new VariationalIncrementalLearner(seed, 10, true, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new VariationalIncrementalLearnerMax(seed, false, true, true, new SimpleLocalVBEM()));

        Exp_Spanish_living_conditions experiment = new Exp_Spanish_living_conditions(methods);
        experiment.runCrossValExperiment(seed, kFolds, run, logLevel);
    }

    @Override
    public void runCrossValExperiment(long seed, int kFolds, int run, LogUtils.LogLevel foldLogLevel) throws Exception {
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("-------------------- SPANISH_LIVING_CONDITIONS -------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");

        String dataName = "spanish_living_conditions";
        String filename = "data/mixed/spanish_living_conditions/spanish_living_conditions.arff";
        DataOnMemory<DataInstance> data = DataStreamLoader.open(filename).toDataOnMemory();
        DataUtils.defineAttributesMaxMinValues(data);

        /* Generate data folds */
        String foldsPath = "data/mixed/spanish_living_conditions/" + kFolds + "_folds/";
        Files.createDirectories(Paths.get(foldsPath));
        List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> folds = Kfold.generateAndExport(data, kFolds, dataName, foldsPath);
        System.out.println(kFolds + " folds have been generated");

        /* By default, generate priors from data using the empirical Bayes method */
        final Map<String, double[]> priors = PriorsFromData.generate(data, 1);

        /* Establish the prior parameters of variables using the ECH data
            - urban_degree ()
            - home_rooms ()
            - family members ()

            Prior parameters have been obtained in the python file "generate_priors_from_ech.ipynb"
         */
        double[] urban_degree_prior_params = {0.53, 0.27, 0.15, 0.05};
        double[] family_members_prior_params = {2.52, 1, 0.5, 0.63};
        double[] home_rooms_prior_params = {5.44, 1, 0.5, 0.68};

        priors.put("urban_degree", urban_degree_prior_params);
        priors.put("family_members", family_members_prior_params);
        priors.put("home_rooms", home_rooms_prior_params);

        /* Run methods */
        for (HybridMethod method : methods)
            method.runHybrid(folds, "spanish_living_conditions", run, LogUtils.LogLevel.INFO);
    }
}
