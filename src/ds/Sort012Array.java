package ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav on 15/6/15.
 */
public class Sort012Array {

    //sort array containing 0,1,2 in linear time
    public void sort(int[] input)   {
        int lo = 0;
        int i = lo;
        int gt = input.length - 1;

        while(i != gt) {

            if(input[i] == 2)    {
                swap(input, i , gt);
            } else  if(input[i] == 0)    {
                swap(input, i , lo);
                lo++;
                i++;
            } else {
                i++;
            }

            while(input[gt] == 2)   {
                gt--;
            }
        }

        for(i = 0;i < input.length; i++)    {
            System.out.println(input[i]);
        }
    }

    //Input : {3,0,-9,1,0,-7, 9}
    //Output : {-9,-7,0,0,3,1}
    public void sort1(int[] input)   {
        int i = 0; int j = 0;
        int k = input.length - 1;
        boolean firstZeroFound = false;
        while(i <= k) {
            if (input[i] > input[k]) {
                swap(input, i, k);
            }

            if (input[i] < 0) {
                if (j < i) {
                    swap(input, i, j);
                }
                j++;
            }

            while (input[k] > 0) {
                k--;
            }

            i++;
           //k has reached a -ve or 0 number
        }


        for(i = 0;i < input.length; i++)    {
            System.out.println(input[i]);
        }

    }


    //Input : {3,0,-9,1,0,-7, 9}
    //Output : {-9,-7,0,0,3,1}
    public void sort2(int[] input)   {
        int i = 0; int j = 0;
        int k = input.length - 1;
        while(i <= k) {
            if (input[i] > 0) {
                swap(input, i, k--);
            }else if(input[i] < 0) {
                    swap(input, i++, j++);
            }else   {
                i++;
            }

        }


        for(i = 0;i < input.length; i++)    {
            System.out.println(input[i]);
        }

    }

    public void generateAllPossibleArrays(int[] A, int[] B) {
        generateAllPossibleArraysUtil(A, B, -1, -1, false, null);
    }

    private void generateAllPossibleArraysUtil(int[] A, int[] B, int lastIndex, int count, boolean lastB, List<Integer> result )    {
        if(lastIndex == -1) {
            result = new ArrayList<Integer>();
            for(int i = 0; i < A.length; i++) {
                result.add(0, A[i]);
                generateAllPossibleArraysUtil(A, B, i, 0, false, result);
            }
            return;
        }

        if(!lastB)  {
            int lastElement = A[lastIndex];
            int start = -1;
            //List<Integer> prev = result;

            while(true) {
                int nextElementIndex = lookup(lastElement, B, start+1, B.length - 1);
                if (nextElementIndex == -1) {
                    break;
                } else {
                   // prev = result;
                    result.add(count+1,B[nextElementIndex]);
                    print(result, count+1);
                    generateAllPossibleArraysUtil(A, B, nextElementIndex, count + 1, true, result);
                    //result = prev;
                }
                start = nextElementIndex;
            }
        } else  {
            int lastElement = B[lastIndex];
            int start = -1;
            //List<Integer> prev = result;
            while(true) {
                int nextElementIndex = lookup(lastElement, A, start+1, A.length - 1);
                if (nextElementIndex == -1) {
                    break;
                } else {
                    result.add(count+1, A[nextElementIndex]);
                    //print(result, count+1);
                    generateAllPossibleArraysUtil(A, B, nextElementIndex, count+1, false, result);
                    //result = prev;
                }
                start = nextElementIndex;
            }
        }

    }


    private int lookup(int num, int[] A, int start, int end) {
         if(start > end)    {
             return -1;
         }

        int mid = start + (end - start)/2;
        if(A[mid] > num)    {
            lookup(num, A, start, mid - 1);
            return mid;
        }else if(A[mid] < num)  {
            return lookup(num, A, mid+1, end);
        }else   {
            return mid;
        }
    }

    private void print(List<Integer> result, int n)    {
        int count = 0;
        for(Integer num : result)   {
            if(count > n)
                break;
            System.out.print(num + " ");
            count++;
        }
        System.out.println();
    }

    private void swap(int[] input, int i, int j)    {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void main(String[] args)  {
        int v = 2;
        v = v + v++;
        System.out.println(v);

       /* Sort012Array s = new Sort012Array();
        //s.sort1(new int[]{0,-1,2,1,-5,2,3,-3,-4});
        s.generateAllPossibleArrays(new int[]{10,15,25}, new int[]{1,5,20,30});
        //s.sort1(new int[]{0,-1,2,1,-5,2,3,-3,-4,0,0,2,-3});
*/
    }
}
