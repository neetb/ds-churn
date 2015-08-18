package hackerrank.warmup.hackerrank.easy;

import java.util.Scanner;

/**
 * Created by abhinav on 19/6/15.
 */
public class LonelyInteger {

    public static int find(String[] input)    {
        int N = input.length;
        int res  = 0;
        for(int i = 0; i < N; i++)  {
            res = res ^ (Integer.parseInt(input[i]));
        }
        return res;
    }

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        String[] input = sc.nextLine().trim().split(" ");
        System.out.println(find(input));
    }
}
