from experiments.discrete import Exp_Balance_scale
from experiments.discrete import Exp_Alarm
from experiments.discrete import Exp_Vote
from experiments.discrete import Exp_Breast_cancer
from experiments.discrete import Exp_Car_evaluation
from experiments.discrete import Exp_Coil_42
from experiments.discrete import Exp_Hayes_roth
from experiments.discrete import Exp_Hiv_test
from experiments.discrete import Exp_House_building
from experiments.discrete import Exp_Mushroom
from experiments.discrete import Exp_News_100
from experiments.discrete import Exp_Nursery
from experiments.discrete import Exp_Pascal_voc_2007
from experiments.discrete import Exp_Spect_heart
from experiments.discrete import Exp_Webkb_336

def main():
    run = 1
    n_folds = 10
    fold_log = True

    exp = Exp_Hiv_test.Exp_Hiv_test("hiv_test")
    exp.run(run, n_folds, fold_log)

    exp = Exp_House_building.Exp_House_building("house_building")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Hayes_roth.Exp_Hayes_roth("hayes_roth")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Balance_scale.Exp_Balance_scale("balance_scale")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Car_evaluation.Exp_Car_evaluation("car_evaluation")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Nursery.Exp_Nursery("nursery")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Breast_cancer.Exp_Breast_cancer("breast_cancer")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Vote.Exp_Vote("vote")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Mushroom.Exp_Mushroom("mushroom")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Pascal_voc_2007.Exp_Pascal_voc_2007("pascal_voc_2007")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Spect_heart.Exp_Spect_heart("spect_heart")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Alarm.Exp_Alarm("alarm")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Coil_42.Exp_Coil_42("coil_42")
    exp.run(run, n_folds, fold_log)

    exp = Exp_News_100.Exp_News_100("news_100")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Webkb_336.Exp_Webkb_336("webkb_336")
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()