package experiments.util;

import voltric.data.DiscreteData;
import voltric.data.DiscreteDataInstance;
import voltric.variables.DiscreteVariable;
import voltric.variables.IVariable;
import voltric.variables.StateSpaceType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* We have created this class for the Hannover, HIV, Coleman and House building datasets, whose data comes from a known
distribution, we want to create. We need to shuffle its data or it will hurt cross validation.
*/
public class ExportShuffledData {

    public static void export(DiscreteData data, String filePathString, long seed) throws IOException {
        FileWriter fw = new FileWriter(filePathString);

        // Writes the ARFF @relation line that identifies the Data
        fw.write("@relation " + data.getName() + "\n\n");

        // Writes the ARFF attributes
        for (DiscreteVariable att : data.getVariables()) {
            fw.write(attributeToArffString(att) + "\n");
        }

        // Writes the ARFF data instances
        fw.write("\n@data\n");
        shuffleAndWriteInstances(data, fw, ",", seed);

        // Closes the file
        fw.close();
    }

    private static <V extends IVariable> String attributeToArffString(V attribute){
        if(attribute.getStateSpaceType() == StateSpaceType.REAL)
            return "@attribute " + attribute.getName() + " real";
        else if(attribute.getStateSpaceType() == StateSpaceType.FINITE) {
            StringBuilder stringBuilder = new StringBuilder("@attribute " + attribute.getName() + " {");
            DiscreteVariable discreteAttribute = (DiscreteVariable) attribute;
            List<String> attributeStates = discreteAttribute.getStates();

            // Append all the variable states minus the last one
            attributeStates
                    .stream()
                    .limit(discreteAttribute.getStates().size() - 1)
                    .forEach(e -> stringBuilder.append(e + ", "));

            // Append the last state
            stringBuilder.append(attributeStates.get(attributeStates.size() - 1) + "}");

            return stringBuilder.toString();
        }
        else
            throw new IllegalArgumentException("Unknown StateSpaceType");
    }

    private static void shuffleAndWriteInstances(DiscreteData data, FileWriter writer, String separator, long seed) throws IOException {

        /* Transform dataSet to a list of instances in string form */
        List<String> instancesInStringForm = new ArrayList<>(data.getTotalWeight());
        for(DiscreteDataInstance instance: data.getInstances()){
            int weight = data.getWeight(instance);
            for(int i=0; i < weight; i++)
                instancesInStringForm.add(instanceToArffString(instance, separator));
        }

        /* Shuffle it */
        Collections.shuffle(instancesInStringForm, new Random(seed));

        /* Write it */
        for(String instanceString: instancesInStringForm)
            writer.write(instanceString + "\n");
    }

    private static String instanceToArffString(DiscreteDataInstance instance, String separator) {
        String s = "";

        // Append all the columns of the DataInstance with  the separator except the last one
        for(int i = 0; i < instance.getTextualValues().size() - 1; i++)
            s += instance.getTextualValue(i) + separator;
        // Append the last column of the instance without the separator
        s += instance.getTextualValue(instance.getTextualValues().size() - 1);
        return s;
    }
}
