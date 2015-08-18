package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by abhinav on 5/8/15.
 */
public class WordBreak {
    public static void main(String[] args)  {
        WordBreak test = new WordBreak();
        Set<String> temp = new HashSet<String>();
        temp.add("a");
        temp.add("b");
        System.out.println(test.wordBreak("ab", temp));
    }
    public boolean wordBreak(String s, Set<String> wordDict) {
        if((s == null) || (s.length() == 0))    {
            return false;
        }

        int N = s.length();
        if(N == 1) {
            return wordDict.contains(s);
        }

        boolean[][] temp = new boolean[N][N];
        for(int i = N-1; i >=0; i--) {
            for(int j = i; j < N; j++)   {
                if(i == j)  {
                    temp[i][j] = wordDict.contains(s.substring(i,i+1));
                }else   {
                    boolean val = false;
                    for(int k = i; k < j; k++) {
                        val = val || (temp[i][k] && temp[k+1][j]);
                    }
                    temp[i][j] = val || wordDict.contains(s.substring(i, j+1));
                }
            }
        }

        return temp[0][N-1];
    }
}
