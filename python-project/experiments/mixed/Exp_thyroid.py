from experiments.mixed import MixedExperiment
from spn.structure.StatisticalTypes import MetaType


class Exp_thyroid(MixedExperiment.MixedExperiment):

    # 21 attributes after filtering with 10 folds
    meta_types = [MetaType.REAL, MetaType.DISCRETE, MetaType.DISCRETE, MetaType.DISCRETE, MetaType.DISCRETE,
                  MetaType.DISCRETE, MetaType.DISCRETE, MetaType.DISCRETE, MetaType.DISCRETE, MetaType.DISCRETE,
                  MetaType.DISCRETE, MetaType.DISCRETE, MetaType.DISCRETE, MetaType.DISCRETE, MetaType.DISCRETE,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.DISCRETE,
                  MetaType.DISCRETE]
    var_types_string = "cuuuuuuuuuuuuuuccccuu"

    def run(self, run: int, n_folds: int, fold_log: bool):
        print("\n------------------------------------------------------------------")
        print("------------------------------------------------------------------")
        print("----------------------------- THYROID ----------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------------------------------------------\n")

        super().run(run, n_folds, fold_log)


def main():
    run = 1
    n_folds = 10

    data_name = "thyroid"
    fold_log = True
    exp = Exp_thyroid(data_name)
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()

