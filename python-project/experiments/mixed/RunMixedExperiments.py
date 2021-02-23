from experiments.mixed import Exp_arrythmia
from experiments.mixed import Exp_autos
from experiments.mixed import Exp_blood_transfusion
from experiments.mixed import Exp_breast_cancer_coimbra
from experiments.mixed import Exp_ecoli
from experiments.mixed import Exp_forest_fires
from experiments.mixed import Exp_haberman
from experiments.mixed import Exp_musk
from experiments.mixed import Exp_parkinsons
from experiments.mixed import Exp_planning_relax
from experiments.mixed import Exp_qsar_biodeg
from experiments.mixed import Exp_segment
from experiments.mixed import Exp_thoracic_surgery
from experiments.mixed import Exp_thyroid
from experiments.mixed import Exp_user_knowledge


def main():
    run = 1
    n_folds = 10
    fold_log = True

    exp = Exp_arrythmia.Exp_arrythmia("arryhtmia")
    exp.run(run, n_folds, fold_log)

    exp = Exp_autos.Exp_autos("autos")
    exp.run(run, n_folds, fold_log)

    exp = Exp_blood_transfusion.Exp_blood_transfusion("blood_transfusion")
    exp.run(run, n_folds, fold_log)

    exp = Exp_breast_cancer_coimbra.Exp_breast_cancer_coimbra("breast_cancer_coimbra")
    exp.run(run, n_folds, fold_log)

    exp = Exp_ecoli.Exp_ecoli("ecoli")
    exp.run(run, n_folds, fold_log)

    exp = Exp_forest_fires.Exp_forest_fires("forest_fires")
    exp.run(run, n_folds, fold_log)

    exp = Exp_haberman.Exp_haberman("haberman")
    exp.run(run, n_folds, fold_log)

    exp = Exp_musk.Exp_musk("musk")
    exp.run(run, n_folds, fold_log)

    exp = Exp_parkinsons.Exp_parkinsons("parkinsons")
    exp.run(run, n_folds, fold_log)

    exp = Exp_planning_relax.Exp_planning_relax("planning_relax")
    exp.run(run, n_folds, fold_log)

    exp = Exp_qsar_biodeg.Exp_qsar_biodeg("qsar_biodeg")
    exp.run(run, n_folds, fold_log)

    exp = Exp_segment.Exp_segment("segment")
    exp.run(run, n_folds, fold_log)

    exp = Exp_thoracic_surgery.Exp_thoracic_surgery("thoracic_surgery")
    exp.run(run, n_folds, fold_log)

    exp = Exp_thyroid.Exp_thyroid("thyroid")
    exp.run(run, n_folds, fold_log)

    exp = Exp_user_knowledge.Exp_user_knowledge("user_knowledge")
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()