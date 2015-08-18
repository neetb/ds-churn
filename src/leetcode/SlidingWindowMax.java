package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by abhinav on 21/7/15.
 */
public class SlidingWindowMax {

    public static void main(String[] args)  {
        SlidingWindowMax max = new SlidingWindowMax();
        int[] results = max.maxSlidingWindow(new int[]{-6,-10,-7,-1,-9,9,-8,-4}, 4);

        //int[] results = max.maxSlidingWindow(new int[]{-6,-10,-7,-1,-9,9,-8,-4,10,-5,2,9,0,-7,7,4,-2,-10,8,7}, 7);
        for(int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if((nums == null) || (nums.length == 0) || (k == 0))    {
            return new int[0];
        }

        int N = nums.length;
        int count = 0;
        int[] result = new int[N-k+1];
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        int currentMax = Integer.MIN_VALUE;
        int currentMaxIndex = -1;

        for(int i = 0; i < k; i++)  {
            if(currentMax < nums[i])    {
                currentMaxIndex = i;
                currentMax = nums[i];
            }
            deque.add(i);
        }

        result[count++] = currentMax;

        for(int i = k; i < N; i++)   {
           if(nums[i] >= currentMax) {
               deque.clear();
               deque.add(i);
               currentMax = nums[i];
               //currentMaxIndex = i;
           } else   {
               deque.removeFirst();
               deque.addLast(i);
               if(currentMax == nums[i - k])  {
                   int tempMaxIndex = -1;
                   int tempMax = Integer.MIN_VALUE;
                   for(int item : deque)    {
                     if(tempMax < nums[item])  {
                        tempMax = nums[item];
                        //tempMaxIndex = j;
                     }
                 }
                // currentMaxIndex = tempMaxIndex;
                 currentMax = tempMax;
               }
           }

            result[count++] = currentMax;
        }

        return result;

    }



}
