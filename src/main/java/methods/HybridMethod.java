package methods;

import eu.amidst.core.datastream.DataInstance;
import eu.amidst.core.datastream.DataOnMemory;
import eu.amidst.core.models.BayesianNetwork;
import eu.amidst.extension.io.GenieWriter;
import eu.amidst.extension.util.LogUtils;
import voltric.util.Tuple;

import java.io.File;
import java.util.List;

public interface HybridMethod extends Method {

    void runHybrid(List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> folds,
                   String dataName,
                   int run,
                   LogUtils.LogLevel foldLogLevel) throws Exception;

    default void storeHybridModels(List<BayesianNetwork> models,
                                   String directoryPath,
                                   String dataName,
                                   String methodName) throws Exception {

        new File(directoryPath).mkdirs();

        for(int i = 0; i < models.size(); i++) {
            GenieWriter genieWriter = new GenieWriter();
            genieWriter.write(models.get(i), new File(directoryPath + "/" + dataName + "_" +methodName + ".xdsl"));
        }
    }
}
