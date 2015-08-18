package leetcode;

import java.util.Arrays;

/**
 * Created by abhinav on 26/7/15.
 */
public class ThreeSumClosest {

    public static void main(String[] args)  {
        ThreeSumClosest test = new ThreeSumClosest();
        System.out.println(test.threeSumClosest(new int[]{13,2,0,-14,-20,19,8,-5,-13,-3,20,15,20,5,13,14,-17,-7,12,-6,0,20,-19,-1,-15,-2,8,-2,-9,13,0,-3,-18,-9,-9,-19,17,-14,-19,-4,-16,2,0,9,5,-7,-4,20,18,9,0,12,-1,10,-17,-11,16,-13,-14,-3,0,2,-18,2,8,20,-15,3,-13,-12,-2,-19,11,11,-10,1,1,-10,-2,12,0,17,-19,-7,8,-19,-17,5,-5,-10,8,0,-12,4,19,2,0,12,14,-9,15,7,0,-16,-5,16,-12,0,2,-16,14,18,12,13,5,0,5,6}, -59));
        System.out.println(test.threeSumClosestv1(new int[]{13,2,0,-14,-20,19,8,-5,-13,-3,20,15,20,5,13,14,-17,-7,12,-6,0,20,-19,-1,-15,-2,8,-2,-9,13,0,-3,-18,-9,-9,-19,17,-14,-19,-4,-16,2,0,9,5,-7,-4,20,18,9,0,12,-1,10,-17,-11,16,-13,-14,-3,0,2,-18,2,8,20,-15,3,-13,-12,-2,-19,11,11,-10,1,1,-10,-2,12,0,17,-19,-7,8,-19,-17,5,-5,-10,8,0,-12,4,19,2,0,12,14,-9,15,7,0,-16,-5,16,-12,0,2,-16,14,18,12,13,5,0,5,6}, -59));

    }

    public int threeSumClosest(int[] nums, int target) {
        if((nums == null) || (nums.length == 0)) {
            // what to return ?
            return -1;
        }

        int N = nums.length;
        int closestSum = 0;
        boolean first = true;
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++)    {
            int item = nums[i];
            int j = i+1; int k = N-1;
            if((item > 0) && (target < item))   {
                break;
            }
            while(j < k)    {
                int left = nums[j];
                int right = nums[k];
                int sum = left + right + item;
                //System.out.println("*"+sum);
                if(first)   {
                    closestSum = sum;
                    first = false;
                }else {
                    if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                        closestSum = sum;
                    }
                }

                if(sum < target)    {
                    j++;
                    while((j < N - 1) && (nums[j] == nums[j-1])) {
                        j++;
                    }
                }else if(sum > target)  {
                    k--;
                    while((k > 0) && (nums[k] == nums[k+1])) {
                        k--;
                    }
                }else   {
                    break;
                }
            }
        }

        return closestSum;
    }

    public int threeSumClosestv1(int[] nums, int target) {
        int result=nums[0]+nums[1]+nums[2];
        Arrays.sort(nums);
        for(int i=0; i<nums.length-2;i++){

            int head=i+1, tail=nums.length-1;
            while(head<tail){
                int sum=nums[i]+nums[head]+nums[tail];
                if(sum==target) return target;
                result=(Math.abs(result-target)> Math.abs(sum-target))? sum : result;
                if(sum<target) {
                    head++;
                }
                else {
                    tail--;
                }
            }
        }
        return result;
    }
}
