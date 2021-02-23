package experiments.real.comparative.mixed;

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
        Set<HybridMethod> methods = new LinkedHashSet<>();
        methods.add(new VariationalLCM(seed));
        methods.add(new ConstrainedIncrementalLearner(seed, 1, true, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new ConstrainedIncrementalLearner(seed, 10, true, true, true, 3, false, false, new SimpleLocalVBEM()));
        methods.add(new IncrementalLearner(seed, false, true, true, new SimpleLocalVBEM()));

        /* Definimos los experimentos que se van a ejecutar */
        List<CrossValidationExperiment> experimentList = new ArrayList<>();

        //experimentList.add(new Exp_arrythmia(methods));
        experimentList.add(new Exp_autos(methods));
        experimentList.add(new Exp_blood_transfusion(methods));
        experimentList.add(new Exp_breast_cancer_coimbra(methods));
        experimentList.add(new Exp_ecoli(methods));
        experimentList.add(new Exp_forest_fires(methods));
        experimentList.add(new Exp_haberman(methods));
        //experimentList.add(new Exp_musk(methods));
        experimentList.add(new Exp_haberman(methods));
        experimentList.add(new Exp_parkinsons(methods));
        experimentList.add(new Exp_planning_relax(methods));
        //experimentList.add(new Exp_qsar_biodeg(methods));
        experimentList.add(new Exp_segment(methods));
        experimentList.add(new Exp_thoracic_surgery(methods));
        //experimentList.add(new Exp_thyroid(methods));
        experimentList.add(new Exp_user_knowledge(methods));

        /* Ejecutamos los experimentos */
        for(CrossValidationExperiment experiment: experimentList)
            experiment.runCrossValExperiment(seed, 10, run, LogUtils.LogLevel.INFO);

    }
}
