package hackerrank.warmup.hackerrank.difficult;

import java.util.Scanner;

/**
 * Created by abhinav on 6/7/15.
 */
public class SubsequenceWeighting {

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(sc.nextLine().trim());
            Pair[] pairs = new Pair[N];
            /*long[] keys = new long[N];
            long[] weights = new long[N];
            */
            String[] keysText = sc.nextLine().trim().split(" ");
            String[] weightText = sc.nextLine().trim().split(" ");
            for (int j = 0; j < N; j++) {
                pairs[j] = new Pair(Long.parseLong(keysText[j]), Long.parseLong(weightText[j]));
            }
            System.out.println(findMaxWeightSubsequenceV2(pairs));
        }
    }

    private static long findMaxWeightSubsequenceV1(Pair[] input)  {
        if((input == null) || (input.length == 0))  {
            return 0;
        }

        // array in which ith entry stores max sum uptil the ith element
        int N = input.length;
        long[] maxSum = new long[N];

        for(int i = 0; i < N; i++)   {
            maxSum[i] = input[i].weight;

            for(int j = i-1; j >=0; j--) {
                if (input[i].key > input[j].key) {
                    long temp = maxSum[j] + input[i].weight;
                    if (maxSum[i] < temp) {
                        maxSum[i] = temp;
                    }
                }
            }
        }

        long max = Long.MIN_VALUE;
        for(int k = 0; k < maxSum.length; k++)  {
            if(max < maxSum[k]) {
                max = maxSum[k];
            }
        }

        return max;
    }

    private static long findMaxWeightSubsequenceV2(Pair[] input)  {
        if((input == null) || (input.length == 0))  {
            return 0;
        }

        // array in which ith entry stores max sum uptil the ith element
        int N = input.length;
        long[] maxSum = new long[N];
        long[] maxKey = new long[N];
        long maxKeyValue = input[0].key;

        for(int i = 0; i < N; i++)   {
            maxSum[i] = input[i].weight;

            if(maxKeyValue < input[i].key)  {
                maxKeyValue = input[i].key;
            }

            maxKey[i] = maxKeyValue;

            for(int j = i-1; j >=0; j--)    {
                if(input[i].key > input[j].key)   {
                    long temp = maxSum[j] + input[i].weight;
                    if(maxSum[i] < temp)    {
                        maxSum[i] = temp;
                    }

                    if(input[j].key == maxKey[j]) {
                        break;
                    }
                }
            }
        }

        long max = Long.MIN_VALUE;
        for(int k = 0; k < maxSum.length; k++)  {
            if(max < maxSum[k]) {
                max = maxSum[k];
            }
        }

        return max;
    }

    private static long findMaxWeightSubsequenceV3(Pair[] input)  {
        if((input == null) || (input.length == 0))  {
            return 0;
        }

        // array in which ith entry stores max sum uptil the ith element
        int N = input.length;
        long[] maxSum = new long[N];
        long[] maxKey = new long[N];
        long maxKeyValue = input[0].key;
        int[] rangeStart = new int[N];

        for(int i = 0; i < N; i++)   {
            maxSum[i] = input[i].weight;

            if((i > 0) && (input[i].key > input[i-1].key)) {
                rangeStart[i] = rangeStart[i-1];
                if(maxKeyValue < input[i].key)  {
                    maxKeyValue = input[i].key;
                }

            }else   {
                rangeStart[i] = i;
                maxKeyValue = input[i].key;
            }

            maxKey[i] = maxKeyValue;

            for(int j = i-1; j >=0; j--)    {
                if(input[i].key > input[j].key)   {
                    long temp = maxSum[j] + input[i].weight;
                    if(maxSum[i] < temp)    {
                        maxSum[i] = temp;
                    }

                    if(input[j].key == maxKey[j]) {
                        j = rangeStart[j] - 1;
                    }
                }
            }
        }

        long max = Long.MIN_VALUE;
        for(int k = 0; k < maxSum.length; k++)  {
            if(max < maxSum[k]) {
                max = maxSum[k];
            }
        }

        return max;
    }

    static class Pair  {
        long key;
        long weight;

        public Pair(long key, long weight)   {
            this.key = key;
            this.weight = weight;
        }
    }
}
