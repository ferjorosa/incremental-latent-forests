package experiments.real.comparative.discrete;

import eu.amidst.core.datastream.DataInstance;
import eu.amidst.core.datastream.DataOnMemory;
import eu.amidst.core.io.DataStreamLoader;
import eu.amidst.extension.learn.structure.BLFM_BinA;
import eu.amidst.extension.learn.structure.typelocalvbem.SimpleLocalVBEM;
import eu.amidst.extension.util.LogUtils;
import eu.amidst.extension.util.PriorsFromData;
import experiments.CrossValidationExperiment;
import experiments.DiscreteDataExperiment;
import experiments.util.Kfold;
import methods.*;
import voltric.learning.structure.incremental.localemtype.SimpleLocalEM;
import voltric.util.Tuple;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exp_Coil_42 extends DiscreteDataExperiment implements CrossValidationExperiment {

    public Exp_Coil_42(Set<DiscreteMethod> methods) {
        super(methods);
    }

    public static void main(String[] args) throws Exception {

        long seed = 0;
        int kFolds = 10;
        int run = 1;
        LogUtils.LogLevel logLevel = LogUtils.LogLevel.INFO;

        Set<DiscreteMethod> methods = new LinkedHashSet<>();
        methods.add(new BinA(seed, BLFM_BinA.LinkageType.AVERAGE));
        methods.add(new LCM(seed));
        methods.add(new VariationalIncrementalLearner(seed, 10, false, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new VariationalIncrementalLearner(seed, 1, false, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new VariationalIncrementalLearnerMax(seed, false, true, true, new SimpleLocalVBEM()));

        Exp_Coil_42 exp = new Exp_Coil_42(methods);
        exp.runCrossValExperiment(seed, kFolds, run, logLevel);
    }

    @Override
    public void runCrossValExperiment(long seed, int kFolds, int run, LogUtils.LogLevel foldLogLevel) throws Exception {

        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("---------------------------- COIL_42 -----------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");

        /* Generate data folds */
        String foldsPath = "data/discrete/coil_42/"+kFolds+"_folds/";
        Files.createDirectories(Paths.get(foldsPath));

        String filename = "data/discrete/coil_42/coil_42.arff";
        String dataName = "coil_42";

        DataOnMemory<DataInstance> data = DataStreamLoader.open(filename).toDataOnMemory();
        List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> folds = Kfold.generateAndExport(data, kFolds, dataName, foldsPath);
        System.out.println(kFolds + " folds have been generated");

        /* Filter Bayesian methods and assign them the empirical Bayes priors */
        final Map<String, double[]> priors = PriorsFromData.generate(data, 1);
        methods.stream()
                .filter(x -> x instanceof BayesianMethod)
                .forEach(x-> ((BayesianMethod) x).setPriors(priors));

        /* Run methods */
        for(DiscreteMethod method: methods)
            method.runDiscrete(folds, "coil_42", run, LogUtils.LogLevel.INFO);
    }
}