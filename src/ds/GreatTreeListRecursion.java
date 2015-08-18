package ds;

/**
 * Created by abhinav on 1/6/15.
 */
public class GreatTreeListRecursion {


    private static Node build(Node node) {
        if (node == null) {
            return null;
        }

        if ((node.left == null) & (node.right == null)) {
            return node;
        }


        if (node.left != null) {
            Node prev = build(node.left);

            while (prev.right != null) {
                prev = prev.right;
            }

            prev.right = node;
            node.left = prev;
        }

        if (node.right != null) {
            Node prev2 = build(node.right);

            while (prev2.left != null) {
                prev2 = prev2.left;
            }

            prev2.left = node;
            node.right = prev2;

        }

        return node;

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
}
