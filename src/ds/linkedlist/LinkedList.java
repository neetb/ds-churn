package ds.linkedlist;

/**
 * Created by abhinav on 18/5/15.
 */
public class LinkedList<T> {

    private Node head;


    public int getLengthRecursive() {
        Node temp = this.head;
        return getLengthRecursive(temp);
    }

    private int getLengthRecursive(Node start) {
        if (start == null) {
            return 0;
        } else
            return 1 + getLengthRecursive(start.next);
    }

    public int getLengthIterative(Node head) {
        if (head == null) {
            return 0;
        }

        int len = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            len++;
        }

        return len;
    }

    public Node getNthNode(int N) {
        if (N <= 0) {
            return null;
        }

        Node result = null;
        Node temp = head;
        int i = 1;

        while (i++ != N) {
            temp = temp.next;
        }

        return temp;
    }


    public Node getNthNodeFromTheEnd(int N) {
        if (N <= 0) {
            return null;
        }

        Node fast = head;
        Node slow = head;
        int i = 1;
        while (i++ != N) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public Node getMiddleNode() {
        Node slow = head;
        Node fast = head;

        while ((fast != null) && (fast.next != null)) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


    public void add(T item) {
        Node newNode = new Node(item, null);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void printList(Node start) {
        Node temp = start;
        while (temp.next != null) {
            System.out.print(temp.item + "->");
            temp = temp.next;
        }
        System.out.print(temp.item);
        System.out.println();
    }

    public boolean isPalindrome(Node head) {
        Node temp = head;
        Node slow = head;
        Node fast = head;
        boolean isPalindrome = true;
        while ((fast != null) && (fast.next != null)) {
            fast = fast.next.next;
            slow = slow.next;
        }

        Node reversedHalf = null;
        int len = getLengthIterative(head);
        if (len % 2 == 0) {
            reversedHalf = reverse(slow);
        } else {
            reversedHalf = reverse(slow.next);
        }

        int count = 1;
        while (count++ != len / 2) {
            if (!temp.item.equals(reversedHalf.item)) {
                isPalindrome = false;
                break;
            }
            temp = temp.next;
            reversedHalf = reversedHalf.next;
        }
        return isPalindrome;
    }

    /*public Node isPalindromeRecursive1(Node head, Node start) {
        if(start == null)   {
            return head;
        }

        Node result = isPalindromeRecursive1(head, start.next);

        if((result!=null) && (head.item == start.item)) {
            return head.next;
        }else{
            return new Node( -1, null);
        }
    }*/


   /* public boolean isPalindromeRecursive2(Node head, Node start) {
        if(start == null)   {
            return true;
        }

        boolean result = isPalindromeRecursive2(head, start.next);

        if((result) && (head.item == start.item)) {
            head = head.next;
            return true;
        }else{
            return false;
        }
    }*/

    private Node reverse(Node node) {
        if (node == null) {
            return null;
        }

        Node first = node;

        if (first.next == null) {
            return first;
        }

        Node rest = reverse(first.next);
        first.next.next = first;
        first.next = null;
        return rest;

    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return head;

        k = k % (getLen(head));
        if (k == 0) {
            return head;
        }

        ListNode temp = head;
        ListNode prev = null;
        ListNode newHead = head;

        for (int i = 1; i < k; i++) {
            temp = temp.next;
        }

        while (temp.next != null) {
            temp = temp.next;
            prev = newHead;
            newHead = newHead.next;
        }

        prev.next = null;
        temp.next = head;
        return newHead;
    }

    private int getLen(ListNode head) {
        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            temp = temp.next;
            len++;
        }
        return len;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public Node reverseList() {
        Node node = head;
        node = node.next;
        Node prev = head;
        prev.next = null;
        while (node != null) {
            Node next = node.next;
            node.next = prev;
            prev = node;
            node = next;

        }

        head = prev;
        return prev;
    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(14);
        list.add(5);
        list.add(6);
        list.add(7);

       // System.out.println(list.getLen(list.head));

       /* if (list.getLengthIterative(list.head) == 7) {
            System.out.println("getLenIter ver works fine");
        } else {
            System.out.println("getLenIter ver does not work fine");
        }

        if (list.getLengthRecursive(list.head) == 7) {
            System.out.println("getLengthRecursive ver works fine");
        } else {
            System.out.println("getLengthRecursive ver does not work fine");
        }

        System.out.println("Result of getNthNode for N = 3 is " + list.getNthNode(3).getItem());
        System.out.println("Result of getNthNodeFromTheEnd for N = 3 is " + list.getNthNodeFromTheEnd(3).getItem());
        System.out.println("Result of get middleNode is :" + list.getMiddleNode().getItem());


        System.out.println("Original List");
        list.printList(list.head);


        //reverse a list
        Node tail = list.reverse(list.head);

        System.out.println("Reversed list");
        list.printList(tail);

        LinkedList<Character> list1 = new LinkedList<Character>();
        list1.add('n');
        list1.add('i');
        list1.add('t');
        list1.add('t');
        list1.add('i');
        list1.add('n');

        // Node temp = list1.head;
        System.out.println("isPalindrome? " + list1.isPalindrome(list1.head));*/
    }


    static class Node<T> {
        T item;
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
