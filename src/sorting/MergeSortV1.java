package sorting;

import java.util.Arrays;

/**
 * Created by abhinav on 12/6/15.
 */
public class MergeSortV1<T extends Comparable> {

    public void sort(T[] input) {
        if((input == null) || (input.length == 0))  {
            return;
        }

        T[] aux = (T[])new Comparable[input.length];
        aux = Arrays.copyOf(input, input.length);
        mergeSort(input, aux, 0, input.length - 1);

        for(int i = 0; i < input.length; i++)   {
            System.out.println(input[i]);
        }

    }

    private void mergeSort(T[] input, T[] aux, int lo, int hi)  {
        if(hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo)/2;

        mergeSort(input, aux, lo, mid);
        mergeSort(aux, input, mid + 1, hi);
        merge(input, aux, lo, hi);
    }

    private void merge(T[] input, T[] aux, int lo, int hi)  {
       /* for(int i = lo; i <= hi; i++)   {
            aux[i] = input[i];
        }*/

        int mid = lo + (hi - lo)/2;
        int i = lo;
        int j = mid + 1;
        for(int k = lo; k <= hi; k++) {
             if(i > mid)    {
                 input[k] = aux[j++];
             }else if(j > hi)   {
                 input[k] = aux[i++];
             }else if(aux[i].compareTo(aux[j]) < 0)  {
                 input[k] = aux[i++];
             }else  {
                 input[k] = aux[j++];
             }

        }
    }


    public static void main(String[] args)  {
        MergeSortV1 sort = new MergeSortV1();
        sort.sort(new Integer[]{7,2,6,1,0,9,8,5});
    }
}
