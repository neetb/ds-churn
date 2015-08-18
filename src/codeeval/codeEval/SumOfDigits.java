package codeeval.codeEval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramneet on 29/4/15.
 */
public class SumOfDigits {

    private List<Integer> inputs = new ArrayList<Integer>();

    public static void main(String[] args)  throws IOException{

        if (args.length != 1) {
            System.out.println("Invalid Input : Expect 1 argument giving the input file name");
            return;
        }

        String inputFileName = args[0];

        SumOfDigits sumOfDigits = new SumOfDigits();
        sumOfDigits.processInput(inputFileName);
        sumOfDigits.find();
    }

    private void processInput(String inputFileName) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFileName));
            String input = null;

            while ((input = reader.readLine()) != null) {
                int num = Integer.parseInt(input);
                inputs.add(num);
            }
        }
        finally {
            reader.close();
        }
    }


    private void find() {
       for (Integer num : inputs) {
            String numTxt = num.toString();
            int sum = 0;
            for (int i = 0; i < numTxt.length(); i++) {
                char digit = numTxt.charAt(i);
                sum += digit - '0';
            }
            System.out.println(sum);
        }
    }
}
