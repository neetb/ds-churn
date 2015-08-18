package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav on 12/8/15.
 */
public class PalindromePartitioning {

    public static void main(String[] args)  {
        PalindromePartitioning test = new PalindromePartitioning();
        //test.partition("a");cabababcbc
        System.out.println(test.minCut("aabba"));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> current = new ArrayList<String>();
        helper(s, s, result, current);
        return result;

    }

    public int minCut(String s) {
        int n = s.length();

        boolean dp[][] = new boolean[n][n];
        int cut[] = new int[n];

        for (int j = 0; j < n; j++) {
            cut[j] = j; //set maximum # of cut
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i+1][j-1])) {
                    dp[i][j] = true;

                    // if need to cut, add 1 to the previous cut[i-1]
                    if (i > 0){
                        cut[j] = Math.min(cut[j], cut[i-1] + 1);
                    }else{
                        // if [0...j] is palindrome, no need to cut
                        cut[j] = 0;
                    }
                }
            }
        }

        return cut[n-1];
    }


    public int minCut1(String s) {
        int N = s.length();
        int[][] minCuts = new int[N][N];


        for(int i = N-1; i >=0; i--)  {
            for(int j = i; j < N; j++)  {
                int diff = Math.abs(j - i);
                if(diff == 0)   {
                    minCuts[i][i] = 0;
                }else if (diff == 1)    {
                    if(s.charAt(i) == s.charAt(j))  {
                        minCuts[i][j] = 0;
                    }else   {
                        minCuts[i][j] = 1;
                    }
                }else   {
                    if(s.charAt(i) == s.charAt(j)) {
                        if((i < N-1) && (j > 0) && (minCuts[i+1][j-1] == 0))  {
                            minCuts[i][j] = 0;
                            continue;
                        }
                    }

                    int min = Integer.MAX_VALUE;
                    for(int pointer = i; pointer < j; pointer++) {
                        min = min(min, minCuts[i][pointer] + minCuts[pointer+1][j] + 1);
                    }
                    minCuts[i][j] = min;

                }
            }
        }

        return minCuts[0][N-1];
    }

    private int min(int a, int b)    {
        return a < b ? a : b;
    }



    private void helper(String s, String temp, List<List<String>> result, List<String> current)    {
        if((temp == null) || (temp.length() == 0))   {
            result.add(new ArrayList(current));
            return;
        }

        for(int i = 0; i < temp.length(); i++) {
            if(isPalindrome(temp.substring(0, i+1))) {
                //add current string
                current.add(temp.substring(i+1));
                helper(s, temp.substring(i+1), result, current);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s)  {
        if(s.length() == 1) {
            return true;
        }

        if(s.length() ==2)  {
            return (s.charAt(0) == s.charAt(1));
        }

        int i = 0;
        int j = s.length() - 1;

        while(i < j)    {
            if(s.charAt(i) != s.charAt(j))  {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
}
