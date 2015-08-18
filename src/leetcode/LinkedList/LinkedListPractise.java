package leetcode.LinkedList;

/**
 * Created by abhinav on 23/7/15.
 */
public class LinkedListPractise {

    public static void main(String[] args)  {
        LinkedListPractise test = new LinkedListPractise();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode newHead = test.reverseKGroup(head, 4);

        System.out.println("After");

        while(newHead != null) {
            System.out.print(newHead.val);
            newHead = newHead.next;

            if(newHead!=null)  {
                System.out.print(" , ");
            }
        }
      /*  System.out.println("Before");

        ListNode temp = head;
        while(temp != null) {
            System.out.print(temp.val);
            temp = temp.next;
            if(temp!=null)  {
                System.out.print(" , ");
            }
        }


        ListNode newHead = test.reverseKGroup(head, 3);
        System.out.println("After");

        while(newHead != null) {
            System.out.print(newHead.val);
            newHead = newHead.next;

            if(newHead!=null)  {
                System.out.print(" , ");
            }
        }*/
    }
    public ListNode swapPairs1(ListNode head) {
        if((head == null) || (head.next == null))  {
            return head;
        }

        ListNode node = head;
        ListNode newHead = null;
        ListNode tail = null;

        while(node != null)   {
            ListNode tempNext = null;

            if(node.next != null) {
                tempNext = node.next.next;
            }

            ListNode tempHead = reverse1(node);

            if(newHead == null)   {
                newHead = tempHead;
                tail = newHead.next;
            }else {
                tail.next = tempHead;
                tail = tempHead.next;
            }
            node = tempNext;

        }
        return newHead;
    }

    private ListNode reverse1(ListNode head) {
        if((head == null) || (head.next == null))   {
            return head;
        }

        ListNode node = head;
        ListNode temp = node.next;
        temp.next = node;
        return temp;
    }


    /******************* version 2 *******************/

    public ListNode swapPairsV2(ListNode head) {
        if((head == null) || (head.next == null))  {
            return head;
        }


      ListNode prev = null;
      ListNode newHead = head.next;
      while((head != null) && (head.next!=null)) {
            if(prev!=null)  {
               prev.next = head.next;
            }
            swap(head, head.next);
            prev = head;
            head = head.next;
        }
        return newHead;
    }

    public ListNode swapPairs(ListNode head) {
        if((head == null) || (head.next == null))  {
            return head;
        }


        ListNode start = new ListNode(Integer.MAX_VALUE);
        start.next = head;
        ListNode current = start;

        while((current != null) && (current.next!=null) && (current.next.next!=null)) {
            current.next = swap(current.next, current.next.next);
            current = current.next.next;
        }
        return start.next;
    }


    private ListNode swap(ListNode a, ListNode b) {
        a.next = b.next;
        b.next = a;
        return b;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if((head == null) || (k == 1))    {
            return head;
        }

        ListNode start = new ListNode(Integer.MIN_VALUE);
        start.next = head;
        ListNode current = start;

        while(current != null) {
            ListNode temp = current.next;
            ListNode res = reverseK(current.next, k);
            if(res == current.next) {
                break;
            }else   {
                current.next = res;
            }
            current = temp;
        }

        return start.next;
    }

    private ListNode reverseK(ListNode head, int k) {
        if ((head == null) || (head.next == null)) {
            return head;
        }

        int len = length(head);
        if (len < k) {
            return head;
        }

        ListNode temp = head;
        ListNode prev = null;
        int count = 0;
        while ((count != k) && (temp != null)) {
            ListNode next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
            count++;
        }

        /*if(count != k)    {
            return head;
        }else   {
            head.next =  temp;
            return prev;
        }*/

        head.next = temp;
        return prev;
    }

    private int length(ListNode head)   {
        ListNode temp = head;
        int len = 0;
        while(temp!=null)   {
             temp = temp.next;
            len++;
        }
        return len;
    }




}
