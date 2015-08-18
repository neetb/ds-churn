package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by abhinav on 23/7/15.
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum test = new ThreeSum();
        int[] input = new int[] {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
        long start = System.currentTimeMillis();
        List<List<Integer>> res = test.threeSum(input);
        long timeT = System.currentTimeMillis();
        System.out.println(timeT - start);

        for (List<Integer> result : res) {
            System.out.println(result);
        }
    }

    /*For example, given array S = {-1 0 1 2 -1 -4},

      Sort the array :: -4, -1, -1, 0, 1, 2
    A solution set is:
            (-1, 0, 1)
            (-1, -1, 2)
    */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if ((nums == null) || (nums.length == 0)) {
            return result;
        }

        Arrays.sort(nums);
        int N = nums.length;

        for (int i = 0; i < N - 1; i++) {
            if (nums[i] > 0) {
                break;
            }

            if((i > 0) && (nums[i] == nums[i-1]))    {
               continue;
            }

            int j = i + 1;
            int k = N - 1;

            while (j < k) {
                int sum = nums[j] + nums[k] + nums[i];
                if (sum < 0) {
                    j++;
                     while ((j < N) && (nums[j] == nums[j - 1])) {
                        j++;
                    }
                } else if (sum > 0) {
                    k--;
                    while ((k > i) && (nums[k] == nums[k + 1])) {
                        k--;
                    }
                } else {
                    List<Integer> res = new ArrayList<Integer>();
                    res.add(nums[i]);
                    res.add(nums[j]);
                    res.add(nums[k]);
                    result.add(res);

                    j++;
                    k--;

                    while ((j < N) && (nums[j] == nums[j - 1])) {
                        j++;
                    }

                    while ((k > i) && (nums[k] == nums[k + 1])) {
                        k--;
                    }
                }


            }

        }

        return result;
    }
}