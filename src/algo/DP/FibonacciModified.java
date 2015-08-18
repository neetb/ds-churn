package algo.DP;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by ramneet on 19/6/15.
 */
public class FibonacciModified {

    public static void main(String[] args)  {
        Scanner sc =  new Scanner(System.in);
        String[] inputs = sc.nextLine().trim().split(" ");
        int a  = Integer.parseInt(inputs[0]);
        int b = Integer.parseInt(inputs[1]);
        int N  =Integer.parseInt(inputs[2]);
        System.out.println(find(a, b, N));
    }

    public static BigInteger find(int a, int b, int N) {
        BigInteger[] temp = new BigInteger[N];
        temp[0] = BigInteger.valueOf(a);
        temp[1] = BigInteger.valueOf(b);

        for(int i = 2; i < N; i++)    {
            temp[i] = (temp[i-1].pow(2)).add(temp[i-2]);
        }
      return temp[N-1];
    }
}
