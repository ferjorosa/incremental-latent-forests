from experiments.mixed import MixedExperiment
from spn.structure.StatisticalTypes import MetaType


class Exp_user_knowledge(MixedExperiment.MixedExperiment):

    # 5 attributes after filtering with 10 folds
    meta_types = [MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.DISCRETE]
    var_types_string = "cccccu"

    def run(self, run: int, n_folds: int, fold_log: bool):
        print("\n------------------------------------------------------------------")
        print("------------------------------------------------------------------")
        print("-------------------------- USER_KNOWLEDGE ------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------------------------------------------\n")

        super().run(run, n_folds, fold_log)


def main():
    run = 1
    n_folds = 10

    data_name = "user_knowledge"
    fold_log = True
    exp = Exp_user_knowledge(data_name)
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()

