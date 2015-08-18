package hackerrank.warmup.hackerrank.difficult;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by ramneet on 18/6/15.
 */
public class FindMedian {
    private MaxHeap<Integer> maxHeap;
    private MinHeap<Integer> minHeap;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        FindMedian findMedian = new FindMedian();
        findMedian.init(N);

       // FileWriter fos = new FileWriter("/home/abhinav/Work/temp/output1.txt");
        int[] temp = new int[N];
        for(int i = 0; i < N; i++)  {
            int num = Integer.parseInt(sc.nextLine());
            double avg = findMedian.findMedian(num, N);
            System.out.println(avg);
            //fos.write(avg + "\n");
            //temp[i] = num;
            //System.out.println(findMedian.findMedian(num, temp, i));
        }
        //fos.flush();
       //fos.close();
    }

    public  void init(int N)   {
        //not fixing the sizes of max and min, adding one extra element for storage before the heaps are balanced again
        this.maxHeap = new MaxHeap<Integer>(N/2 + 1);
        this.minHeap = new MinHeap<Integer>(N/2 + 1);

    }

   /* public double findMedian(int num, int[] temp, int i) {
       Arrays.sort(temp);
       if()
    }*/

    public double findMedian(int num, int N) {
        if (this.maxHeap.isEmpty()) {
            this.maxHeap.insert(num);
        }
        else {
            //incoming num is less than the max element of max heap --> one of the smallest numbers
            if (this.maxHeap.largest().compareTo(num) > 0) {
                this.maxHeap.insert(num);
            } else {
                this.minHeap.insert(num);
            }
        }

        balanceHeaps();

        int maxHeapSize = this.maxHeap.cnt - 1;
        int minHeapSize = this.minHeap.cnt - 1;

        //calculate the average
        if(minHeap.isEmpty())
            return maxHeap.largest();

        if(minHeapSize == maxHeapSize)  {
            return (((double)maxHeap.largest()) + ((double)minHeap.Smallest())) / 2;
        }

        if(maxHeapSize > minHeapSize)   {
            return maxHeap.largest();
        }else   {
            return minHeap.Smallest();
        }

    }

    private void balanceHeaps() {
        int maxHeapSize = this.maxHeap.cnt - 1;
        int minHeapSize = this.minHeap.cnt - 1;
        int diff = maxHeapSize - minHeapSize;
        if(Math.abs(diff) >  1)    {
            if(diff > 0)    {
                minHeap.insert(maxHeap.removeLargest());
            }else   {
                maxHeap.insert(minHeap.removeSmallest());
            }
        }
    }

    static class MaxHeap<T extends Comparable> {
        T[] items;
        int cnt = 1;

        public MaxHeap(int N)   {
            items = (T[])new Comparable[N+1];
        }

        public void insert(T num)   {
           items[cnt++] = num;
           swim(cnt - 1);
        }

        private void swim(int k)    {
           while((k > 1) && (less(k/2, k)))  {
              swap(k, k/2);
              k= k/2;
           }
        }

        public T removeLargest()    {
            T max = items[1];
            swap(1, cnt-1);
            items[cnt - 1] = null;
            cnt--;
            sink(1);
            return max;
        }

        public T largest()    {
            T max = items[1];
            return max;
        }

        private void sink(int k)    {
            while(2 * k < cnt) {
                int i = 2 * k;
                int j = i+1;

                //replace kth element with the one of the bigger child nodes, iteratively till we reach a leaf node
                int min = i;
                if((j < cnt) && (less(i,j))) {
                    min = j;
                }

                if(!less(k, min)) {
                    break;
                }
                swap(k, min);
                k = min;
             }
        }

        private boolean less(int i, int j)  {
            return items[i].compareTo(items[j]) < 0;
        }

        private void swap(int i, int j) {
            T temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }

        public boolean isEmpty()    {
            return cnt == 1;
        }
    }


   static class MinHeap<T extends Comparable> {
        T[] items;
        int cnt = 1;

        public MinHeap(int N)   {
            items = (T[])new Comparable[N+1];
        }

        public void insert(T num)   {
            items[cnt++] = num;
            swim(cnt - 1);
        }

        private void swim(int k)    {
            while((k > 1) && (greater(k/2, k)))  {
                swap(k, k/2);
                k= k/2;
            }
        }

        public T removeSmallest()    {
            T max = items[1];
            swap(1, cnt-1);
            items[cnt - 1] = null;
            cnt--;
            sink(1);
            return max;
        }

       public T Smallest()    {
           T min = items[1];
           return min;
       }

        private void sink(int k)    {
            while(2 * k < cnt) {
                int i = 2 * k;
                int j = i+1;

                //replace kth element with the one of the bigger child nodes, iteratively till we reach a leaf node
                int min = i;
                if((j < cnt) && (greater(i,j))) {
                    min = j;
                }
                if(!greater(k, min)) {
                    break;
                }
                swap(k, min);
                k = min;
            }
        }

        private boolean greater(int i, int j)  {
            return items[i].compareTo(items[j]) > 0;
        }

        private void swap(int i, int j) {
            T temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }

       public boolean isEmpty()    {
           return cnt == 1;
       }
    }
}
