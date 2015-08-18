package codeeval.codeEval;

/**
 * Created by ramneet on 29/4/15.
 */
public class SumOf1000Primes {


    public  static void main(String[] args) {
        SumOf1000Primes soln = new SumOf1000Primes();
        soln.find();
    }

    private void find() {
        int count = 0;
        long sum = 0;
        int num = 2;

        while (true) {
            if (count == 1000)
                break;

            if (isPrime(num)) {
                count++;
                sum = sum + num;
            }
            num++;
        }

        System.out.println(sum);
    }


    private boolean isPrime(long num)    {
        for (int i=2; i<=Math.sqrt(num); i++)    {
            if(num % i == 0)
                return false;
        }

        return true;
    }
}
