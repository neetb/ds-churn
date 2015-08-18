package algo.DP;

/**
 * Created by ramneet on 17/6/15.
 */
public class MatrixChainMultiplication {

    private int[] input;
    private int N;

    public MatrixChainMultiplication(int[] input)   {
        this.input = input;
        this.N = input.length - 1;
    }

    public static void main(String[] args)  {
        int[] input1 = new int[]{10, 20, 30};
        MatrixChainMultiplication mul = new MatrixChainMultiplication(input1);
        System.out.println("No of minimum operation : "+mul.process());
    }
    public int process()    {
        int[][] temp = new int[N+1][N+1];

        //init unused rows with -1
        for(int i = 0; i <= N; i++)  {
            temp[0][i] = -1;
            temp[i][0] = -1;
        }

        int i = N;
        int j = N;

        while((i <=N) && (j <=N)) {
            int originalI = i;

            while(i >= 1) {
                if(i == j)  {
                    temp[i][j] = 0;
                }else   {
                    int min = Integer.MAX_VALUE;
                    for(int k = i; k < j; k++)  {
                       int val = temp[i][k] + temp[k+1][j] + rows(i) * cols(k) * cols(j);
                        if(val < min)   {
                            min = val;
                        }
                    }
                    temp[i][j] = min;
                }
                i--;
                j--;
            }

            j = N;
            i = originalI - 1;
        }

        return temp[1][N];
    }

    private int rows(int k) {
        if((k < 1) || (k > N))  {
            return -1;
        }

        return input[k-1];
    }


    private int cols(int k) {
        if((k < 1) || (k > N))  {
            return -1;
        }

        return input[k];
    }
}
