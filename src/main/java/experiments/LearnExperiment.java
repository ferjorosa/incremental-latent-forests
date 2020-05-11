package experiments;

import eu.amidst.extension.util.LogUtils;

public interface LearnExperiment {

    void runLearnExperiment(long seed, LogUtils.LogLevel logLevel) throws Exception;
}
