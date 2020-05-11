package experiments;

import eu.amidst.extension.util.LogUtils;

public interface CrossValidationExperiment {

    void runCrossValExperiment(long seed, int kFolds, int run, LogUtils.LogLevel foldLogLevel) throws Exception;
}
