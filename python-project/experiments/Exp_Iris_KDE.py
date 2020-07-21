import statsmodels.api as sm
import numpy as np
from scipy.io import arff
import pandas as pd
from spn.structure.StatisticalTypes import MetaType
from spn.structure.Base import Context

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

dens_u = sm.nonparametric.KDEMultivariate(data=train_data,
                                          var_type='cccc', bw='normal_reference')

log_likelihoods = np.log(dens_u.pdf(test_data))
ll_sum = np.sum(log_likelihoods)

print(ll_sum)