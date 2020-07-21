from experiments.continuous import ContinuousExperiment


class Exp_Iris(ContinuousExperiment.ContinuousExperiment):
    def run(self, missing_percentage: float, run: int, n_folds: int, n_restarts: int,
            fold_log: bool):
        print("\n------------------------------------------------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------ IRIS ------------------------------")
        print("------------------------------------------------------------------")
        print("------------------------------------------------------------------\n")

        super().run(missing_percentage, run, n_folds, n_restarts, fold_log)


def main():
    run = 1
    n_folds = 10
    missing_percentage = 0.2
    n_restarts = 64
    data_name = "iris"
    fold_log = False
    exp = Exp_Iris(data_name)
    exp.run(missing_percentage, run, n_folds, n_restarts, fold_log)


if __name__ == "__main__":
    main()

