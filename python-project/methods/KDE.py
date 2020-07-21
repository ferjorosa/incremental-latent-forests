import statsmodels.api as sm
import numpy as np
import os
import time
import json


def apply(train_datasets, var_types_string, test_datasets, n_folds, result_path, filename, foldLog):

    print("\n========================")
    print("KDE")
    print("========================")

    results = {}
    folds = {}
    avg_learning_time = 0
    avg_test_ll = 0
    for i in range(1, n_folds + 1):
        index = i-1
        init_time = time.time()*1000
        model = sm.nonparametric.KDEMultivariate(data=train_datasets[index], var_type=var_types_string, bw='normal_reference')
        end_time = time.time()*1000

        learning_time = end_time - init_time
        test_ll = np.log(model.pdf(test_datasets[index]))
        test_ll = np.sum(test_ll)

        fold_result = {"test_ll": test_ll, "learning_time": learning_time}

        folds["fold_" + str(i)] = fold_result
        avg_learning_time = avg_learning_time + learning_time
        avg_test_ll = avg_test_ll + test_ll

        if foldLog:
            print("----------------------------------------")
            print("Fold (" + str(i) + "): ")
            print("Test LL: " + str(test_ll))
            print("Learning time: " + str(learning_time))

    # Generate the average results and store them in the dictionary, then store them in a JSON file
    avg_test_ll = avg_test_ll / n_folds
    avg_learning_time = avg_learning_time / n_folds / 1000  # in seconds
    results["average_test_ll"] = avg_test_ll
    results["average_learning_time"] = avg_learning_time
    results["folds"] = folds
    store_json(results, result_path, filename)

    print("----------------------------------------")
    print("----------------------------------------")
    print("Average Test LL: " + str(avg_test_ll))
    print("Average learning time: " + str(avg_learning_time))


def store_json(results, path, filename):
    if os.path.isfile(path + filename + "_results_KDE.json"):
        os.remove(path + filename + "_results_KDE.json")
        with open(path + filename + "_results_KDE.json", 'w') as fp:
            json.dump(results, fp, sort_keys=True, indent=4)
    else:
        with open(path + filename + "_results_KDE.json", 'w') as fp:
            json.dump(results, fp, sort_keys=True, indent=4)
