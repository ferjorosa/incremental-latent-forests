from spn.structure.StatisticalTypes import MetaType
from scipy.io import arff
from spn.structure.Base import Context
import pandas as pd
from methods import MSPN, KDE


class Exp_Spanish_living_conditions:

    # 4 attributes after filtering with 10 folds
    meta_types = [MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL]
    var_types_string = "cccc"
    data_name = "spanish_living_conditions"

    def run(self, n_folds: int, fold_log: bool):
        print("\n------------------------------------------------------------------")
        print("------------------------------------------------------------------")
        print("-------------------- SPANISH_LIVING_CONDITIONS -------------------")
        print("------------------------------------------------------------------")
        print("------------------------------------------------------------------\n")

        base_path = "../../../data/mixed/" + self.data_name + "/10_folds/"
        train_datasets = []
        test_datasets = []
        ds_contexts = []

        # Prepare folds' data
        for i in range(1, 11):
            train_data_path = base_path + self.data_name + "_" + str(i) + "_train.arff"
            test_data_path = base_path + self.data_name + "_" + str(i) + "_test.arff"

            # Load data
            train_data = arff.loadarff(train_data_path)
            train_data = pd.DataFrame(train_data[0])
            train_data = train_data.values
            train_datasets.append(train_data)

            test_data = arff.loadarff(test_data_path)
            test_data = pd.DataFrame(test_data[0])
            test_data = test_data.values
            test_datasets.append(test_data)

            # Create context for MSPN algorithm
            ds_context = Context(self.meta_types)
            ds_contexts.append(ds_context)

        # Apply MSPN
        results_path = "../../../results/"+ self.data_name + "/" + str(
            n_folds) + "_folds/MSPN/"
        MSPN.apply(train_datasets, ds_contexts, test_datasets, n_folds, results_path, self.data_name, fold_log)

        # Apply KDE
        results_path = "../../../results/run_" + str(run) + "/continuous/" + self.data_name + "/" + str(
            n_folds) + "_folds/KDE/"
        KDE.apply(train_datasets, self.var_types_string, test_datasets, n_folds, results_path, self.data_name, fold_log)


def main():
    run = 1
    n_folds = 10

    data_name = "spanish_living_conditions"
    fold_log = True
    exp = Exp_Spanish_living_conditions(data_name)
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()

