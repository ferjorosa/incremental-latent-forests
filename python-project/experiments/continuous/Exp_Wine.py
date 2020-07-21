from experiments.continuous import ContinuousExperiment
from spn.structure.StatisticalTypes import MetaType


class Exp_Wine(ContinuousExperiment.ContinuousExperiment):

    # 13 attributes after filtering with 10 folds
    meta_types = [MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL]
    var_types_string = "ccccccccccccc"

    def run(self, run: int, n_folds: int, fold_log: bool):
        print("\n------------------------------------------------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------ WINE ------------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------------------------------------------\n")

        super().run(run, n_folds, fold_log)


def main():
    run = 1
    n_folds = 10

    data_name = "wine"
    fold_log = True
    exp = Exp_Wine(data_name)
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()

