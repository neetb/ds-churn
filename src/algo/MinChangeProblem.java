package algo;

/**
 * Created by abhinav on 13/6/15.
 */
public class MinChangeProblem {

    public int findMinNoOfCoins(int S, int[] coins) {
        int[] min = new int[S + 1];
        min[0] = 0;
        for (int i = 1; i <= S; i++) {
            min[i] = Integer.MAX_VALUE;
        }

        //calculate min no. of coins for i starting from 1 till S
        for (int i = 1; i <= S; i++) {
            // check for each denomination
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    min[i] = min(min[i], 1 + min[i - coins[j]]);
                }
            }
        }
        return min[S];
    }

    public int findNoOfWays(int S, int[] coins) {
   /*     if(S == 0)
            return 0;

        for(int k = 0; k < )
*/
        return 0;
    }

   /* public int longestIncreasingSubSeq(int[] input) {
        int[] len = new int[input.length];
        int max = Integer.MIN_VALUE;
        for(int  i = 0; i < input.length; i++)   {
            len[i] = 1;
        }

        for(int j = 1; j < input.length; j++)   {
            if(input[j] >= input[j-1])  {
               //len[j] = max(1 + len[j-1], len[j]);
                len[j] = 1+len[j-1];
                if(max < len[j]) {
                   max = len[j];
               }
            }
        }
        return max;
    }*/


    private int min(int a, int b) {
        return a < b ? a : b;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        MinChangeProblem p = new MinChangeProblem();
        System.out.println(p.findMinNoOfCoins(7, new int[]{1, 3, 5, 6}));
        System.out.println(p.longestIncreasingSubSeq(new int[]{3, 4, 8, 6, 7, 9}));
    }

    public int longestIncreasingSubSeq(int[] input) {
        int[] sol = new int[input.length];
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < input.length; i++) {
            sol[i] = 1;
        }

        for (int i = 1; i < input.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (input[i] >= input[j]) {
                    //len[j] = max(1 + len[j-1], len[j]);
                    sol[i] = max(sol[j] + 1, sol[i]);
                    if (sol[i] > min) {
                        min = sol[i];
                    }
                }
            }
        }
        return min;
    }
}
