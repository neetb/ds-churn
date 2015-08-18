package hackerrank.warmup.moderate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ramneet on 16/5/15.
 */
public class MaximizeSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCaseCount = Integer.parseInt(sc.nextLine());
        List<BigInteger>[] inputs =  (List<BigInteger>[])new List[testCaseCount];
        List<Long> Ms = new ArrayList<Long>();

        for(int i = 0;i < testCaseCount; i++)   {
            long N = sc.nextLong();
            long M = sc.nextLong();
            Ms.add(M);
            sc.nextLine();
            inputs[i] = new ArrayList<BigInteger>();
            for(int j = 0;j < N; j++)   {
                     inputs[i].add(sc.nextBigInteger());
            }

            sc.nextLine();
        }


        int i = 0;
        for(long M : Ms) {
            System.out.println(maxSum(inputs[i], inputs[i].size(), M));
            i++;
        }

    }


    private static BigInteger maxSum(List<BigInteger> inputList, int N, long M)  {
        BigInteger max = BigInteger.ZERO;
        List<BigInteger> prevValues = new ArrayList<BigInteger>();

        if((inputList == null) || (inputList.size() == 0))  {
            return max;
        }

        prevValues.add(inputList.get(0));

        for(BigInteger input : inputList)  {
            List<BigInteger> values = new ArrayList<BigInteger>();

            for(BigInteger prevVal : prevValues)    {
                BigInteger sum = prevVal.add(input);
                BigInteger rem = sum.remainder(BigInteger.valueOf(M));
                if( rem != BigInteger.ZERO)   {
                    values.add(sum);
                }

                if(rem == BigInteger.valueOf(M).subtract(BigInteger.ONE))   {
                    max = rem;
                    break;
                }

                if(rem.compareTo(max) == 1)   {
                    max = rem;
                }

            }

            values.add(input);
            prevValues = null;
            prevValues = values;
        }

        return max;
    }
}
