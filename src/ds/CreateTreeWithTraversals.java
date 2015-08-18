package ds;

import java.util.Stack;

/**
 * Created by abhinav on 28/5/15.
 */
public class CreateTreeWithTraversals {

    private static int prIndex;

    private static int index = 0;

    private static int maxLevel = -1;

    public static void main(String[] args)  {
        //Node root = makeTree(new int[]{5,2,1,3,4,6,7}, new int[]{1,2,3,4,5,6,7}, 0, 6, true);
        //Node root = makeTree(new int[]{20,10,8,15,14,35,25,100}, new int[]{8,10,14,15,20,25,35,100}, 0, 7, true);
        Node root = makeTree(new int[]{20,8,4,12,10,14,22}, new int[]{4,8,10,12,14,20,22}, 0, 6, true);
      /*  System.out.println(LCAInBST(root,4, 12).item);
        System.out.println(LCAInBST(root,10, 12).item);
        System.out.println(LCAInBST(root,8,12).item);*/
        System.out.println(LCAInBST(root,8,22).item);

        //System.out.println(printInorderPreSuc(root, 25).item);
        //System.out.println(printInorderSuc(root, 16).item);

       /* Result res = new Result();
        printInorderPreSuc(root, 25, res);
        System.out.println(res.pre);
        System.out.println(res.suc);

         res = new Result();
        printInorderPreSuc(root, 10, res);
        System.out.println(res.pre);
        System.out.println(res.suc);
*/

        //Node root = makeTree(new int[]{18,3,2,1,5,1,4}, new int[]{2,3,1,18,1,5,4}, 0, 6, false);
        //Node root = makeTree(new int[]{10,-2,8,7,-100}, new int[]{8,-2,10,7,-100}, 0, 6, false);
        //printInOrder(root);
       /* doubleTree(root);
        System.out.println("Inorder after doubling*************");
        printInOrder(root);*/
        //printInOrder(root);
        //System.out.println(maxSumRootToLeaf(root));
        //printSpiral(root);
        //inorderWithoutRecursion(root);
        //printPostOrder(new int[]{4,2,5,1,3,6}, new int[]{1,2,4,5,3,6}, 0, 5);
        //System.out.println(LCA(root,6,3,false,false).item);

        //printLef                                                              tView(root);
        //printZigZag(root);
    }


    private static Node LCAInBST(Node node, int k1, int k2) {
        if(node == null)
            return null;

        int curr = node.item;
        boolean isK1Left = k1 < curr;
        boolean isK1Right = k1 > curr;

        boolean isK2Left = k2 < curr;
        boolean isK2Right = k2 > curr;

        if((node.item == k1) || (node.item == k2))  {
            return node;
        }

        if(((isK1Left) && (isK2Right)) || ((isK2Left) && (isK1Right))){
            boolean k1Exists = isK1Left ? find(node.left, k1) : find(node.right, k1);
            boolean k2Exists = isK2Left ? find(node.right, k2) : find(node.right, k2);
            if((k1Exists) && (k2Exists))
                return node;
            else
                return null;
        }

        if(isK1Left)    {
            return LCAInBST(node.left, k1, k2);
        }else   {
            return LCAInBST(node.right, k1, k2);
        }

    }

    private static boolean find(Node n, int k)  {
        if(n == null)
            return false;

        if(n.item == k) {
            return true;
        }

        if(n.item > k)  {
            return find(n.left, k);
        }else   {
            return find(n.right, k);
        }
    }

    private static void printInorderPreSuc(Node node, int key, Result res)   {
        if(node == null)
            return;

        if(node.item > key) {
            res.suc = node.item;
            printInorderPreSuc(node.left, key, res);
        }
        else if(node.item < key)   {
            res.pre = node.item;
            printInorderPreSuc(node.right, key, res);
        }
        else {
            //matching key
            if(node.left != null) {
                res.pre = max(node.left).item;
            }

            //matching key
            if(node.right != null)   {
                res.suc = min(node.right).item;
            }

        }

    }

