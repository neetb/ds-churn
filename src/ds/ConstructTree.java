package ds;

/**
 * Created by abhinav on 30/5/15.
 */
public class ConstructTree {
    private static int index = 0;


    public static void main(String[] args) {
        Node parent = null;
        test(parent);
        System.out.println(parent);
    }

    private static void test(Node test) {
        test = new Node(1);
        return;
    }

    private static Node makeTree(int[] pre, char[] preLN, Node parent) {
        if(index == pre.length) {
            return parent;
        }

        Node node = new Node(pre[index++]);

        if (parent == null) {
            if (preLN[index] == 'L') {
                return node;
            } else {
                makeTree(pre, preLN, node);
                return node;
            }
        }


        if (parent.left == null) {
            parent.left = node;
        }

        if (parent.right == null) {
            parent.right = node;
        }

        if (preLN[index] == 'N') {
            return makeTree(pre, preLN, node);
        } else {
            if((parent.left != null) && (parent.right != null)) {
               return parent;
            }else {
                return makeTree(pre, preLN, parent);
            }
        }
    }

    static class Node  {
        int item;
        Node left;
        Node right;

        public Node(int item)   {
            this.item = item;
            this.left = null;
            this.right = null;
        }
    }
}
