package algo.DP;

/**
 * Created by ramneet on 17/6/15.
 */
public class LongestSequences {

    public static void main(String[] args)  {
        char[] X = "ABCDGH".toCharArray();
        char[] Y = "AEDFHR".toCharArray();
        System.out.println(lcs(X, Y));
        printPerm("", "abcd");
    }

    private static int lcs(char[] a, char[] b)   {
        int N = a.length;
        int M = b.length;

        int[][] lcs = new int[N+1][M+1];
        //we need lcs[N+1][M+1]

        for(int i = 0; i <=N; i++)  {
            lcs[i][0] = 0;
        }

        for(int i = 0; i <=M; i++)  {
            lcs[0][i] = 0;
        }

        for(int i = 1; i <=N; i++)  {
            for(int j = 1; j <= M; j++) {
                if(a[i-1] == b[j-1])    {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                } else  {
                    lcs[i][j] = max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }

        return lcs[N][M];
    }

    private static int max(int a , int b)   {
        return a > b ? a : b;
    }


    private static void printPerm(String prefix, String txt) {
        if((txt == null) || (txt.length() == 0))   {
            System.out.println(prefix);
            return;
        }else{
            for(int i = 0; i < txt.length(); i++)   {
                printPerm(prefix + txt.charAt(i), txt.substring(0, i) + txt.substring(i+1));
            }
        }
    }
}