    private static Node printInorderPre(Node node, int key)   {
        if(node == null)
            return null;

        if(node.item > key) {
            return printInorderPre(node.left, key);
        }else if(node.item < key)   {
            Node ret = printInorderPre(node.right, key);
            if((ret == null) || (ret.item == key))
                return node;
            return ret;
        }else {
            //matching key
            if(node.left == null)   {
                return node;
            }
            else return max(node.left);


        }
    }

    private static Node max(Node node)  {
        if(node.right == null)
            return node;

        while(node.right != null)   {
            node = node.right;
        }

        return node;
    }

    private static Node printInorderSuc(Node node, int key)   {
        if(node == null)
            return null;

        if(node.item > key) {
           Node ret =  printInorderSuc(node.left, key);
            if((ret == null) ||(ret.item == key))
                return node;
            return ret;
        }else if(node.item < key)   {
            return printInorderSuc(node.right, key);
        }else {
            //matching key
            if(node.right == null)   {
                return node;
            }
            else return min(node.right);
        }
    }

    private static Node min(Node node)  {
        if(node == null)
            return null;

        while(node.left != null)    {
            node = node.left;
        }

        return node;
    }

    private static void printZigZag(Node node)  {
        if(node == null)
            return;


        Stack<Node> stack1 = new Stack<Node>();
        Stack<Node> stack2 = new Stack<Node>();

        stack2.push(node);
     do {

         while (!stack2.isEmpty()) {
             Node temp = stack2.pop();
             System.out.println(temp.item);

             if (temp.left != null) {
                 stack1.push(temp.left);
             }

             if (temp.right != null) {
                 stack1.push(temp.right);
             }

         }

         while (!stack1.isEmpty()) {
             Node temp = stack1.pop();
             System.out.println(temp.item);

             if (temp.right != null) {
                 stack2.push(temp.right);
             }

             if (temp.left != null) {
                 stack2.push(temp.left);
             }
         }

     }while(!stack1.isEmpty() || !stack2.isEmpty());
    }



    private static void printLeftView(Node node) {
        printLeftViewUtil(node, 0);
    }

    private static void printLeftViewUtil(Node node, int level) {
      if(node == null)  {
          return;
      }

      //if((node .left == null) && (node.right == null))  {
          if(level > maxLevel)  {
              maxLevel = level;
              System.out.println(node.item);
          }
      //}

      printLeftViewUtil(node.left, level + 1);
      printLeftViewUtil(node.right, level + 1);

    }

    private static void printPostOrder(int[] in, int[] pre, int start, int end) {
        if(start > end) {
            return;
        }

        int item = pre[index++];
        int inIndex = findIndex(in,item,start,end,false);

        if(inIndex == -1)   {
             System.out.println("Index -1 recvd");
        }

        printPostOrder(in, pre, start, inIndex - 1);
        printPostOrder(in, pre, inIndex + 1, end);

        System.out.println(item);

    }


    private static Node LCA(Node node, int n1, int n2, boolean n1b, boolean n2b)   {
         //base case
        if(node == null)    {
            return null;
        }

        if(node.item == n1) {
            //n1b = true;
            return node;
        }


        if(node.item == n2) {
           // n2b = true;
            return node;
        }

        Node left = LCA(node.left, n1, n2, n1b, n2b);

       /* if(n1b && n2b)  {
            return node;
        }*/

        Node right = LCA(node.right, n1, n2, n1b, n2b);

        if((left!=null) && (right!=null)) {
            return node;
        }

        return left != null ? left : right;
    }



    private static int maxSumRootToLeaf(Node node)  {
        if(node == null)    {
            return Integer.MIN_VALUE;
        }

        if((node.left == null) && (node.right == null)) {
            return node.item;
        }

        return node.item + max(maxSumRootToLeaf(node.left), maxSumRootToLeaf(node.right));
    }

    private static int max(int a, int b)   {
        return a > b ? a : b;
    }

