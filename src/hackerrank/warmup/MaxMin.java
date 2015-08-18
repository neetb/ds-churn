package hackerrank.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by abhinav on 17/5/15.
 */
public class MaxMin {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int K = Integer.parseInt(in.readLine());
        int[] list = new int[N];

        for (int i = 0; i < N; i++)
            list[i] = Integer.parseInt(in.readLine());

        int unfairness = result(list, K);

        System.out.println(unfairness);
    }

    private static int result(int[] input, int K)    {
        Arrays.sort(input);
        int result = Integer.MAX_VALUE;
        for(int i = 0; i <= input.length - K; i++)   {
            int diff = input[i + K - 1] - input[i];
            if(diff < result)    {
                result = diff;
            }
        }
        return result;
    }
}
