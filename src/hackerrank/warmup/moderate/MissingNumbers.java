package hackerrank.warmup.moderate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by abhinav on 19/5/15.
 */
public class MissingNumbers {

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] inputA = new int[n];
        for(int i = 0; i < n; i++)  {
           inputA[i] = sc.nextInt();
        }

        sc.nextLine();
        int m = Integer.parseInt(sc.nextLine());
        int[] inputB = new int[m];
        for(int i = 0; i < m; i++)  {
            inputB[i] = sc.nextInt();
        }

        List<Integer> result = missingNumbers(inputA, inputB);
        int count = 0;
        for(int num : result)   {
            count++;
            System.out.print(num);
            if(count < result.size())   {
                System.out.print(" ");
            }
        }
    }

    public static List<Integer> missingNumbers(int[] inputA, int[] inputB) {
        int[] count =  new int[100];
        List<Integer> missingNumbers = new ArrayList<Integer>();

        int min = findMin(inputB);

        for (int i = 0; i < inputB.length; i++) {
            count[inputB[i] - min]++;
        }

        for (int i = 0; i < inputA.length; i++) {
            count[inputA[i] - min]--;
        }

        for (int i = 0; i < count.length; i++) {
            if(count[i] > 0) {
                missingNumbers.add(min + i);
            }
        }

        return missingNumbers;
    }

    private static int findMin(int[] input)    {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < input.length; i++)   {
            if(input[i] < min)  {
                min  = input[i];
            }
        }
        return min;
    }
}
