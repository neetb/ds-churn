package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by abhinav on 12/8/15.
 */
public class GenerateSubsets {

    public static void main(String[] args)  {
        GenerateSubsets test = new GenerateSubsets();
        test.subsets(new int[]{1,2,3,4});
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> subset = new ArrayList<Integer>();
        res.add(subset);

        Arrays.sort(nums);
        doSubsets(nums,res,subset,0);
        return res;
    }

    private void doSubsets(int[] nums, List res,List subset,int start){
        if(start != nums.length){
            for(int i=start; i<nums.length; i++){
                subset.add(nums[i]);
                res.add(new ArrayList(subset));
                doSubsets(nums,res,subset,i+1);
                subset.remove(subset.get(subset.size()-1));
            }
        }
    }
}
