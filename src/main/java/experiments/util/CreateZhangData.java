package experiments.util;

import voltric.data.DiscreteData;
import voltric.data.DiscreteDataInstance;
import voltric.variables.DiscreteVariable;
import voltric.variables.modelTypes.VariableType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Data used in N. L. Zhang. Hierarchical latent class models for cluster analysis. Journal of Machine Learning Research, 5:697–723, 2004.

This script generates arff files with the specified joint distribution of the following datasets:
- Coleman
- HIV_test
- House building
- Hannover
*/
public class CreateZhangData {

    public static void main(String[] args) throws Exception {
        long seed = 0;

        coleman(seed);
        hiv_test(seed);
        house_building(seed);
        hannover(seed);
    }


    private static void coleman(long seed) throws IOException {
        List<String> states = new ArrayList<>();
        states.add("0");
        states.add("1");

        DiscreteVariable A = new DiscreteVariable("A", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable B = new DiscreteVariable("B", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable C = new DiscreteVariable("C", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable D = new DiscreteVariable("D", states, VariableType.MANIFEST_VARIABLE);
        List<DiscreteVariable> variables = new ArrayList<>();
        variables.add(A);
        variables.add(B);
        variables.add(C);
        variables.add(D);

        DiscreteData data =  new DiscreteData("coleman", variables);
        int[] inst_1 = {0,0,0,0};
        data.add(new DiscreteDataInstance(inst_1), 458);
        int[] inst_2 = {0,0,0,1};
        data.add(new DiscreteDataInstance(inst_2), 140);
        int[] inst_3 = {0,0,1,0};
        data.add(new DiscreteDataInstance(inst_3), 110);
        int[] inst_4 = {0,0,1,1};
        data.add(new DiscreteDataInstance(inst_4), 49);
        int[] inst_5 = {0,1,0,0};
        data.add(new DiscreteDataInstance(inst_5), 171);
        int[] inst_6 = {0,1,0,1};
        data.add(new DiscreteDataInstance(inst_6), 182);
        int[] inst_7 = {0,1,1,0};
        data.add(new DiscreteDataInstance(inst_7), 56);
        int[] inst_8 = {0,1,1,1};
        data.add(new DiscreteDataInstance(inst_8), 87);
        int[] inst_9 = {1,0,0,0};
        data.add(new DiscreteDataInstance(inst_9), 184);
        int[] inst_10 = {1,0,0,1};
        data.add(new DiscreteDataInstance(inst_10), 75);
        int[] inst_11 = {1,0,1,0};
        data.add(new DiscreteDataInstance(inst_11), 531);
        int[] inst_12 = {1,0,1,1};
        data.add(new DiscreteDataInstance(inst_12), 281);
        int[] inst_13 = {1,1,0,0};
        data.add(new DiscreteDataInstance(inst_13), 85);
        int[] inst_14 = {1,1,0,1};
        data.add(new DiscreteDataInstance(inst_14), 97);
        int[] inst_15 = {1,1,1,0};
        data.add(new DiscreteDataInstance(inst_15), 338);
        int[] inst_16 = {1,1,1,1};
        data.add(new DiscreteDataInstance(inst_16), 554);

        ExportShuffledData.export(data, "coleman.arff", seed);
    }

    private static void hiv_test(long seed) throws IOException {
        /* Variables */
        List<String> states = new ArrayList<>();
        states.add("0");
        states.add("1");

        DiscreteVariable A = new DiscreteVariable("A", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable B = new DiscreteVariable("B", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable C = new DiscreteVariable("C", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable D = new DiscreteVariable("D", states, VariableType.MANIFEST_VARIABLE);
        List<DiscreteVariable> variables = new ArrayList<>();
        variables.add(A);
        variables.add(B);
        variables.add(C);
        variables.add(D);

        /* Data */
        DiscreteData data =  new DiscreteData("hiv_test", variables);
        int[] inst_1 = {0,0,0,0};
        data.add(new DiscreteDataInstance(inst_1), 170);
        int[] inst_2 = {0,0,0,1};
        data.add(new DiscreteDataInstance(inst_2), 15);
        int[] inst_3 = {0,0,1,0};
        data.add(new DiscreteDataInstance(inst_3), 0);
        int[] inst_4 = {0,0,1,1};
        data.add(new DiscreteDataInstance(inst_4), 0);
        int[] inst_5 = {0,1,0,0};
        data.add(new DiscreteDataInstance(inst_5), 6);
        int[] inst_6 = {0,1,0,1};
        data.add(new DiscreteDataInstance(inst_6), 0);
        int[] inst_7 = {0,1,1,0};
        data.add(new DiscreteDataInstance(inst_7), 0);
        int[] inst_8 = {0,1,1,1};
        data.add(new DiscreteDataInstance(inst_8), 0);
        int[] inst_9 = {1,0,0,0};
        data.add(new DiscreteDataInstance(inst_9), 4);
        int[] inst_10 = {1,0,0,1};
        data.add(new DiscreteDataInstance(inst_10), 17);
        int[] inst_11 = {1,0,1,0};
        data.add(new DiscreteDataInstance(inst_11), 0);
        int[] inst_12 = {1,0,1,1};
        data.add(new DiscreteDataInstance(inst_12), 83);
        int[] inst_13 = {1,1,0,0};
        data.add(new DiscreteDataInstance(inst_13), 1);
        int[] inst_14 = {1,1,0,1};
        data.add(new DiscreteDataInstance(inst_14), 4);
        int[] inst_15 = {1,1,1,0};
        data.add(new DiscreteDataInstance(inst_15), 0);
        int[] inst_16 = {1,1,1,1};
        data.add(new DiscreteDataInstance(inst_16), 128);

        /* Export */
        ExportShuffledData.export(data, "hiv_test.arff", seed);
    }

    private static void house_building(long seed) throws IOException {

        /* Variables */
        List<String> states = new ArrayList<>();
        states.add("0");
        states.add("1");

        DiscreteVariable A = new DiscreteVariable("A", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable B = new DiscreteVariable("B", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable C = new DiscreteVariable("C", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable D = new DiscreteVariable("D", states, VariableType.MANIFEST_VARIABLE);
        List<DiscreteVariable> variables = new ArrayList<>();
        variables.add(A);
        variables.add(B);
        variables.add(C);
        variables.add(D);

        /* Data */
        DiscreteData data =  new DiscreteData("house_building", variables);
        int[] inst_1 = {0,0,0,0};
        data.add(new DiscreteDataInstance(inst_1), 193);
        int[] inst_2 = {0,0,0,1};
        data.add(new DiscreteDataInstance(inst_2), 44);
        int[] inst_3 = {0,0,1,0};
        data.add(new DiscreteDataInstance(inst_3), 26);
        int[] inst_4 = {0,0,1,1};
        data.add(new DiscreteDataInstance(inst_4), 34);
        int[] inst_5 = {0,1,0,0};
        data.add(new DiscreteDataInstance(inst_5), 103);
        int[] inst_6 = {0,1,0,1};
        data.add(new DiscreteDataInstance(inst_6), 77);
        int[] inst_7 = {0,1,1,0};
        data.add(new DiscreteDataInstance(inst_7), 15);
        int[] inst_8 = {0,1,1,1};
        data.add(new DiscreteDataInstance(inst_8), 58);
        int[] inst_9 = {1,0,0,0};
        data.add(new DiscreteDataInstance(inst_9), 58);
        int[] inst_10 = {1,0,0,1};
        data.add(new DiscreteDataInstance(inst_10), 16);
        int[] inst_11 = {1,0,1,0};
        data.add(new DiscreteDataInstance(inst_11), 32);
        int[] inst_12 = {1,0,1,1};
        data.add(new DiscreteDataInstance(inst_12), 48);
        int[] inst_13 = {1,1,0,0};
        data.add(new DiscreteDataInstance(inst_13), 84);
        int[] inst_14 = {1,1,0,1};
        data.add(new DiscreteDataInstance(inst_14), 54);
        int[] inst_15 = {1,1,1,0};
        data.add(new DiscreteDataInstance(inst_15), 60);
        int[] inst_16 = {1,1,1,1};
        data.add(new DiscreteDataInstance(inst_16), 283);

        /* Export */
        ExportShuffledData.export(data, "house_building.arff", seed);
    }

    private static void hannover(long seed) throws IOException {

        /* Variables */
        List<String> states = new ArrayList<>();
        states.add("no"); // no = 0
        states.add("yes"); // yes = 1

        DiscreteVariable back_pain = new DiscreteVariable("back_pain", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable neck_pain = new DiscreteVariable("neck_pain", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable joint_pain = new DiscreteVariable("joint_pain", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable swelling = new DiscreteVariable("swelling", states, VariableType.MANIFEST_VARIABLE);
        DiscreteVariable stiffness = new DiscreteVariable("stiffness", states, VariableType.MANIFEST_VARIABLE);
        List<DiscreteVariable> variables = new ArrayList<>();
        variables.add(back_pain);
        variables.add(neck_pain);
        variables.add(joint_pain);
        variables.add(swelling);
        variables.add(stiffness);

        /* Data */
        DiscreteData data =  new DiscreteData("hannover", variables);
        int[] inst_1 = {0,0,0,0,0};
        data.add(new DiscreteDataInstance(inst_1), 3634);
        int[] inst_2 = {0,0,0,0,1};
        data.add(new DiscreteDataInstance(inst_2), 73);
        int[] inst_3 = {0,0,0,1,0};
        data.add(new DiscreteDataInstance(inst_3), 87);
        int[] inst_4 = {0,0,0,1,1};
        data.add(new DiscreteDataInstance(inst_4), 10);
        int[] inst_5 = {0,0,1,0,0};
        data.add(new DiscreteDataInstance(inst_5), 440);
        int[] inst_6 = {0,0,1,0,1};
        data.add(new DiscreteDataInstance(inst_6), 89);
        int[] inst_7 = {0,0,1,1,0};
        data.add(new DiscreteDataInstance(inst_7), 106);
        int[] inst_8 = {0,0,1,1,1};
        data.add(new DiscreteDataInstance(inst_8), 75);
        int[] inst_9 = {0,1,0,0,0};
        data.add(new DiscreteDataInstance(inst_9), 295);
        int[] inst_10 = {0,1,0,0,1};
        data.add(new DiscreteDataInstance(inst_10), 25);
        int[] inst_11 = {0,1,0,1,0};
        data.add(new DiscreteDataInstance(inst_11), 15);
        int[] inst_12 = {0,1,0,1,1};
        data.add(new DiscreteDataInstance(inst_12), 5);
        int[] inst_13 = {0,1,1,0,0};
        data.add(new DiscreteDataInstance(inst_13), 137);
        int[] inst_14 = {0,1,1,0,1};
        data.add(new DiscreteDataInstance(inst_14), 42);
        int[] inst_15 = {0,1,1,1,0};
        data.add(new DiscreteDataInstance(inst_15), 35);
        int[] inst_16 = {0,1,1,1,1};
        data.add(new DiscreteDataInstance(inst_16), 39);
        int[] inst_17 = {1,0,0,0,0};
        data.add(new DiscreteDataInstance(inst_17), 489);
        int[] inst_18 = {1,0,0,0,1};
        data.add(new DiscreteDataInstance(inst_18), 37);
        int[] inst_19 = {1,0,0,1,0};
        data.add(new DiscreteDataInstance(inst_19), 23);
        int[] inst_20 = {1,0,0,1,1};
        data.add(new DiscreteDataInstance(inst_20), 7);
        int[] inst_21 = {1,0,1,0,0};
        data.add(new DiscreteDataInstance(inst_21), 255);
        int[] inst_22 = {1,0,1,0,1};
        data.add(new DiscreteDataInstance(inst_22), 116);
        int[] inst_23 = {1,0,1,1,0};
        data.add(new DiscreteDataInstance(inst_23), 71);
        int[] inst_24 = {1,0,1,1,1};
        data.add(new DiscreteDataInstance(inst_24), 65);
        int[] inst_25 = {1,1,0,0,0};
        data.add(new DiscreteDataInstance(inst_25), 306);
        int[] inst_26 = {1,1,0,0,1};
        data.add(new DiscreteDataInstance(inst_26), 48);
        int[] inst_27 = {1,1,0,1,0};
        data.add(new DiscreteDataInstance(inst_27), 16);
        int[] inst_28 = {1,1,0,1,1};
        data.add(new DiscreteDataInstance(inst_28), 11);
        int[] inst_29 = {1,1,1,0,0};
        data.add(new DiscreteDataInstance(inst_29), 229);
        int[] inst_30 = {1,1,1,0,1};
        data.add(new DiscreteDataInstance(inst_30), 162);
        int[] inst_31 = {1,1,1,1,0};
        data.add(new DiscreteDataInstance(inst_31), 44);
        int[] inst_32 = {1,1,1,1,1};
        data.add(new DiscreteDataInstance(inst_32), 176);

        /* Export */
        ExportShuffledData.export(data, "hannover.arff", seed);
    }
}
