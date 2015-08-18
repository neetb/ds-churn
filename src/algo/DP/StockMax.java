package algo.DP;

import java.util.List;
import java.util.Scanner;

/**
 * Created by ramneet on 26/6/15.
 */
public class StockMax {

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        List[] input = new List[T];
        for(int i = 0; i < T;i++)   {
            int N = Integer.parseInt(sc.nextLine());
            String[] text = sc.nextLine().trim().split(" ");
            int[] nums = new int[N];
            for(int j = 0; j < N; j++)  {
               nums[j] = Integer.parseInt(text[j]);
            }
            System.out.println(findMaxProfit(nums));
        }
    }

    public static long findMaxProfit(int[] input)    {
        long[] count = new long[input.length];
        long result = 0;
        for(int i = 0; i < input.length; i++)   {
            count[i] = 0;
        }
        maxProfit(input, count);

        for(int i = 0; i < count.length; i++)   {
            result += count[i];
        }

        return result;
    }
    public static void maxProfit(int[] input, long[] count)   {
        int N = input.length;
        count[N-1] = 0;
        int max = input[N-1];

        for(int i = N-2; i >=0; i--)    {
           if(input[i] < max) {
               count[i] = max - input[i];
           }else    {
               count[i] = 0;
               max = input[i];
           }
        }
    }
}
