from experiments.discrete import DiscreteExperiment
from spn.structure.StatisticalTypes import MetaType


class Exp_Hiv_test(DiscreteExperiment.DiscreteExperiment):

    # 4 attributes after filtering with 10 folds
    meta_types = [MetaType.DISCRETE, MetaType.DISCRETE, MetaType.DISCRETE, MetaType.DISCRETE]
    var_types_string = "uuuu"

    def run(self, run: int, n_folds: int, fold_log: bool):
        print("\n------------------------------------------------------------------")
        print("------------------------------------------------------------------")
        print("---------------------------- HIV_TEST ----------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------------------------------------------\n")

        super().run(run, n_folds, fold_log)


def main():
    run = 1
    n_folds = 10

    data_name = "hiv_test"
    fold_log = True
    exp = Exp_Hiv_test(data_name)
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()

