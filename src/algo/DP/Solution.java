package algo.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by abhinav on 13/7/15.
 */
public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
       // System.out.println(s.solution(1001));
       // System.out.println(s.solution(1,3));
      /*  System.out.println(s.solution(3,28));
        System.out.println(s.test(3,28));
      */
        System.out.println(s.reverseMain("my name"));

    }

    public int solution(int N) {
        if (N < 0) {
            return 0;
        }

        //Array to store frequency count of every digit of N
        int[] digitCount = new int[10];
        //Total number of digits
        int totalDigits = 0;

        while (N > 0) {
            int digit = N % 10;
            digitCount[digit]++;
            totalDigits++;
            N = N / 10;
        }

        //Total number of combinations - for number n , with digits di..j with frequency fi..fj
        // number of combinations = n!/fi! fj!
        int result = factorial(totalDigits);
        for (int i = 0; i < digitCount.length; i++) {
            if (digitCount[i] > 1) {
                result = result / (factorial(digitCount[i]));
            }
        }

        //to ignore number combinations starting from 0
        if (digitCount[0] > 0) {
            int zeroNumberCnt = factorial(totalDigits - 1);
            //fix one zero at the most significant place
            digitCount[0]--;
            for (int i = 0; i < digitCount.length; i++) {
                if (digitCount[i] > 1) {
                    zeroNumberCnt = zeroNumberCnt / (factorial(digitCount[i]));
                }
            }
            // subtract count of all possible numbers starting with zero from total count.
            result = result - zeroNumberCnt;
        }

        return result;
    }

    private int factorial(int N) {
        int product = 1;
        for (int i = N; i >= 1; i--) {
            product = product * i;
        }
        return product;
    }


    public String solution(int A, int B) {
        if (A % B == 0) {
            return Integer.toString(A / B);
        } else {
            double res = (double) A / (double) B;
            String res1 = Double.toString(res);
            String[] parts = res1.split("\\.");
            //System.out.println(parts.length);
            return format(parts[0], parts[1]);
        }
    }

    private String format(String prefix, String suffix) {
        StringBuilder result = new StringBuilder();
        if(suffix.length() == 1)    {
            result.append(prefix).append(".").append(suffix);
            return result.toString();
        }

        if(containsRepeatedNumbers(suffix)) {
            result.append(prefix).append(".").append("(").append(suffix.charAt(0)).append(")");
            return result.toString();
        }

        String longestRepeatedSeq = findLRS(suffix);

        if((longestRepeatedSeq == null) || (longestRepeatedSeq.isEmpty()))  {
            result.append(prefix).append(".").append(suffix);
            return result.toString();
        } else  {
           int index = suffix.indexOf(longestRepeatedSeq);
           String Q = suffix.substring(0, index);
           result.append(prefix).append(".").append(Q).append("(").append(longestRepeatedSeq).append(")");
           return result.toString();
        }
    }

    private boolean containsRepeatedNumbers(String input)   {
        boolean result = true;

        if((input == null) || (input.length() == 0)){
            return false;
        }
        char ch = input.charAt(0);
        for(int i = 1; i < input.length(); i++)   {
            if(input.charAt(i) != ch)   {
                result = false;
                break;
            }
        }
        return result;
    }

    private String findLRS(String input)    {
        if((input == null) || (input.length() == 0)){
            return null;
        }

        int maxLen = Integer.MIN_VALUE;
        int N = input.length();
        int start_index = -1;
        String[] suffixArr = new String[N];
        for(int i = 0; i < N; i++)  {
            suffixArr[i] = input.substring(i);
        }

        Arrays.sort(suffixArr);

        for(int i = 0; i < N-1; i++)  {
            int len = findCommonSequenceLength(suffixArr[i], suffixArr[i+1]);
            if(len > maxLen)    {
                maxLen = len;
                start_index = i;
            }
        }

        return suffixArr[start_index].substring(0, maxLen);
    }

    private int findCommonSequenceLength(String a, String b)    {
        if((a == null) || (a.length()==0) || (b == null) || (b.length()==0))
            return 0;
        int count = 0;
        int end = a.length() < b.length() ? a.length() : b.length();

        for(int i = 0; i < end; i++) {
           if((a.charAt(i) == b.charAt(i)))   {
               if((i > 0) && (a.charAt(i) == a.charAt(0)))
                   break;
               else
                   count++;
           }else    {
               break;
           }
        }

        return count;
    }

    private String test(int A, int B)   {
        String P = A/B + "";
        StringBuilder Q = new StringBuilder(".");
        StringBuilder temp = new StringBuilder("");
        int repeatingStartIndex = -1;
        int repeatingEndIndex = -1;
        Map<Integer,Integer> posMap = new HashMap<Integer,Integer>();
        int pos = 0;
        A = A % B;
        if(A == 0)  {
            return P;
        }else {
            while(A > 0)    {
                int i = (A * 10)/B;
                if(posMap.containsKey(A))   {
                    repeatingStartIndex = posMap.get(A);
                    repeatingEndIndex = pos;

                    break;
                }
                posMap.put(A, pos++);
                temp.append(i);
                A = (A * 10) % B;
            }
        }

        if(repeatingStartIndex > 0)  {
             P.concat(Q.append(temp.substring(0, repeatingStartIndex)).append("(").append(temp.substring(repeatingStartIndex, repeatingEndIndex)).append(")").toString());
        }else   {
           // P.concat(Q.append(temp.substring(0, repeatingStartIndex)).append("(").append(temp.substring(repeatingStartIndex, repeatingEndIndex)).append(")").toString());

        }
             return P;
    }


    private String reverseMain(String text) {
        StringTokenizer tokenizer = new StringTokenizer(text);
        StringBuilder builder = new StringBuilder();
        while(tokenizer.hasMoreTokens())    {
            String temp = tokenizer.nextToken();
            builder.append(reverse(temp));
            builder.append(" ");
        }
        return reverse(builder.toString());
    }

    private String reverse(String input)    {
        char[] ch = input.toCharArray();
        int N = input.length();
        for(int i = 0; i < N/2;i++)    {
            char temp = ch[N-1-i];
            ch[N-1-i] = ch[i];
            ch[i] = temp;
        }
        String res =  new String(ch);
        System.out.println("rev string : " + res);
        return res;
    }
}
