  package codeeval.medium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ramneet on 30/4/15.
 */
public class LongestLines {

    private List<String> inputs = new ArrayList<String>();
    private int resultCount;

    public static void main(String[] args)  throws IOException {
        if(args.length != 0)    {
            System.out.println("Invalid number of arguments : Expected 1 argument giving the input file name");
            return;
        }

        String inputFileName = "/home/abhinav/Work/Ramneet/learning/git/ds-churn/src/codeeval/medium/input/LongestLines-input01.txt";//args[0];
        LongestLines longestLines = new LongestLines();
        longestLines.process(inputFileName);

        if(longestLines.isInputValid()) {
            longestLines.find();
        }

    }

    private boolean isInputValid()  {
        return resultCount <= inputs.size();
    }

    private void process(String inputFileName) throws IOException {
        BufferedReader reader = null;
        try    {
             reader = new BufferedReader(new FileReader(inputFileName));
             String count = reader.readLine();
             resultCount = Integer.parseInt(count);

             String input = null;
             while((input = reader.readLine()) != null) {
                inputs.add(input);
             }
         }
         finally    {
             reader.close();
         }
    }

    private void find() {
        sort(inputs.toArray(new String[0]), resultCount);
    }

    /* Insertion sort which will stop once it has cutOff number of sorted elements
    *
    *  4 1 3 5 2 7
    *  1 4 5  3 2 7
    * */

   /* One variant with exact same as insertion sort */
    private void sort(String[] inputs, int cutOff)   {
         for(int i=0; i<inputs.length - 1; i++) {

             int j = i+1;
             //for(int j=i+1; j<inputs.length; j++)   {

                 while((j > 0) && (lessThan(inputs[j-1], inputs[j]))) {
                     swap(inputs, j-1, j);
                     j = j-1;
                 }
             //}
         }

        print(inputs, inputs.length);
    }


    /*       why 934 ms versus 500 somthing for previous method
        private void sort(String[] inputs)   {
             for(int j=1; j<inputs.length; j++)   {

            while((j > 0) && (lessThan(inputs[j-1], inputs[j]))) {
                    swap(inputs, j-1, j);
                    j = j-1;
                }

             }
            print(inputs);
        }
    */

    private void print(String[] inputs, int cutOff) {
        for(int i=0;i<cutOff; i++)  {
            System.out.println(inputs[i]);
        }
    }

    private boolean lessThan(String left, String right) {
        return (left.length() < right.length());
    }

    private void swap(String[] inputs, int indexOne, int indexTwo) {
        String temp = inputs[indexOne];
        inputs[indexOne] = inputs[indexTwo];
        inputs[indexTwo] = temp;
    }
}
