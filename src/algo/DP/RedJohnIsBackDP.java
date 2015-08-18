package algo.DP;

import java.util.Scanner;

/**
 * Created by abhinav on 26/6/15.
 */
public class RedJohnIsBackDP {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        int[] input = new int[T];
        for (int i = 0; i < T; i++) {
            input[i] = Integer.parseInt(sc.nextLine());
        }
        //System.out.println(findPrimeCount(findTotalWays(1)));
        for (int i = 0; i < T; i++) {
            System.out.println(findPrimeCount(findTotalWays(input[i])));
        }
    }

    public static int findPrimeCount(int M)   {
        int primeCount = 0;
        if(M < 1)   {
            return 0;
        }

        for(int i = 2; i <=M; i++)   {
            if(isPrime(i))    {
                primeCount++;
            }
        }

        return primeCount;
    }

    private static boolean isPrime(int num) {
        if(num < 1)   {
            return false;
        }

        for(int i = 2; i <= Math.sqrt(num); i++)   {
            if((num % i) == 0)    {
                return false;
            }
        }

        return true;
    }

    public static int findTotalWays(int N)    {
        //ith element of the array stores ways to cover 4 * i area of the wall
        int[] ways = new int[N + 1];
        ways[0] = 0;

        for(int i = 1; i <=N; i++)  {
            if(i < 4)   {
                ways[i] = 1;
                continue;
            }
            if(i == 4)  {
                ways[i] = 2;
                continue;
            }

            ways[i] = ways[i-1] + ways[i-4];
        }

        return ways[N];
    }
}
