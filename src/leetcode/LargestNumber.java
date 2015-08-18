package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * Created by abhinav on 16/8/15.
 */
public class LargestNumber {

    public static void main(String[] args)  {
        LargestNumber test = new LargestNumber();
        System.out.println(test.largestNumber(new int[]{824,938,1399,5607,6973,5703,9609,4398,8247}));
    }
    public String largestNumber(int[] nums) {
        Integer[] input = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++)    {
            input[i] = nums[i];
        }
        return largestNumberHelper(input);
    }

        public String largestNumberHelper(Integer[] nums) {
        StringBuilder result = new StringBuilder();
        if((nums == null) || (nums.length == 0))    {
            return result.toString();
        }


        Arrays.sort(nums, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int a = (Integer) o1;
                int b = (Integer) o2;
                int aMSD = 0;
                int bMSD = 0;
                Stack<Integer> aStack = new Stack<Integer>();
                Stack<Integer> bStack = new Stack<Integer>();

                while (a > 0) {
                    aMSD = a % 10;
                    aStack.push(aMSD);
                    a = a / 10;
                }

                while (b > 0) {
                    bMSD = b % 10;
                    bStack.push(bMSD);
                    b = b / 10;
                }

                int topA = -1;
                int topB = -1;
                if (aMSD == bMSD) {
                    while ((!aStack.isEmpty()) && (!bStack.isEmpty())) {
                        topA = aStack.pop();
                        topB = bStack.pop();

                        if (topA != topB) {
                            return topA > topB ? -1 : 1;
                        }
                    }

                    if ((!aStack.isEmpty()) && (!bStack.isEmpty())) {
                        return 0;
                    } else {
                        while (!aStack.isEmpty()) {
                            int temp = aStack.pop();
                            if (temp != bMSD) {
                                return temp < bMSD ? 1 : -1;
                            }
                        }

                        while (!bStack.isEmpty()) {
                            int temp = bStack.pop();
                            if (temp != aMSD) {
                                return temp < aMSD ? -1 : 1;
                            }
                        }

                        return 0;
                    }

                } else if (aMSD < bMSD) {
                    return 1;
                } else {
                    return -1;
                }
            }

        });

        for(int i = 0; i < nums.length; i++)    {
            result.append(nums[i]);
           // System.out.println(nums[i]);
        }

        return result.toString();
    }
}
