package ds.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav on 2/7/15.
 */
public class IntervalSearchTree {

    private IntervalNode root;

    public static void main(String[] args)  {

        System.out.println(6 - 3 + 1);
        IntervalNode root = null;
        IntervalSearchTree tree = new IntervalSearchTree();
        root = tree.insert(root, 15, 20);
        root = tree.insert(root, 10, 30);
        root = tree.insert(root, 17, 19);
        root = tree.insert(root, 5, 20);
        root = tree.insert(root, 12, 15);
        root = tree.insert(root, 30, 40);
        List<IntervalNode> result = new ArrayList<IntervalNode>();
        tree.findInterval(root, 13, 17, result);
        for(IntervalNode node :result)  {
            System.out.println(node.low + "," + node.high );
        }
    }

    private IntervalNode insert(IntervalNode root, int low, int high)  {
        if(root == null)    {
            root = new IntervalNode(low, high, high);
            return root;
        }

        if(root.low > low)  {
            root.left = insert(root.left, low, high);
            if(root.max < high)
                root.max = high;
        }else   {
            root.right = insert(root.right, low, high);
            if(root.max < high)
                root.max = high;
        }
        return root;
    }

    public void findInterval(IntervalNode root, int low, int high, List<IntervalNode> result) {
        if(root == null)    {
            return ;//null;
        }

        if(insideInterval(root, low, high))    {
            result.add(root);
            //return root;
        }

        findInterval(root.right, low, high, result);
        findInterval(root.left, low, high, result);

/*
        if((root.left == null) || (root.left.max < low))   {
            findInterval(root.right, low, high, result);
        }else   {
            findInterval(root.left, low, high, result);
        }
*/
    }

    private boolean insideInterval(IntervalNode node, int low, int high)    {
        return (((node.low < high) && (node.high > low)) || ((node.low < low) && (node.high > low)));
    }

    class IntervalNode  {

        int low;
        int high;
        int max;
        IntervalNode left;
        IntervalNode right;

        public IntervalNode(int low, int high, int max)   {
            this.low = low;
            this.high = high;
            this.max = max;
        }
    }


}
