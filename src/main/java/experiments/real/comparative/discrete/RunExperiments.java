package experiments.real.comparative.discrete;

import eu.amidst.extension.learn.structure.BLFM_BinA;
import eu.amidst.extension.learn.structure.typelocalvbem.SimpleLocalVBEM;
import eu.amidst.extension.util.LogUtils;
import experiments.CrossValidationExperiment;
import methods.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RunExperiments {

    public static void main(String[] args) throws Exception {

        long seed = 0;
        int run = 1;

        /* Preparamos los metodos que se van a utilizar en los experimentos */
        Set<DiscreteMethod> methods = new LinkedHashSet<>();
        methods.add(new BinA(seed, BLFM_BinA.LinkageType.AVERAGE));
        methods.add(new LCM(seed));
        methods.add(new VariationalIncrementalLearner(seed, 10, false, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new VariationalIncrementalLearner(seed, 1, false, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new VariationalIncrementalLearnerMax(seed, false, true, true, new SimpleLocalVBEM()));

        /* Definimos los experimentos que se van a ejecutar */
        List<CrossValidationExperiment> experimentList = new ArrayList<>();

        experimentList.add(new Exp_Alarm(methods));
        experimentList.add(new Exp_Balance_scale(methods));
        experimentList.add(new Exp_Breast_cancer(methods));
        experimentList.add(new Exp_Car_evaluation(methods));
        experimentList.add(new Exp_Coil_42(methods));
        experimentList.add(new Exp_Hayes_roth(methods));
        experimentList.add(new Exp_Hiv_test(methods));
        experimentList.add(new Exp_Hayes_roth(methods));
        experimentList.add(new Exp_House_building(methods));
        experimentList.add(new Exp_Mushroom(methods));
        experimentList.add(new Exp_News_100(methods));
        experimentList.add(new Exp_Nursery(methods));
        experimentList.add(new Exp_News_100(methods));
        experimentList.add(new Exp_Pascal_Voc_2007(methods));
        experimentList.add(new Exp_Spect_heart(methods));
        experimentList.add(new Exp_Webkb_336(methods));

        /* Ejecutamos los experimentos */
        for(CrossValidationExperiment experiment: experimentList)
            experiment.runCrossValExperiment(seed, 10, run, LogUtils.LogLevel.INFO);

    }
}
