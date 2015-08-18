package leetcode;

/**
 * Created by abhinav on 23/7/15.
 */
public class NumbersPractise {

    public static void main(String[] args)  {
        NumbersPractise test = new NumbersPractise();
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE * -1);
        //System.out.println(test.divideOthers1(29, 3));
        //System.out.println(test.divide(20, 6));
    }



    public int divideOthers(int dividend, int divisor) {
       /* long p = Math.abs((long)dividend);
        long q = Math.abs((long)divisor);*/

        int p = Math.abs(dividend);
        int q = Math.abs(divisor);

        int ret = 0;
        while (p >= q) {
            int counter = 0;
            while (p >= (q << counter)) {
                counter++;
            }
            ret += 1 << (counter - 1);
            p -= q << (counter - 1);
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0))
            return ret;
        else
            return -ret;
    }

    public int divide(int dividend, int divisor) {
        if(divisor == 0) {
            return Integer.MAX_VALUE;
        }

        if(divisor == 1) {
            return dividend;
        }

        if(divisor > dividend)  {
            return 0;
        }

        int result = 0;
        int power = powerOf2(Math.abs(divisor));
        if(power!= -1) {
            result =  dividend >> power;
        } else {

            int temp = Math.abs(divisor);
            int count = 1;

            while ((temp << 1) < dividend) {
                temp = temp << 1;
                count = count << 1;
            }

            while (temp <= dividend) {
                temp = temp + Math.abs(divisor);
                count++;
            }

            count--;
            result = count;
        }
        if(divisor < 0) {
            return -(result);
        }else   {
            return result;
        }

    }

    public int divideV1(int dividend, int divisor) {
            if(divisor == 0) {
            return Integer.MAX_VALUE;
        }

        if(divisor == 1) {
            return dividend;
        }

        if(divisor > dividend)  {
            return 0;
        }

        int power = powerOf2(Math.abs(divisor));
        int result = 0;
        if(power!= -1) {
            result =  dividend >> power;
        } else {
            int start = 0;
            result = 0;
            int count = 0;
            while (start < dividend) {
                count++;
                result++;
                start = (int)Math.pow(Math.abs(divisor), count);

            }

            if(start == dividend)   {
                return count;
            }

            count = count - 1;
            int begin = (int)Math.pow(Math.abs(divisor), count);
            result = count;
            while(begin < dividend) {
                begin = begin + Math.abs(divisor);
                result++;
            }

            //result--;
        }
        if(divisor < 0)
            return -1 * result;
        else
            return result;
    }

    private int powerOf2(int n) {
        int mask = n - 1;
        if((n & mask) == 0)   {
            int power = 0;
            while(n != 1)    {
                n = n >> 1;
                power++;
            }
            return power;
        }else   {
            return -1;
        }
    }

}
