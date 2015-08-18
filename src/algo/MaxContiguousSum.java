package algo;

/**
 * Created by abhinav on 13/6/15.
 */
public class MaxContiguousSum {

    public static void maxSum(int[] input) {
        int N = input.length;
        //sum[i,j] will store sum of sequence i,j
        int[][] sum = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            sum[i][0] = input[i];
            for (int j = i + 1; j < N; j++) {
                sum[i][j] = sum[i][j - 1] + input[j];
            }
        }

        int max = 0;
        int x = -1;
        int y = -1;
        for (int i = 0; i < N; i++) {
           for (int j = 0; j < N; j++) {
                if(sum[i][j] > max) {
                   max = sum[i][j];
                   x = i;
                   y = j;
                }
            }
        }

        System.out.println("Max sum : " + max + " from sequence (i,j_ : (" + x +"," + y + ")");
    }

    public static void maxSum1(int[] input) {
        int N = input.length;
        int maxSum = Integer.MIN_VALUE;
        int runningSum = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < N; i++) {
            if(runningSum > 0)    {
              runningSum += input[i];
            }else   {
               runningSum = input[i];
               x = i;
               y = i;
            }

            if(runningSum > maxSum)   {
                maxSum = runningSum;
                y = i;
            }
        }

        System.out.println("Max sum : " + maxSum + " from sequence (i,j_ : (" + x +"," + y + ")");
    }


    public static void main(String[] args)  {
         maxSum(new int[]{10, -8, 5, 4, -3});
        maxSum1(new int[]{10, -8, 5, 4, -3});
        maxSubArray(new int[]{10, -8, 5, 4, -3});
    }


    public static int maxSubArray(int[] nums) {
        if((nums == null) || (nums.length == 0))    {
            return 0;
        }

        int runningSum = Integer.MIN_VALUE;
        int N = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        int running_start = -1;

        for(int i = 0; i < N; i++)  {
            if(runningSum < 0)  {
                runningSum = nums[i];
                running_start = i;
            }else   {
                runningSum += nums[i];
            }

            if(maxSum < runningSum) {
                start = running_start;
                end = i;
                maxSum = runningSum;
            }
        }

        System.out.println("Max sum : " + maxSum + " from sequence (i,j_ : (" + start +"," + end + ")");
        return maxSum;
    }
}