    private static boolean isSumTree(Node node)    {
        if(node == null)    {
            return true;
        }

        //Leaf is a sum tree
        if((node.left == null) && (node.right == null)) {
            return true;
        }

        boolean leftSumTree = isSumTree(node.left);
        boolean rightSumTree = isSumTree(node.right);

        if(leftSumTree && rightSumTree) {
            int sum = 0;
            if(node.left != null)   {
                sum += node.left.item;
            }

            if(node.right != null)   {
                sum += node.right.item;
            }

            return (sum == node.item);
        } else  {
            return false;
        }

    }

    private static void doubleTree(Node node)   {
        if(node == null)    {
            return;
        }

        Node temp = node.left;
        node.left = new Node(node.item);
        node.left.left = temp;
        doubleTree(temp);
        doubleTree(node.right);

    }


    private static void inorderWithoutRecursion(Node node)  {
        if(node == null)    {
            return;
        }

        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        boolean done = false;

        while(!done) {

            if(node.left != null)  {
                stack.push(node.left);
                node = node.left;
            }
            else {
                if (!stack.isEmpty()) {
                    Node n = stack.pop();
                    System.out.print(n.item + "\t");

                    Node right = n.right;
                    if (right != null) {
                        stack.push(right);
                        node = right;
                    }
                } else {
                    done = true;
                }
            }
        }

    }

    private static void printInOrder(Node node)    {
        if(node == null)    {
            return;
        }

        printInOrder(node.left);
        System.out.println(node.item);
        printInOrder(node.right);
    }


    private static void printSpiral(Node node)  {
        if(node == null)    {
            return;
        }

        Stack<Node> stack = new Stack<Node>();
        stack.push(node);

        printSpiralHelper(node, stack, 0);
    }

    private static void printSpiralHelper(Node node, Stack<Node> stack, int level) {
         if(!stack.isEmpty()) {
             Stack<Node> newStack = new Stack<Node>();
             while(!stack.isEmpty())    {
                 Node n = stack.pop();
                 System.out.print(n.item + " ");
                 if(level == 0) {
                     if(n.right != null)
                        newStack.push(n.right);

                     if(n.left != null)
                        newStack.push(n.left);
                 }else  {

                     if(n.left != null)
                        newStack.push(n.left);

                     if(n.right != null)
                        newStack.push(n.right);
                 }
             }

             printSpiralHelper(node, newStack, level+1);
         }
    }

    private static Node makeTree(int[] pre, int[] in, int start, int end, boolean sorted ) {
        if(start > end) {
            return null;
        }

        if(prIndex == pre.length)   {
            return null;
        }

        int item = pre[prIndex++];
        int index = findIndex(in, item, start, end, sorted);

        Node node = new Node(item);
        node.left = makeTree(pre, in, start, index - 1, sorted);
        node.right = makeTree(pre, in, index + 1, end, sorted);
        return node;

    }


    private static int findIndex(int[] input, int item, int start, int end, boolean sorted) {
        if(sorted)
            return find(input, item, 0, input.length - 1);
        else
            return findLinear(input, item, start, end);
    }

    private static int findLinear(int[] input, int item, int start, int end)  {
        int index = -1;
        for(int i = start;i <=end; i++)    {
            if(input[i] == item)    {
                index = i;
                break;
            }
        }
        return index;
    }

    private static int find(int[] input, int item, int lo, int hi) {
        if(lo > hi) {
            return -1;
        }

        int mid = (lo + hi)/2;
        if(input[mid] > item)   {
            return find(input, item, lo, mid - 1);
        }else if(input[mid] < item) {
            return find(input, item, mid + 1, hi);
        }else   {
            return mid;
        }
    }

   static  class Node  {
        int item;
        Node left;
        Node right;

        public Node(int item)   {
            this.item = item;
            this.left = null;
            this.right = null;
        }
    }

    static class Result {
        int pre;
        int suc;

        public int getPre() {
            return pre;
        }

        public void setPre(int pre) {
            this.pre = pre;
        }

        public int getSuc() {
            return suc;
        }

        public void setSuc(int suc) {
            this.suc = suc;
        }
    }
}
