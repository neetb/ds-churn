package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by abhinav on 24/7/15.
 */
public class ArrayPermutation {

    public static void main(String[] args)   {
        ArrayPermutation test = new ArrayPermutation();
        test.permute(new int[]{1,2,3});
        test.permute2(new int[]{1,2,3});
    }

    public List<List<Integer>> permute(int[] nums) {
        if((nums == null) || (nums.length == 0)) {
            return null;
        }

        List<Integer> input = new ArrayList<Integer>();
        List<Integer> remaining = new ArrayList<Integer>();

        for(int i = 0; i < nums.length; i++)    {
            input.add(nums[i]);
        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        permute(result, remaining, input);

        for(List<Integer> res : result) {
            System.out.println(res);
        }

        return result;
    }

    public List<List<Integer>> permute2(int[] nums) {
        if((nums == null) || (nums.length == 0)) {
            return null;
        }

        List<Integer> input = new ArrayList<Integer>();
        List<Integer> remaining = new ArrayList<Integer>();

        for(int i = 0; i < nums.length; i++)    {
            input.add(nums[i]);
        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        permute2(result, remaining, input);

        for(List<Integer> res : result) {
            System.out.println(res);
        }

        return result;
    }

    private void permute(List<List<Integer>> result, List<Integer> prefix, List<Integer> suffix) {
        if (suffix.isEmpty()) {
            result.add(prefix);
        } else {
            for (int i = 0; i < suffix.size(); i++) {
                List<Integer> prefixCopy = new ArrayList<Integer>(prefix);
                List<Integer> suffixCopy = new ArrayList<Integer>(suffix);

                int item = suffixCopy.get(i);
                prefixCopy.add(item);
                suffixCopy.remove(i);
                permute(result, prefixCopy, suffixCopy);
            }
        }
    }



    private void permute2(List<List<Integer>> result, List<Integer> prefix, List<Integer> suffix) {
        if (suffix.isEmpty()) {
            result.add(prefix);
        } else {
            for (int i = 0; i < suffix.size(); i++) {
                List<Integer> prefixCopy = new ArrayList<Integer>(prefix);
                List<Integer> suffixCopy = new ArrayList<Integer>(suffix);

                prefixCopy.add(suffixCopy.get(i));
                suffixCopy.remove(i);
                permute2(result, prefixCopy, suffixCopy);
                //prefix.remove(prefix.size() - 1);
            }
        }
    }
}
