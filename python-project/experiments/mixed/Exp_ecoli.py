from experiments.mixed import MixedExperiment
from spn.structure.StatisticalTypes import MetaType


class Exp_ecoli(MixedExperiment.MixedExperiment):

    # 7 attributes after filtering with 10 folds
    meta_types = [MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.DISCRETE]
    var_types_string = "ccccccu"

    def run(self, run: int, n_folds: int, fold_log: bool):
        print("\n------------------------------------------------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------ ECOLI -----------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------------------------------------------\n")

        super().run(run, n_folds, fold_log)


def main():
    run = 1
    n_folds = 10

    data_name = "ecoli"
    fold_log = True
    exp = Exp_ecoli(data_name)
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()

