package experiments.real.comparative.continuous;

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
        Set<ContinuousMethod> methods = new LinkedHashSet<>();
        methods.add(new GaussianLCM(seed));
        methods.add(new ConstrainedIncrementalLearner(seed, 1, true, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new ConstrainedIncrementalLearner(seed, 10, true, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new GS(seed));
        methods.add(new IncrementalLearner(seed, false, true, true, new SimpleLocalVBEM()));
        methods.add(new GEAST("geast_settings.xml", seed));

        /* Definimos los experimentos que se van a ejecutar */
        List<CrossValidationExperiment> experimentList = new ArrayList<>();

        experimentList.add(new Exp_100_plants(methods));
        experimentList.add(new Exp_Alcohol(methods));
        experimentList.add(new Exp_Buddymove(methods));
        experimentList.add(new Exp_Geo_music(methods));
        experimentList.add(new Exp_Glass(methods));
        experimentList.add(new Exp_Ilpd(methods));
        experimentList.add(new Exp_Ionosphere(methods));
        experimentList.add(new Exp_Iris(methods));
        experimentList.add(new Exp_Leaf(methods));
        experimentList.add(new Exp_Nba(methods));
        experimentList.add(new Exp_Vehicle(methods));
        experimentList.add(new Exp_Waveform(methods));
        experimentList.add(new Exp_Wdbc(methods));
        experimentList.add(new Exp_Wine(methods));
        experimentList.add(new Exp_Yeast(methods));

        /* Ejecutamos los experimentos */
        for(CrossValidationExperiment experiment: experimentList)
            experiment.runCrossValExperiment(seed, 10, run, LogUtils.LogLevel.INFO);

    }
}
