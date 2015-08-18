package algo.DP;

/**
 * Created by ramneet on 18/6/15.
 */
public class Knapsack01 {

    /*int val[] = {60, 100, 120};
    int wt[] = {10, 20, 30};
    int  W = 50;*/
    public int knapsack(int[] val, int[] wt, int w) {
        int[] temp = new int[w + 1];
        int[] wts = new int[w+1];
        temp[0] = 0;
        wts[0] = 0;

        for (int i = 1; i <= w; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < wt.length; j++) {
                int weight = wt[j];
                int split = 0;
                int value = 0;
                if (weight > i) {
                    value = temp[i - 1];
                    split = wts[i-1];
                } else {
                    value = val[j] + temp[i - weight];
                    split = j;
                }

                if (value > max) {
                    max = value;
                    wts[i] = split;
                }
            }
            temp[i] = max;
        }

        System.out.println("**spliut:" +wts[w]);
        return temp[w];
    }

    public static void main(String[] args) {
        Knapsack01 solver = new Knapsack01();

       /* int val[] = {10, 100, 300};
        int wt[] = {10, 20, 30};
        int W = 50;
        System.out.println(solver.knapsack(val, wt, W));
        System.out.println("Max value :: " + solver.knapsackv2(val, wt, W));
*/
        solver.cutRod(new int[]{1,2,3,4,5,6,7,8}, new int[]{1, 5, 8, 9, 10, 17, 17, 20}, 8);

    }

    private int knapsackv2(int[] val, int[] wt, int W) {
        int N = val.length;
        int[] sol = new int[W+1];
        sol[0] = 0;
        int[] split = new int[W+1];
        split[0] = 0;
        for(int i = 1; i <= W; i++) {
            int max = Integer.MIN_VALUE;
            int temp = 0;
            int lastSplit = 0;

            for(int j=0; j < N; j++)    {
                if(wt[j] > i)   {
                    temp = sol[i-1];
                    lastSplit = split[i-1];

                }else   {
                    temp = sol[i - wt[j]] + val[j];
                    lastSplit = j;
                }
                if(max <= temp)  {
                    max = temp;
                    split[i] = lastSplit;
                }


            }
            sol[i] = max;
        }

        int k = W;
        int splitValue = split[k];
        System.out.println("Following are the splits : ");
        while(k > 0)    {
            splitValue = split[k];
            System.out.print(wt[splitValue] + " , ");
            k = k - wt[splitValue];
        }

        return sol[W];
    }

    private void cutRod(int[] lens, int[] price, int length)    {

        int[] sol = new int[length+1];
        sol[0] = 0;
        for(int i = 1; i <= length; i++)    {
            int maxValue = Integer.MIN_VALUE;
            for(int j = 0; j < lens.length; j++) {
                if(lens[j] <= i)    {
                   maxValue = max(maxValue, sol[i-lens[j]] + price[j]);
                }
            }
            sol[i] = maxValue;
        }

        System.out.println("Max value :: " + sol[length]);
    }

    private int max (int a, int b)  {
        return a > b ? a : b;
    }


}
