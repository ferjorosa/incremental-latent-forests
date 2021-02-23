package experiments.real.comparative.mixed;

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
import methods.*;
import voltric.util.Tuple;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exp_haberman extends MixedDataExperiment implements CrossValidationExperiment {

    public Exp_haberman(Set<HybridMethod> methods) { super(methods); }

    public static void main(String[] args) throws Exception {
        long seed = 0;
        int kFolds = 10;
        int run = 1;
        LogUtils.LogLevel logLevel = LogUtils.LogLevel.INFO;

        Set<HybridMethod> methods = new LinkedHashSet<>();
        methods.add(new VariationalLCM(seed));
        methods.add(new ConstrainedIncrementalLearner(seed, 1, true, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new ConstrainedIncrementalLearner(seed, 10, true, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new IncrementalLearner(seed, false, true, true, new SimpleLocalVBEM()));

        Exp_haberman experiment = new Exp_haberman(methods);
        experiment.runCrossValExperiment(seed, kFolds, run, logLevel);
    }

    @Override
    public void runCrossValExperiment(long seed, int kFolds, int run, LogUtils.LogLevel foldLogLevel) throws Exception {
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("---------------------------- HABERMAN ----------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");

        String dataName = "haberman";
        String filename = "data/mixed/"+dataName+"/"+dataName+".arff";
        DataOnMemory<DataInstance> data = DataStreamLoader.open(filename).toDataOnMemory();
        DataUtils.defineAttributesMaxMinValues(data);

        /* Generate data folds */
        String foldsPath = "data/mixed/"+dataName+"/" + kFolds + "_folds/";
        Files.createDirectories(Paths.get(foldsPath));
        List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> folds = Kfold.generateAndExport(data, kFolds, dataName, foldsPath);
        System.out.println(kFolds + " folds have been generated");

        /* Filter Bayesian methods and assign them the empirical Bayes priors */
        final Map<String, double[]> priors = PriorsFromData.generate(data, 1);
        this.methods.stream()
                .filter(x -> x instanceof BayesianMethod)
                .forEach(x -> ((BayesianMethod) x).setPriors(priors));

        /* Run methods */
        for (HybridMethod method : methods)
            method.runHybrid(folds, dataName, run, LogUtils.LogLevel.INFO);
    }
}
