package methods;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.amidst.extension.util.MyMath;
import eu.amidst.extension.util.Tuple3;
import experiments.util.result.ExperimentResult;
import experiments.util.result.FoldResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public interface Method {

    default void showAverageScoreAndTime(List<Tuple3<Double, Double, Long>> scoreAndTime) {

        List<Double> testLLs = scoreAndTime.stream().map(x-> x.getFirst()).collect(Collectors.toList());
        List<Double> trainBICs = scoreAndTime.stream().map(x-> x.getSecond()).collect(Collectors.toList());
        List<Double> learningTimes = scoreAndTime.stream().map(x-> (double) x.getThird()).collect(Collectors.toList());

        double averageTestLL = MyMath.mean(testLLs);
        double averageTrainBIC = MyMath.mean(trainBICs);
        double averageTime = MyMath.mean(learningTimes) / 1000;

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat f = new DecimalFormat("0.00", otherSymbols);
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println("Average learning Time: " + f.format(averageTime) + "s");
        System.out.println("Average Test Log-likelihood: " + f.format(averageTestLL));
        System.out.println("Average Train BIC: " + f.format(averageTrainBIC));
    }

    default void storeResults(List<Tuple3<Double, Double, Long>> scoreAndTime,
                              String directoryPath,
                              String fileName) throws IOException {

        new File(directoryPath).mkdirs();

        /* Create FoldResult objects */
        Map<String, FoldResult> folds = new LinkedHashMap<>(scoreAndTime.size());
        int i = 1;
        for(Tuple3<Double, Double, Long> tuple: scoreAndTime) {
            FoldResult foldResult = new FoldResult();
            foldResult.setTest_LL(tuple.getFirst());
            foldResult.setTrain_BIC(tuple.getSecond());
            foldResult.setLearning_time(tuple.getThird());
            folds.put("fold_" + i, foldResult);
            i++;
        }

        /* Estimate average scores and time */
        List<Double> testLLs = scoreAndTime.stream().map(x-> x.getFirst()).collect(Collectors.toList());
        List<Double> trainBICs = scoreAndTime.stream().map(x-> x.getSecond()).collect(Collectors.toList());
        List<Double> learningTimes = scoreAndTime.stream().map(x-> (double) x.getThird()).collect(Collectors.toList());

        double averageTestLL = MyMath.mean(testLLs);
        double averageTrainBIC = MyMath.mean(trainBICs);
        double averageTime = MyMath.mean(learningTimes) / 1000;

        /* Create the ExperimentResult object */
        ExperimentResult experimentResult = new ExperimentResult();
        experimentResult.setAverage_train_BIC(averageTrainBIC);
        experimentResult.setAverage_test_LL(averageTestLL);
        experimentResult.setAverage_learning_time(averageTime);
        experimentResult.setFolds(folds);

        /* Write the Json file */
        String output = directoryPath + "/" + fileName;
        try (Writer writer = new FileWriter(output)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(experimentResult, writer);
        }
    }
}
