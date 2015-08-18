package algo.DP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by abhinav on 19/6/15.
 */
public class MaximumSumProblem {

    public static int maxContiguousSum(List<Integer> input)    {
        int max = Integer.MIN_VALUE;
        int runningSum = 0;
        int start_index = 0;
        int end_index = 0;
        int best_start_index = 0;
        int best_end_index = 0;
        int i = 0;
        for(int num : input)   {
            if(runningSum >= 0)  {
                runningSum += num;
            }else   {
                start_index = i;
                runningSum = num;
            }

            if(runningSum > max)    {
                end_index = i;
                best_start_index = start_index;
                best_end_index = end_index;
                max  =runningSum;
            }
            i++;
        }

        System.out.println("index :" + best_start_index + " , " + best_end_index);
        return max;
    }


    public static int maxSubsequenceSum(List<Integer> input)    {
        int max = 0;
        int min = Integer.MIN_VALUE;
        boolean allNegative = true;
        for(int num : input)   {
            if(num >= 0){
                max  += num;
                allNegative = false;
            }else   {
                if(num > min)   {
                    min = num;
                }
            }
        }

        if(allNegative) {
            return min;
        }else {
            return max;
        }
    }

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine().trim());
        List<Integer>[] input = new List[T];
        for(int i = 0; i < T; i++)  {
            int N = Integer.parseInt(sc.nextLine().trim());
            input[i] = new ArrayList<Integer>();
            String[] inputNums = sc.nextLine().trim().split(" ");
            for(int j = 0; j < N; j++)  {
                input[i].add(Integer.parseInt(inputNums[j]));
            }
        }

        for(int i = 0; i < T; i++)  {
            System.out.println(maxContiguousSum(input[i]) + " "+ maxSubsequenceSum(input[i]));
        }
    }
}
