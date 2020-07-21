from experiments.continuous import ContinuousExperiment
from spn.structure.StatisticalTypes import MetaType


class Exp_Waveform(ContinuousExperiment.ContinuousExperiment):

    # 40 attributes after filtering
    meta_types = [MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL,
                  MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL]
    var_types_string = "cccccccccccccccccccccccccccccccccccccccc"

    def run(self, run: int, n_folds: int, fold_log: bool):
        print("\n------------------------------------------------------------------")
        print("------------------------------------------------------------------")
        print("---------------------------- WAVEFORM ----------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------------------------------------------\n")

        super().run(run, n_folds, fold_log)


def main():
    run = 1
    n_folds = 10

    data_name = "waveform"
    fold_log = True
    exp = Exp_Waveform(data_name)
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()

