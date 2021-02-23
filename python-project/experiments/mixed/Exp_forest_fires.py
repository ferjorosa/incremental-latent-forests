from experiments.mixed import MixedExperiment
from spn.structure.StatisticalTypes import MetaType


class Exp_forest_fires(MixedExperiment.MixedExperiment):

    # 13 attributes after filtering with 10 folds
    meta_types = [MetaType.REAL, MetaType.REAL, MetaType.DISCRETE, MetaType.DISCRETE, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL]
    var_types_string = "ccuuccccccccc"

    def run(self, run: int, n_folds: int, fold_log: bool):
        print("\n------------------------------------------------------------------")
        print("------------------------------------------------------------------")
        print("--------------------------- FOREST_FIRES -------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------------------------------------------\n")

        super().run(run, n_folds, fold_log)


def main():
    run = 1
    n_folds = 10

    data_name = "forest_fires"
    fold_log = True
    exp = Exp_forest_fires(data_name)
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()

