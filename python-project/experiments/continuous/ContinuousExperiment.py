from scipy.io import arff
from spn.structure.Base import Context
import pandas as pd
import numpy as np
from methods import MSPN, KDE


class ContinuousExperiment:

    def __init__(self, data_name):
        self.data_name = data_name

    def run(self, run: int, n_folds: int, meta_types, var_types_string, fold_log: bool):
        base_path = "../../data/continuous/" + self.data_name + "/10_folds/"
        train_datasets = []
        test_datasets = []
        train_no_missing_datasets = []
        ds_contexts = []

        # Prepare folds' data
        for i in range(1, 11):
            train_data_path = base_path + self.data_name + "_" + str(i) + "_train.arff"
            test_data_path = base_path + self.data_name + "_" + str(i) + "_test.arff"

            # Load data
            train_data = arff.loadarff(train_data_path)
            train_data = pd.DataFrame(train_data[0])
            train_data = train_data.replace(b'?', np.NaN)
            train_datasets.append(train_data)

            test_data = arff.loadarff(test_data_path)
            test_data = pd.DataFrame(test_data[0])
            test_datasets.append(test_data)

            # Create context for MSPN algorithm
            ds_context = Context(meta_types)
            ds_contexts.append(ds_context)

        # Apply MSPN
        results_path = "../../results/run_" + str(run) + "/continuous/" + self.data_name + "/" + str(n_folds) + "_folds/MSPN/"
        MSPN.apply(train_datasets, ds_contexts, test_datasets, n_folds, results_path, self.data_name, fold_log)

        # Apply KDE
        results_path = "../../results/run_" + str(run) + "/continuous/" + self.data_name + "/" + str(n_folds) + "_folds/KDE/"
        KDE.apply(train_datasets, var_types_string, test_datasets, n_folds, results_path, self.data_name, fold_log)
