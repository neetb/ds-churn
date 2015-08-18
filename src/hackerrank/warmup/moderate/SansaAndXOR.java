package hackerrank.warmup.moderate;

import java.util.Scanner;

/**
 * Created by ramneet on 19/6/15.
 */
public class SansaAndXOR {

    public static int xor(String[] input)    {
       int N = input.length;
       int a = Integer.parseInt(input[0]);
       int b = Integer.parseInt(input[N-1]);

       if(N % 2 == 0)   {
           return 0;
       } else   {
           return a ^ b;
       }
    }

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < T; i++)  {
            int N = Integer.parseInt(sc.nextLine());
            String[] input = sc.nextLine().trim().split(" ");
            System.out.println(xor(input));
        }
    }
}
