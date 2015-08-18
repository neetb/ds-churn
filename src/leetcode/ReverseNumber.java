package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by abhinav on 22/7/15.
 */
public class ReverseNumber {

    public static void main(String[] args) {
      /*  System.out.println(reverse(1534236469));
        int[] input = new int[]{0, -6, 0, -14, 2, 0, -9, 5, -9, -8, -7, 12, -4, 14, -6, 6, 0, 5, -2, 6, -7, 1, 10, -10, -5, 3, -2, -3, -13, -6, 1, -6, 3, 9, -5, 12, -6, -7, 2, 0, 1, 11, -11, 4, 2, -2, -5, -13, 11, 0, 9, 11, -13, -4, -13, -11, 14, -8, 1, 8, 1, 9, -13, -11, 3, -11, 9, 12, -2, -4, -11, 6, 14, -7, -5, 1, -1, -3, -4, -5, 12, 12, 13, 6, -7, -15, 10, 14, 14, -12, 8, 0, 13, 2, -3, 1, -1, -9, -9, 12, -6, -5, -5, -6, 4, 5, 2, 10, -13, 13, 12, 6};
        long start1 = System.currentTimeMillis();
        threeSum1(new int[]{0,0,0});
        System.out.println("Total time taken by v1::" + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        threeSum(new int[]{0,0,0});
        System.out.println("Total time taken by me::" + (System.currentTimeMillis() - start2));
    */
        //int[] input = new int[]{3,2,1};
        int[] input = new int[]{1,3,2};
        //int[] input = new int[]{2,3,0,2,4,1};


        nextPermutationv1(input);
        for(int i = 0; i < input.length; i++)   {
            System.out.print(input[i]);
        }


    }

    public static int reverse(int y) {
        long multiplier = 0;
        int x = Math.abs(y);

        while (x > 0) {
            multiplier = (multiplier * 10) + (x % 10);
            x = x / 10;
        }

        if (y < 0) {
            long res = (0 - multiplier);
            return ((res < Integer.MIN_VALUE) ? Integer.MIN_VALUE : (int) res);
        } else {

            return ((multiplier > Integer.MAX_VALUE) ? 0 : (int) multiplier);
        }
        // 9646324351

    }

    public static List<List<Integer>> threeSum1(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        Arrays.sort(num);
        //-4,-1,-1,0,1,2
        int length = num.length;

        for (int i = 0; i < length; i++) {
            int currVal = num[i];
            if (i > 0 && currVal == num[i - 1]) continue;
            if (currVal > 0) break;

            int target = -1 * currVal;
            int left = i + 1;
            int right = length - 1;

            while (right > left) {
                int leftVal = num[left];
                int rightVal = num[right];
                int sum = leftVal + rightVal;

                if (sum < target) {
                    while (++left < right && num[left] == leftVal) ;
                } else if (sum > target) {
                    while (left < --right && num[right] == rightVal) ;
                } else {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(currVal);
                    list.add(leftVal);
                    list.add(rightVal);
                    result.add(list);

                    while (++left < right && num[left] == leftVal) ;
                    while (left < --right && num[right] == rightVal) ;
                }
            }

        }
        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if ((nums == null) || (nums.length == 0)) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            if (nums[i] > 0)
                break;

            int item = -nums[i];
            List<Integer> temp = new ArrayList<Integer>();
            int j = i + 1;
            int k = nums.length - 1;

            if ((i > 0) && (nums[i] == nums[i - 1])) {
                continue;
            }

            while (j < k) {

                if (nums[j] + nums[k] > item) {
                    k--;
                } else if (nums[j] + nums[k] < item) {
                    j++;
                } else {
                    temp.add(-item);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    j++;
                    k--;
                    result.add(temp);
                }

                while ((k > j) && (nums[k] == nums[k - 1])) {
                    k--;

                }

                while ((j < k) && (nums[j] == nums[j - 1])) {
                    j++;

                }
            }
        }

        return result;

    }


    private static void nextPermutationv1(int[] nums) {
        if ((nums == null) || (nums.length == 0)) {
            return;
        }

        int N = nums.length;
        int i = N-1;
        int temp = -1;

        while(i > 0) {
            if (nums[i] > nums[i - 1]) {
                temp = i-1;
                break;
              } else {
                i--;
            }
        }

        if(temp != -1) {
            //int temp_j = -1;
            for(int j = N-1; j > temp; j--) {
               if(nums[j] > nums[temp]) {
                  swap(nums, j, temp);
                  //temp_j = j;
                  break;
               }
            }

            int k = N-1;
            int temp_j = temp + 1;
            while(temp_j < k)   {
                int tmp = nums[temp_j];
                nums[temp_j] = nums[k];
                nums[k] = tmp;
                temp_j++;
                k--;
            }
        } else  {
            Arrays.sort(nums);
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
