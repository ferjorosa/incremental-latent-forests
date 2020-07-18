import numpy as np
from spn.structure.Base import Context
from spn.structure.StatisticalTypes import MetaType
from spn.algorithms.LearningWrappers import learn_mspn
from scipy.io import arff
import pandas as pd
from spn.algorithms.Inference import log_likelihood

data_name = "iris"
train_data_path = "../../data/continuous/" + data_name + "/10_folds/" + data_name + "_1_train.arff"
test_data_path = "../../data/continuous/" + data_name + "/10_folds/" + data_name + "_1_test.arff"

train_data = arff.loadarff(train_data_path)[0]
train_data = pd.DataFrame(train_data)
train_data = train_data.values

test_data = arff.loadarff(test_data_path)[0]
test_data = pd.DataFrame(test_data)
test_data = test_data.values

ds_context = Context(meta_types=[MetaType.REAL, MetaType.REAL, MetaType.REAL, MetaType.REAL])
ds_context.add_domains(train_data)

mspn = learn_mspn(train_data, ds_context, min_instances_slice=20)

ll = log_likelihood(mspn, test_data)
ll_total = np.sum(ll)