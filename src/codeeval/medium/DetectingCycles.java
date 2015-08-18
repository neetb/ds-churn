package codeeval.medium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ramneet on 30/4/15.
 */
public class DetectingCycles {

    private List<String> inputLines = new ArrayList<String>();
    public static void main(String[] args) throws IOException {
        if(args.length != 0)    {
            System.out.println("Invalid number of arguments : Expected 1 argument giving the input file name");
            return;
        }

        String inputFileName = "/home/abhinav/Work/Ramneet/learning/git/ds-churn/src/codeeval/medium/input/DetectingCycles-input01.txt";//args[0];
        DetectingCycles detectingCycles = new DetectingCycles();
        detectingCycles.process(inputFileName);
        detectingCycles.find();

    }

    private void process(String inputFileName) throws IOException {
        BufferedReader reader = null;
        try    {
            reader = new BufferedReader(new FileReader(inputFileName));
            String input = null;
            while((input = reader.readLine()) != null) {
                inputLines.add(input);
            }
        }
        finally    {
            reader.close();
        }
    }

    private void find() {
        for(String input : inputLines)  {
            Map<String, Integer> inputMap = new HashMap<String,Integer>();
            String[] inputNums = input.split(" ");
            for(int i=0; i<inputNums.length; i++)   {
                String inputNum = inputNums[i];
                Integer value;
                if((value=inputMap.get(inputNum)) == null) {
                    inputMap.put(inputNum, i);
                }
                else    {
                    print(inputNums, value, i-1);
                    break;
                }
            }
        }
    }


    private void print(String[] inputNums, int start, int end)  {
        for(int i=start; i<=end; i++)   {
            System.out.print(inputNums[i]);
            if(i < inputNums.length - 1)
                System.out.print(" ");
        }
        System.out.println();
    }

}
