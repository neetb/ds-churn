package hackerrank.warmup;

import java.util.Scanner;

/**
 * Created by abhinav on 17/5/15.
 */
public class TimeInWords {


    public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);
        int h = Integer.parseInt(sc.nextLine().trim());
        int m = Integer.parseInt(sc.nextLine().trim());
       System.out.println(describe(h,m));
    }

    private static String describe(int h, int m)   {
        StringBuilder timeInWords = new StringBuilder();
        if(m < 30) {
            if(m == 0)  {
                timeInWords.append(h).append(" o' clock");
            } else if (m == 1)
               timeInWords.append(m).append(" min past ").append(h);
            else
                timeInWords.append(m).append(" mins past ").append(h);

        }else if(m > 30)    {
            int diff = 60 - m;
            if(diff == 1)
                timeInWords.append(diff).append(" min to ").append(h+1);
            else
                timeInWords.append(diff).append(" mins to").append(h+1);

        }else   {
            timeInWords.append("half past ").append(h);
        }

        return timeInWords.toString();
    }
}
