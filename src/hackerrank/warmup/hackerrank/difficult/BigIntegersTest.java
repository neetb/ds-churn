package hackerrank.warmup.hackerrank.difficult;

import java.math.BigInteger;

/**
 * Created by abhinav on 20/5/15.
 */
public class BigIntegersTest {

    public static void main(String[] args) {
        /*U 41224 41224 999999999914801692 999999999966033236
        R 99656 99656 45
        U 16404 16404 999999999036359997 999999999991689605
        R 5173 5173 46
        U 83426 83426 999999999915033740 999999999940950668
        R 21900 21900 47
        U 1248 1248 999999999996432762 999999999287344475
        R 65605 65605 48
        U 11703 11703 999999999344709746 999999999821910808
        R 31733 31733 49
        U 64020 64020 999999999552147822 999999999974197228
        R 67200 67200 50
        U 20172 20172 999999999639817320 999999999665188324
        R 74146 74146 51*/
       /* String arg1 = "999999999914801692";
        String arg2 = "999999999966033236";

        String arg1 = "99";
        String arg2 = "99";

        BigInteger i1 = new BigInteger(arg1);
        BigInteger i2 = new BigInteger(arg2);
        BigInteger res  = expo(i1,i2);
        System.out.println(res);
        */
         long n = 100000;
        System.out.println( n * (n - 1) / 2);
    }

/*
    Function exp-by-squaring(x,n)
    if n<0 then return exp-by-squaring(1/x, -n);
    else if n=0 then return 1;
    else if n=1 then return x;
    else if n is even then return exp-by-squaring(x2, n/2);
    else if n is odd then return x * exp-by-squaring(x2, (n-1)/2).
*/

    private static BigInteger expo(BigInteger x, BigInteger n) {
       if(n.intValue() == 0)
           return BigInteger.ONE;
        else if (n.intValue() == 1)
           return x;
        else if(n.mod(BigInteger.valueOf(2)) == BigInteger.ZERO)  {
             return expo(x.pow(2), n.divide(BigInteger.valueOf(2)));
       }else    {
             return x.multiply(expo(x.pow(2), (n.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2))));
       }
    }

}
