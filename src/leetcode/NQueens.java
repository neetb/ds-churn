package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav on 10/8/15.
 */
public class NQueens {

    public static void main(String[] args)  {
        NQueens test = new NQueens();
        test.solveNQueens(4);
    }

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        //init(board);
        List<List<String>> result = new ArrayList<List<String>>();

        if(n <= 0)  {
            return result;
        }

        solve(board, 0, result);
        return result;

    }

    //private void init()

    private void solve(char[][] board, int rowIndex, List<List<String>> result)    {
        int n = board.length;
        if(rowIndex >= board.length)    {
            addToList(result, board);
            board = new char[n][n];
            return;
        }
/*

        for(int i = rowIndex; i < n; i++)  {
            for(int j = 0; j < n; j++)  {
                if(isLegal(board, i, j))   {
                    board[i][j] = 'Q';
                    solve(board, i+1, result);
                    board[i][j] = '.';
                }
            }
        }
*/

        for(int j = 0; j < n; j++)  {
            if(isLegal(board, rowIndex, j))   {
                board[rowIndex][j] = 'Q';
                solve(board, rowIndex + 1, result);
                board[rowIndex][j] = '.';
            }
        }
    }

    private boolean isLegal(char[][] board, int i, int j) {
      int temp_i = i;
      int temp_j = j;
      int N = board.length;

        while(temp_i >=0) {
          if ((temp_i >= 1) && (board[temp_i - 1][j] == 'Q')) {
              return false;
          }
          temp_i--;
      }

      temp_i = i;

      while((temp_i >=0) && (temp_j>=0)) {
          if ((temp_i >= 1) && (temp_j >= 1) && (board[temp_i - 1][temp_j - 1] == 'Q')) {
              return false;
          }
          temp_i--;
          temp_j--;
      }

        temp_i = i;
        temp_j = j;

        while((temp_i >=0) && (temp_j< N)) {
            if ((temp_i >= 1) && (temp_j < N - 1) && (board[temp_i - 1][temp_j + 1] == 'Q')) {
                return false;
            }
            temp_i--;
            temp_j++;

        }


        return true;
    }

    private void addToList(List<List<String>> result, char[][] board)   {
        List<String> current  = new ArrayList<String>();
        for(int i = 0; i < board.length; i++)   {
            StringBuilder text = new StringBuilder();
            for(int j = 0; j < board.length; j++)   {
                if(board[i][j] != 'Q')   {
                    text.append('.');
                }else   {
                    text.append(board[i][j]);
                }

            }
            current.add(text.toString());
        }
        result.add(current);
    }
}
