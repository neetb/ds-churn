package hackerrank.warmup.moderate;

import java.util.Scanner;

/**
 * Created by abhinav on 17/5/15.
 */
public class MaxIndexProduct {


    public static void main(String[] args) {
  /*       Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] input = new int[N];
        for(int i = 0; i < N; i++)  {
           input[i] = sc.nextInt();
        }
        System.out.println(maxIndexProduct(input));
*/
        System.out.println(maxProduct(new int[]{3, -1, -4}));
    }

    private static long maxIndexProduct(int[] input)    {
        int N = input.length;
        //array storing indexes of max left/right numbers
        int[] leftMax = new int[N];
        int[] rightMax = new int[N];

        populateLeftMax(leftMax, input);
        populateRightMax(rightMax, input);

        long maxProd = Long.MIN_VALUE;
        for(int i = 0; i < N; i++)  {

            long prod = (long)(leftMax[i] + 1) * (rightMax[i] + 1);
            if(prod > maxProd)  {
                maxProd = prod;
            }
        }

        return maxProd;

    }

    private static void populateLeftMax(int[] leftMax, int[] input)    {
        int N = input.length;

        for(int i = 0; i < N; i++) {
            if(i == 0){
                leftMax[i] = -1;
            }else   {
                if(input[i-1] > input[i])   {
                    leftMax[i] = i-1;
                }else   {
                    int possibleMax = leftMax[i-1];
                    while(true) {
                        if(possibleMax == -1)   {
                            leftMax[i] = -1;
                            break;
                        }

                        if(input[possibleMax] > input[i]){
                            leftMax[i] = possibleMax;
                            break;
                        }

                        possibleMax = leftMax[possibleMax];
                    }
                }
            }

        }
    }

    private static void populateRightMax(int[] rightMax, int[] input)    {
        int N = input.length;

        for(int i = N-1; i >= 0; i--) {
            if(i == N-1){
                rightMax[i] = -1;
            }else   {
                if(input[i+1] > input[i])   {
                    rightMax[i] = i+1;
                }else   {
                    int possibleMax = rightMax[i+1];
                    while(true) {
                        if(possibleMax == -1)   {
                            rightMax[i] = -1;
                            break;
                        }

                        if(input[possibleMax] > input[i]){
                            rightMax[i] = possibleMax;
                            break;
                        }

                        possibleMax = rightMax[possibleMax];
                    }
                }
            }

        }

    }

    public static int maxProduct(int[] A) {
        if(A==null || A.length==0)
            return 0;

        int maxLocal = A[0];
        int minLocal = A[0];
        int global = A[0];

        for(int i=1; i<A.length; i++){
            int temp = maxLocal;
            maxLocal = Math.max(Math.max(A[i]*maxLocal, A[i]), A[i]*minLocal);
            minLocal = Math.min(Math.min(A[i]*temp, A[i]), A[i]*minLocal);
            global = Math.max(global, maxLocal);
        }
        return global;
    }

}
