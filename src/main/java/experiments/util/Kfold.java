package experiments.util;

import eu.amidst.core.datastream.Attribute;
import eu.amidst.core.datastream.DataInstance;
import eu.amidst.core.datastream.DataOnMemory;
import eu.amidst.core.datastream.DataOnMemoryListContainer;
import eu.amidst.core.datastream.filereaders.arffFileReader.ARFFDataWriter;
import eu.amidst.core.io.DataStreamLoader;
import eu.amidst.extension.data.DataUtils;
import eu.amidst.extension.util.MyMath;
import voltric.util.Tuple;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** Generates k folds from a dataset in the form of a list of tuples
 *
 * It can filter zero-variance columns (if any of the folds contains a column with zero variance, it's filtered)
 */
public class Kfold {

    public static void main(String[] args) throws Exception {

        /* Discrete (low-medium) */
        /*
        String directory = "discrete";
        String[] fileNames = {
                "alarm", "asia", "balance_scale", "breast_cancer", "breast_w", "cancer", "car_evaluation",
                "child", "coil_42", "coleman", "earthquake", "hannover", "hayes_roth", "hiv_test", "house_building", "insurance",
                "mildew", "monks_1", "monks_2", "monks_3", "mushroom", "nursery", "pascal_voc_2007", "sachs", "solar_flare",
                "somerville", "spect_heart", "survey", "vote", "water", "web_phishing", "zoo"
        };
        */
        /* Continuous (low-medium) */
        String directory = "continuous";
        String[] fileNames = {
                "alcohol", "banknote", "blood_transfusion", "breast_cancer_coimbra", "buddymove", "cpu", "cryotherapy",
                "ecoli", "glass", "haberman", "ilpd", "ionosphere", "iris", "leaf", "nba",
                "parkinsons", "pima", "planning_relax", "qsar_aqua_toxicity", "qsar_fish_toxicity", "real_state_valuation",
                "seeds", "travel_reviews", "user_knowledge", "vehicle", "vertebral", "wdbc", "wholesale", "wine",
                "wine_quality_white", "yeast"
        };

        for(String fileName: fileNames) {
            System.out.println("\n" + fileName);
            String filePath = "data/"+ directory + "/" +fileName + "/" + fileName + ".arff";
            DataOnMemory<DataInstance> data = DataStreamLoader.open(filePath).toDataOnMemory();

            List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> folds = generate(data, 10);
            List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> filteredFolds = filterZeroVarianceColumns(folds);
            export(filteredFolds, fileName, "data/"+ directory + "/" + fileName + "/10_folds/");
        }
    }

    public static List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> generateAndExport(DataOnMemory<DataInstance> data,
                                                                                                        int k,
                                                                                                        String dataName,
                                                                                                        String path) throws IOException {

        List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> trainTestFolds = generate(data, k);
        trainTestFolds = filterZeroVarianceColumns(trainTestFolds);

        for(int i = 0; i < trainTestFolds.size(); i++){
            Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>> fold = trainTestFolds.get(i);
            ARFFDataWriter.writeToARFFFile(fold.getFirst(), path + dataName + "_" + (i+1) + "_train.arff");
            ARFFDataWriter.writeToARFFFile(fold.getSecond(), path + dataName + "_" + (i+1) + "_test.arff");
        }

        return trainTestFolds;
    }

    private static void export(List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> folds,
                               String dataName,
                               String path) throws IOException {

        /* Create directory if it doesnt exist */
        new File(path).mkdirs();

        for(int i = 0; i < folds.size(); i++){
            Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>> fold = folds.get(i);
            ARFFDataWriter.writeToARFFFile(fold.getFirst(), path + dataName + "_" + (i+1) + "_train.arff");
            ARFFDataWriter.writeToARFFFile(fold.getSecond(), path + dataName + "_" + (i+1) + "_test.arff");
        }
    }


    /** Generates a list of k train-test folds */
    private static List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> generate(DataOnMemory<DataInstance> data, int k) {

        /* Initialize the list of train-set folds */
        List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> trainTestFolds = new ArrayList<>(k);

        /* First, divide the dataset into k folds */
        int[] indices = new int[k+1];
        int division = data.getNumberOfDataInstances() / k;
        for(int i = 1; i < indices.length -1; i++) {
            int t = indices[i-1];
            indices[i] = t + division;
        }
        indices[k] = data.getNumberOfDataInstances();

        List<List<DataInstance>> folds = new ArrayList<>(k);

        for(int i = 0; i < k; i++){
            List<DataInstance> fold = new ArrayList<>(indices[i+1] - indices[i]);
            folds.add(fold);
            for(int j = indices[i]; j < indices[i+1]; j++){
                fold.add(data.getDataInstance(j));
            }
        }

        /* Then rotate these folds to generate a pair of train and test datasets */
        for(int i = 0; i < k; i++) {
            List<DataInstance> trainInstances = new ArrayList<>();
            List<DataInstance> testInstances = new ArrayList<>();
            for(int j = 0; j < k; j++) {
                if (j == i)
                    testInstances.addAll(folds.get(j));
                else
                    trainInstances.addAll(folds.get(j));
            }
            DataOnMemory<DataInstance> foldTrainData = new DataOnMemoryListContainer<>(data.getAttributes(), trainInstances);
            DataOnMemory<DataInstance> foldTestData = new DataOnMemoryListContainer<>(data.getAttributes(), testInstances);
            trainTestFolds.add(new Tuple<>(foldTrainData, foldTestData));
        }

        return trainTestFolds;
    }

    /** Iterate through the folds, if there is a column in any of them (on the TRAIN part) with zero variance, it is filtered */
    private static List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> filterZeroVarianceColumns(List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> folds) {

        /* Find columns with zero variance*/
        Set<Integer> attributesToFilter = new LinkedHashSet<>();
        for(Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>> fold: folds) {

            DataOnMemory<DataInstance> train = fold.getFirst();

            // Initialize columnsData
            List<double[]> columnsData = new ArrayList<>(train.getAttributes().getNumberOfAttributes());
            for(int i = 0; i < train.getAttributes().getNumberOfAttributes(); i++)
                columnsData.add(new double[train.getNumberOfDataInstances()]);

            // Iterate through the instances and add its data to its corresponding array
            for(int i = 0; i < train.getNumberOfDataInstances(); i++){
                double[] instanceValues = train.getDataInstance(i).toArray();
                for(int j = 0; j < instanceValues.length; j++){
                    columnsData.get(j)[i] = instanceValues[j]; // j columns, i instance
                }
            }

            // Once the data has been separated by column, estimate the variance
            for(int j = 0; j < columnsData.size(); j++) {
                double stdev = MyMath.stDev(columnsData.get(j));
                double var = Math.pow(stdev, 2);
                if(var == 0)
                    attributesToFilter.add(j);
            }
        }

        /* Print the attributes with zero variance that are going to be filtered */
        System.out.println("Attributes with zero variance in at least one fold (will be ignored): ");
        for(Integer zeroVarAttributeIndex: attributesToFilter) {
            Attribute zeroVarAttribute = folds.get(0).getFirst()
                    .getAttributes().getFullListOfAttributes().get(zeroVarAttributeIndex);
            System.out.print(zeroVarAttribute.getName()+", ");
        }
        System.out.println();

        /* Select columns with more than zero variance and filter the others */
        List<Attribute> attributesForProjection = new ArrayList<>();
        List<Attribute> columns = folds.get(0).getFirst().getAttributes().getFullListOfAttributes();
        for(int j = 0; j < columns.size(); j++) {
            if(!attributesToFilter.contains(j))
                attributesForProjection.add(columns.get(j));
        }

        /* Project each fold */
        List<Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>>> projectedFolds = new ArrayList<>(folds.size());
        for(Tuple<DataOnMemory<DataInstance>, DataOnMemory<DataInstance>> fold: folds) {
            DataOnMemory<DataInstance> train = fold.getFirst();
            DataOnMemory<DataInstance> test = fold.getSecond();
            DataOnMemory<DataInstance> projectedTrain = DataUtils.project(train, attributesForProjection);
            DataOnMemory<DataInstance> projectedTest = DataUtils.project(test, attributesForProjection);
            projectedFolds.add(new Tuple<>(projectedTrain, projectedTest));
        }

        return projectedFolds;
    }
}
