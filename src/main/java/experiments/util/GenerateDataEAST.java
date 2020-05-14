package experiments.util;

import voltric.data.DiscreteData;
import voltric.data.DiscreteDataInstance;
import voltric.io.data.DataFileLoader;
import voltric.variables.DiscreteVariable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Simple script to transform ARFF files into DATA files that can be read by the EAST algorithm
 */
public class GenerateDataEAST {

    public static void main(String[] args) throws IOException{

        String directory = "discrete";
        String[] fileNames = {
            "news_100"
        };

        for(String fileName: fileNames) {
            DiscreteData dataVoltric = DataFileLoader.loadDiscreteData("data/" + directory + "/" + fileName + "/" + fileName + ".arff");
            generate(dataVoltric, "data_east/" + directory + "/" + fileName + "/", fileName + ".data");

            for (int i = 1; i < 11; i++) {

                DiscreteData foldTrain = DataFileLoader.loadDiscreteData("data/" + directory + "/" + fileName + "/10_folds/" + fileName + "_" + i + "_train.arff");
                generate(foldTrain, "data_east/" + directory + "/" + fileName + "/10_folds/", fileName + "_" + i + "_train.data");

                DiscreteData foldTest = DataFileLoader.loadDiscreteData("data/" + directory + "/" + fileName + "/10_folds/" + fileName + "_" + i + "_test.arff");
                generate(foldTest, "data_east/" + directory + "/" + fileName + "/10_folds/", fileName + "_" + i + "_test.data");
            }
        }
    }

    public static void generate(DiscreteData data, String path, String fileName) throws IOException{

        /* Create the hierarchy of directories (if necessary) */
        new File(path).mkdirs();

        String filePath = path + fileName;
        FileWriter fw = new FileWriter(filePath);

        // write data name
        fw.write("Name: "+ data.getName());

        // write attributes
        fw.write("\n\n//Variables: name of variable followed by names of states\n");

        for(DiscreteVariable var: data.getVariables()) {
            fw.write("\n" + var.getName()+": ");
            for(String state: var.getStates())
                fw.write(state+" ");
        }

        fw.write("\n\n//Records: Numbers in the last column are frequencies.\n\n");
        // write instances
        for (DiscreteDataInstance instance : data.getInstances())
            writeInstanceToFile(instance, fw, " ");

        fw.close();
    }

    private static void writeInstanceToFile(DiscreteDataInstance instance, FileWriter writer, String separator) throws IOException{
        try {
            String instanceString = instanceToString(instance, separator);
            double weight = instance.getData().getWeight(instance);

            writer.write(instanceString + "   " + weight + "\n");
        } catch (Exception e) {
            throw e;
        }
    }

    private static String instanceToString(DiscreteDataInstance instance, String separator) {
        String s = "";

        // Append all the columns of the DataInstance with  the separator except the last one
        for(int i = 0; i < instance.getTextualValues().size() - 1; i++)
            s += instance.getNumericValue(i) + separator;
        // Append the last column of the instance without the separator
        s += instance.getNumericValue(instance.getTextualValues().size() - 1);
        return s;
    }
}
