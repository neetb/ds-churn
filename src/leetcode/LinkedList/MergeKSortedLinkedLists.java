package leetcode.LinkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by abhinav on 24/7/15.
 */
public class MergeKSortedLinkedLists {

    public static void main(String[] args) {
        MergeKSortedLinkedLists test = new MergeKSortedLinkedLists();
        ListNode[] lists = new ListNode[2];
        lists[0] = new ListNode(1);
        lists[1] = new ListNode(0);
        //lists[2] = new ListNode(11);
        ListNode head = test.mergeKLists(lists);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    /**
     * We have K sorted lists say L1, L2, L3...Lk
     * To merge these sorted lists, we will returen a listNode pointing to a list of N*k nodes.
     * Approach -
     * Take first element from every list, we have k elements. sort the k elements and take the smallest and add it to the list.
     * We need to keep track of the specific list from which the element was taken. Add the next element from that list and then repeat.
     * <p/>
     * Our data structure ie array/heap would always contain k elements max.
     * <p/>
     * The complexity analysis -
     * heap - nk*logk (Better approach)
     * array of k elements, sorting klogk, sorting at every step - nk * klogk or insertion and shifting will take nk*n*klogk
     */

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode mergedHead = null;
        ListNode mergedTail = null;

        if ((lists == null) || (lists.length == 0)) {
            return mergedHead;
        }

        int k = lists.length;
        MinHeap minHeap = new MinHeap(k);
        boolean allEmpty = true;

        for (int i = 0; i < k; i++) {
            ListNode node = lists[i];
            if (node != null) {
                //allEmpty = false;
                minHeap.add(node);
            }
        }

        //run this while loop till we havent covered all the N*K elements.
        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.removeSmallest();
            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }

            ListNode newNode = new ListNode(smallestNode.val);

            if (mergedHead == null) {
                mergedHead = newNode;
                mergedTail = newNode;
            } else {
                mergedTail.next = newNode;
                mergedTail = newNode;
            }
        }

        return mergedHead;
    }


    public ListNode mergeKListsv1(ListNode[] lists) {
        ListNode mergedHead = null;
        ListNode mergedTail = null;
        ListNodeComparator comparator = new ListNodeComparator();

        if ((lists == null) || (lists.length == 0)) {
            return mergedHead;
        }

        int k = lists.length;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(k, comparator);
        boolean allEmpty = true;

        for (int i = 0; i < k; i++) {
            ListNode node = lists[i];
            if (node != null) {
                //allEmpty = false;
                minHeap.add(node);
            }
        }

        //run this while loop till we havent covered all the N*K elements.
        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.poll();
            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }

            ListNode newNode = new ListNode(smallestNode.val);

            if (mergedHead == null) {
                mergedHead = newNode;
                mergedTail = newNode;
            } else {
                mergedTail.next = newNode;
                mergedTail = newNode;
            }
        }

        return mergedHead;
    }

    class ListNodeComparator implements Comparator<ListNode>    {
        public int compare(ListNode a, ListNode b)  {
            if(a.val == b.val)  {
                return 0;
            }else if(a.val > b.val)  {
                return 1;
            }else   {
                return -1;
            }
        }
    }

    class MinHeap {
        ListNode[] items;
        int N = 0;

        public MinHeap(int K) {
            items = new ListNode[K + 1];
        }

        public void add(ListNode node) {
            items[++N] = node;
            swim(N);
        }

        public ListNode removeSmallest() {
            ListNode smallest = items[1];
            swap(1, N);
            N--;
            sink(1);
            return smallest;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        private void swim(int k) {
            while ((k > 1) && (less(items[k], items[k >> 1]))) {
                swap(k, k >> 1);
            }
        }

        private void sink(int k) {
            int j = k << 1;
            while (j <= N) {
                if ((j < N) && less(items[j - 1], items[j])) {
                    j--;
                }
                if (!less(items[k], items[j])) {
                    swap(j, k);
                    k = j;
                } else {
                    break;
                }
            }
        }

        private void swap(int a, int b) {
            ListNode temp = items[a];
            items[a] = items[b];
            items[b] = temp;
        }

        private boolean less(ListNode a, ListNode b) {
            return a.val < b.val;
        }
    }

}
