package hackerrank.warmup.hackerrank.easy;

import java.util.Scanner;

/**
 * Created by abhinav on 27/5/15.
 */
public class GameOfThronesI {


    public static void main(String[] args)  {
     /*   Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        boolean result = find(input);
        System.out.println(result ? "YES" : "NO");*/

        Scanner myScan = new Scanner(System.in);
        String inputString = myScan.nextLine();

        String ans;
        // Assign ans a value of YES or NO, depending on whether or not inputString satisfies the required condition
        System.out.println(find(inputString));
        myScan.close();
    }

    private static String find(String input)  {
        int len = input.length();
        char[] chArr = input.toCharArray();
        int[] countArr = new int[26];
        int oneAllowed = 0;

        for(int i = 0; i < len; i++)  {
            countArr[chArr[i] - 'a']++;
        }

        if((len % 2) != 0)  {
            oneAllowed = 1;
        }

        for(int i = 0; i < countArr.length; i++)  {
            if((countArr[i] % 2) != 0) {
                if(oneAllowed == 0) {
                  return "NO";
                }else   {
                   oneAllowed--;
                }
            }
        }
        return "YES";
    }
}
