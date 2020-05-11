package experiments.real.comparative.continuous;

import eu.amidst.core.datastream.Attribute;
import eu.amidst.core.datastream.DataInstance;
import eu.amidst.core.datastream.DataOnMemory;
import eu.amidst.core.io.DataStreamLoader;
import eu.amidst.extension.data.DataUtils;
import eu.amidst.extension.learn.structure.typelocalvbem.SimpleLocalVBEM;
import eu.amidst.extension.util.LogUtils;
import eu.amidst.extension.util.PriorsFromData;
import experiments.ContinuousDataExperiment;
import experiments.CrossValidationExperiment;
import experiments.util.Kfold;
import methods.*;
import voltric.util.Tuple;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Exp_Glass extends ContinuousDataExperiment implements CrossValidationExperiment {

    public Exp_Glass(Set<ContinuousMethod> methods) {
        super(methods);
    }

    public static void main(String[] args) throws Exception {

        long seed = 0;
        int kFolds = 10;
        int run = 1;
        LogUtils.LogLevel logLevel = LogUtils.LogLevel.INFO;

        Set<ContinuousMethod> methods = new LinkedHashSet<>();
        methods.add(new GaussianLCM(seed));
        methods.add(new VariationalIncrementalLearner(seed, 1, true, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new VariationalIncrementalLearner(seed, 10, true, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new GS(seed));
        methods.add(new VariationalIncrementalLearnerMax(seed, false, true, true, new SimpleLocalVBEM()));
        methods.add(new GEAST("geast_settings.xml", seed));

        Exp_Glass exp = new Exp_Glass(methods);
        exp.runCrossValExperiment(seed, kFolds, run, logLevel);
    }

    @Override
    public void runCrossValExperiment(long seed, int kFolds, int run, LogUtils.LogLevel foldLogLevel) throws Exception {

        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("----------------------------- GLASS ------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");

        /* Generate data folds */
        String foldsPath = "data/continuous/glass/" + kFolds + "_folds/";
        Files.createDirectories(Paths.get(foldsPath));

        String filename = "data/continuous/glass/glass.arff";
        String dataName = "glass";

        DataOnMemory<DataInstance> data = DataStreamLoader.open(filename).toDataOnMemory();
        // Remove the class attribute
        List<Attribute> filteredAttributes = data.getAttributes().getFullListOfAttributes().stream()
                .filter(x->!x.getName().equals("Type"))
                .collect(Collectors.toList());
        data = DataUtils.project(data, filteredAttributes);
        List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> folds = Kfold.generateAndExport(data, kFolds, dataName, foldsPath);
        System.out.println(kFolds + " folds have been generated");

        /* Filter Bayesian methods and assign them the empirical Bayes priors */
        final Map<String, double[]> priors = PriorsFromData.generate(data, 1);
        methods.stream()
                .filter(x -> x instanceof BayesianMethod)
                .forEach(x -> ((BayesianMethod) x).setPriors(priors));

        /* Run methods */
        for (ContinuousMethod method : methods)
            method.runContinuous(folds, "glass", run, LogUtils.LogLevel.INFO);
    }
}
