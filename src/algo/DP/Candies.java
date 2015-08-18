package algo.DP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by abhinav on 26/6/15.
 */
public class Candies {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine().trim());
        int[] input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(sc.nextLine().trim());
        }
        int result = 0;
        int[] count = new int[input.length];
        System.out.println(minCandiesV2(input));
    }


    public static int minCandiesV2(int[] input) {
        int[] count = new int[input.length];
        int result = 0;
        for (int i = 0; i < count.length; i++) {
            count[i] = 1;
        }
        minCandiesUtilV2(input, 0, 100000, count);
        for (int i = 0; i < count.length; i++) {
            result += count[i];
        }
        return result;
    }


    public static Result minCandiesUtilV2(int[] input, int i, int prevValue, int[] count) {
        if (i >= input.length) {
            return new Result(i, prevValue);
        }

        if ((i > 0) && (input[i] > input[i - 1])) {
            count[i] = prevValue + 1;
            return minCandiesUtilV2(input, i + 1, count[i], count);
         }


        int k = 1;


        if (((i > 0) && (input[i] <= input[i - 1])) || (i == 0)) {
            for (k = 1; k < prevValue; k++) {
                count[i] = k;
                //prevValue = k;
                Result res = minCandiesUtilV2(input, i + 1, k, count);
                int index = res.index;
                if(index == input.length)   {
                    return  new Result(index, prevValue);
                }

                //prevValue = res.prevValue;
            }

        }

        return  new Result(i, prevValue);
    }

    public static int minCandies(int[] input) {
        int[] count = new int[input.    length];
        int result = 0;
        for (int i = 0; i < count.length; i++) {
            count[i] = 1;
        }
        minCandiesUtil(input, count, 0, input.length - 1);
        for (int i = 0; i < count.length; i++) {
            result += count[i];
        }
        return result;
    }

    private static void minCandiesUtil(int[] input, int[] count, int lo, int hi) {
        int N = input.length;

        if (lo > hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        if (((mid + 1) < N) && (input[mid] > input[mid + 1])) {
            count[mid] = count[mid + 1] + 1;
        }

        if (((mid - 1) >= 0) && (input[mid] > input[mid - 1])) {
            count[mid] += count[mid - 1] + 1;
        }

        minCandiesUtil(input, count, lo, mid - 1);
        minCandiesUtil(input, count, mid + 1, hi);

    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    static class Result    {
        int index;
        int prevValue;

        public Result(int index, int prevValue) {
           this.index = index;
            this.prevValue = prevValue;
        }
    }
}
