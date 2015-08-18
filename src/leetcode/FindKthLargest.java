package leetcode;

/**
 * Created by abhinav on 4/8/15.
 */
public class FindKthLargest {

    public static void main(String[] args)  {
        FindKthLargest test = new FindKthLargest();
        System.out.println(test.findKthLargest(new int[]{7,6,5,4,3,2,1}, 5));
    }

    public int findKthLargest(int[] nums, int k) {
        //error input checking goes here
        if((nums == null) || (nums.length == 0))    {
            return -1;
        }

        int pos = nums.length - k;
        return findKthLargestUtil(nums, pos, 0, nums.length - 1);

    }

    private int findKthLargestUtil(int[] nums, int k, int lo, int hi)   {
        while(lo <= hi) {
            int pos = partition(nums, lo, hi);
            if(pos == k)    {
                return nums[pos];
            }else if(pos > k)   {
                hi = pos - 1;
            }else   {
                lo = pos + 1;
            }
        }

        return -1;
    }

    private int partition(int[] nums, int lo, int hi)   {
        int pivot = nums[lo];
        int i = lo + 1;
        int j = hi;

        while(i <= j) {
            while((i <= hi) && (nums[i] < pivot)) {
                i++;
            }

            while(nums[j] > pivot) {
                j--;
            }

            if(i > j)   {
                break;
            }

            swap(nums, i, j);
            i++;
            j--;
        }

        swap(nums, lo, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
