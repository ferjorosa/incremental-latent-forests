import statsmodels.api as sm
import numpy as np
from scipy.io import arff
import pandas as pd

data_name = "iris"
train_data_path = "../../data/continuous/" + data_name + "/10_folds/" + data_name + "_1_train.arff"
test_data_path = "../../data/continuous/" + data_name + "/10_folds/" + data_name + "_1_test.arff"

train_data = arff.loadarff(train_data_path)[0]
train_data = pd.DataFrame(train_data)
train_data = train_data.values

test_data = arff.loadarff(test_data_path)[0]
test_data = pd.DataFrame(test_data)
test_data = test_data.values

dens_u = sm.nonparametric.KDEMultivariate(data=train_data,
                                          var_type='cccc', bw='normal_reference')

log_likelihoods = np.log(dens_u.pdf(test_data))
ll_sum = np.sum(log_likelihoods)