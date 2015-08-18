package leetcode;

/**
 * Created by abhinav on 22/7/15.
 */
public class LongestPalindromicSubString {
   public static void main(String[] args)   {
       LongestPalindromicSubString test = new LongestPalindromicSubString();
       int x = 1534236469;

       //System.out.println(test.longestPalindrome("aaaa"));
       //System.out.println(test.checkPalindromev2(1000021, 1));
       //System.out.println(test.isPalindromev1(1000021));
       //System.out.println(test.isPalindromev1(1200021));

   }

    public String longestPalindrome(String s) {
        if((s == null) || (s.length() == 0))    {
            return null;
        }

        int N = s.length();
        boolean[][] isPalindrome = new boolean[N][N];
        for(int i = 0; i < N; i++)  {
            isPalindrome[i][i] = true;
        }

        int maxLen = 0;
        int max_i = 0;
        int max_j = 0;

        for(int i = N-1; i >=0; i--)    {
            for(int j = N-1; j>i; j--)      {
                if((j - i) == 1)    {
                    //2 len substring
                    isPalindrome[i][j] = s.charAt(i) == s.charAt(j);
                }else   {
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindrome[i+1][j-1];
                }

                if(isPalindrome[i][j])  {
                    if(maxLen < (j-i + 1))  {
                        maxLen = j-i+1;
                        max_i = i;
                        max_j = j;
                    }
                }
            }
        }

        return s.substring(max_i, max_j+1);
    }

    public boolean checkPalindromev1(int x)    {
        if(x < 0)   {
            return false;
        }

        if(x < 10)  {
             return true;
        }

        if((x % 11) == 0){
            return true;
        }

        return checkPalindrome(Integer.toString(x));

    }


    public boolean checkPalindromev2(int x, int multiplier)    {
        if(x < 0)
            return false;

        if(x < 10)
            return true;

        int LSD = x % 10;
        int MSD = 0;

        int temp = x;
        while(temp/10 > 0) {
            multiplier *= 10;
            temp = temp/10;
            MSD = temp;
        }

        if(LSD != MSD)  {
            return false;
        }else   {
            int nextNum = (x - ((MSD * multiplier) + LSD))/10;
            return checkPalindromev2(nextNum, multiplier/100);
         }

    }

    public boolean checkPalindromev3(int x)    {
        if(x < 0)
            return false;

        if(x < 10)
            return true;

        int multiplier = 1;

        while(x/multiplier >= 10) {
            multiplier *= 10;
        }

        while(x != 0) {
            int LSD = x % 10;
            int MSD = x / multiplier;

            if (LSD != MSD) {
                return false;
            } else {
                x = (x % multiplier) / 10;
                multiplier = multiplier / 100;
            }
        }

        return true;

    }


    private boolean checkPalindrome(String x) {
           int N  = x.length();

           for(int i = 0; i < N/2; i++)   {
               if(x.charAt(i) != x.charAt(N-i-1))   {
                   return false;
               }
           }

        return true;
    }

    boolean isPalindrome(int x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            int l = x / div;
            int r = x % 10;
            if (l != r) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }



    public boolean isPalindromev1(int x)    {
        if(x < 0)
            return false;

        if(x < 10)
            return true;

        int multiplier = 1;
        while(x/multiplier >= 10) {
            multiplier *= 10;
        }

        while(x > 0) {
            int LSD = x % 10;
            int MSD = x/multiplier;

            if(LSD != MSD)  {
                return false;
            }else   {
                x = (x - ((MSD * multiplier) + LSD))/10;
                multiplier = multiplier/100;
            }
        }
        return true;
    }
}
