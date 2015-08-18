package hackerrank.warmup.moderate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by abhinav on 20/5/15.
 */
public class Pairs {

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        sc.nextLine();

        int[] input = new int[N];
        for(int i = 0;i < N; i++)   {
            input[i] = sc.nextInt();
        }

        //long start1 = System.currentTimeMillis();
        //System.out.println(countPairsV1(input, K));
        //System.out.println("Time taken for v1 : " + (System.currentTimeMillis() - start1));

        /*long start2 =  System.currentTimeMillis();
        System.out.println("Time taken for v2 : " + (System.currentTimeMillis() - start2));*/

        System.out.println(countPairsV2(input, K));


    }

    private static int countPairsV1(int[] input, int k)  {
        Arrays.sort(input);
        int count = 0;
        for(int i = 0; i < input.length; i++)   {
            int num = input[i];
            int pos = find(input, i+1, input.length - 1, num + k);
            if(pos != -1)   {
                count++;
            }
        }
        return count;
    }

    private static int find(int[] input, int lo, int hi, int item)   {
        if(hi < lo) {
            return -1;
        }

        int mid = (lo + hi)/2;
        if(input[mid] < item) {
           return find(input, mid+1, hi, item);
        }else if(input[mid] > item) {
           return find(input, lo, mid-1, item);
        }else   {

        }  return mid;
    }


    private static int countPairsV2(int[] input, int k)  {
        Map<Integer,Integer> countMap = new HashMap<Integer,Integer>();
        Map<Integer,Integer> mainMap = new HashMap<Integer,Integer>();

        for(int i = 0; i < input.length; i++) {
            int num = input[i];
            mainMap.put(num, 0);
        }

        for(int i = 0; i < input.length; i++)   {
            int num = input[i];
            //boolean existsInMainMap = mainMap.containsKey(num);

            if(countMap.containsKey(num - k))  {
               int count = countMap.get(num - k);
               countMap.put(num - k, count+1);
            }else {
                if(mainMap.containsKey(num - k))
                    countMap.put(num - k, 1);
            }

            if(countMap.containsKey(num + k))  {
                int count = countMap.get(num + k);
                countMap.put(num + k, count+1);
            }else {
                if(mainMap.containsKey(num + k))
                    countMap.put(num + k, 1);
            }
        }

        int sum = 0;

        for(Integer key : countMap.keySet())    {
            int val = countMap.get(key);
            sum += val;
        }

        return sum/2;
    }
}
