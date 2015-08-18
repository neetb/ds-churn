package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav on 4/8/15.
 */
public class SpiralMatrix {

    public static void main(String[] args)  {
        SpiralMatrix test = new SpiralMatrix();
        test.spiralOrder(new int[][]{{3}, {2}});
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if((matrix == null) || (matrix.length == 0) || (matrix[0].length == 0))    {
            return result;
        }

        if(matrix.length == 1 ) {
            for(int i = 0; i < matrix[0].length; i++)   {
                result.add(matrix[0][i]);
            }
            return result;
        }


        int m = matrix.length;
        int n = matrix[0].length;
        int start_i = 0;
        int start_j = 0;
        int end_i = m-1;
        int end_j = n-1;

        while((end_i >= 0) && (end_j >= 0) && (start_i <= end_i) && (start_j <= end_j)) {
            int j = start_j;
            //print first horizontal
            for(j = start_j; j <= end_j; j++)  {
                result.add(matrix[start_i][j]);
            }

            //print right vertical
            for(int k = start_i+1; k <= end_i; k++)    {
                result.add(matrix[k][end_j]);
            }

            if(start_i != end_i)    {
                //print bottom horizontal
                for(int p = end_j-1; p >= start_j; p--)    {
                    result.add(matrix[end_i][p]);
                }
            }

            if(start_j != end_j)    {
                //print left vertical
                for(int q = end_i-1; q > start_i; q--)    {
                    result.add(matrix[q][start_j]);
                }
            }

            end_i--;
            end_j--;
            start_i++;
            start_j++;
        }
        return result;
    }


}
