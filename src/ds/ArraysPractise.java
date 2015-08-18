package ds;

import java.util.Arrays;

/**
 * Created by abhinav on 12/7/15.
 */
public class ArraysPractise {

   public static void main(String[] args)   {
   /*    findSumClosestToZero(new int[]{1, 60, -10, 70, -80, 85});
       findSumClosestToZero(new int[]{-1,-2,-3,-4,-5,-6});
       findSumClosestToZero(new int[]{1,2,-3,4,5,6});

       System.out.println(findKthBitonic(new int[]{1,22,44,55,66,111,34,23,12}, 0, 8));
       System.out.println(findKthBitonic(new int[]{1,22,44,55,66,111,9999}, 0, 6));
       System.out.println(findKthBitonic(new int[]{1,22,44,55,66,111,9}, 0, 6));

       System.out.println(medianOfSortedArrays(new int[]{1, 5, 7, 10, 13}, new int[]{11, 15, 23, 30, 45}));
*/
       System.out.println(minSubArrayLen(7, new int[]{4, 3, 1, 7, 3}));

   }

    public static int minSubArrayLen(int s, int[] a) {
        if (a == null || a.length == 0)
            return 0;

        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;

        while (j < a.length) {
            sum += a[j++];

            while (sum >= s) {
                min = Math.min(min, j - i);
                sum -= a[i++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
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
        while(lo < hi) {
            int pos = partition(nums, lo, hi);
            if(pos == k)    {
                return nums[pos];
            }else if(pos > k)   {
                hi = pos - 1;
            }else   {
                lo = pos + 1;
                k = k - pos;
            }
        }

        return -1;
    }

    private int partition(int[] nums, int lo, int hi)   {
        int pivot = nums[lo];
        int i = lo;
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


   private static void findSumClosestToZero(int[] input)   {
       if((input == null) || (input.length == 0))    {
           return;
       }

       Arrays.sort(input);
       int min_i = -1;
       int min_j = -1;
       int min_sum = Integer.MAX_VALUE;

       for(int i = 0; i < input.length - 1; i++)    {
           int item = input[i];
               int pos = lookup(input, Math.abs(item), i + 1, input.length - 1);
               int diff1 = item + input[pos];
               int diff2 = Integer.MAX_VALUE;
               if (pos > i+1)  {
                   diff2 = item + input[pos - 1];
               }
               if((Math.abs(diff1) < Math.abs(diff2)) && (Math.abs(diff1) < Math.abs(min_sum)))    {
                   min_i = i;
                   min_j = pos;
                   min_sum = diff1;
               }

               if((Math.abs(diff2) < Math.abs(diff1)) && (Math.abs(diff2) < Math.abs(min_sum)))    {
                   min_i = i;
                   min_j = pos - 1;
                   min_sum = diff2;
               }

       }

       System.out.println("Closest sum to zero is :" + min_sum +" with i,j as " + input[min_i] + " , "+ input[min_j]);
   }


   private static int findKthBitonic(int[] input, int lo, int hi)  {
       if((input == null) || (input.length == 0))    {
           return -1;
       }

       //base
       if(lo == hi) {
           return lo;
       }
       if(lo > hi) {
           return -1;
       }

       int mid = lo + (hi - lo)/2;
       //part of the increasing subsequence
       if(input[mid] > input[mid-1]) {
           if(input[mid] > input[mid+1])    {
               return mid;
           }else    {
                return findKthBitonic(input, mid+1, hi);
               //return -1;
           }
       } else   {
           return findKthBitonic(input, lo, mid-1);
       }
   }

   private static int lookup(int[] input, int item, int lo, int hi) {
       if(lo >= hi)  {
           return lo;
       }

       int mid = lo + (hi - lo)/2;
       if(input[mid] > item)    {
           return lookup(input, item, lo, mid - 1);
       } else  if(input[mid] < item)    {
           return lookup(input, item, mid + 1, hi);
       } else   {
           //should not happen though as there are no dups
           return mid;
       }
   }

    private static int medianOfSortedArrays(int[] a, int[] b) {
      return   medianOfSortedArraysUtil(a, b, 0, a.length-1, 0, b.length-1);
    }

    //not correwct
    private static int medianOfSortedArraysUtil(int[] a, int[] b, int loA, int hiA, int loB, int hiB)  {

        if(hiA - loA == 1)  {
           return (max(a[loA], b[loB]) + min(a[hiA], b[hiB]) ) / 2;
        }


        if(hiA - loA  == 0)  {
           return (a[loA] + b[loA])/2;
        }

        int medA = loA + (hiA - loA)/2;
        int medB = loB + (hiB - loB)/2;

        if(a[medA] > b[medB]) {
           return medianOfSortedArraysUtil(a, b, loA, medA, medB, hiB);
        }else if(a[medA] < b[medB])  {
           return medianOfSortedArraysUtil(a, b, medA, hiA, loB, medB);
        }else   {
            return a[medA];
        }
    }

    private static int max(int a, int b)    {
        return a > b ? a : b;
    }


    private static int min(int a, int b)    {
        return a < b ? a : b;
    }



}

