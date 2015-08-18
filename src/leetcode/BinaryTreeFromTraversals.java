package leetcode;

import java.util.Arrays;

/**
 * Created by abhinav on 13/8/15.
 */
public class BinaryTreeFromTraversals {

        private static int index = 0;

        public static void main(String[] args)  {
            BinaryTreeFromTraversals test = new BinaryTreeFromTraversals();
            TreeNode root = test.buildTree(new int[]{1, 2}, new int[]{2, 1});
            //Arrays.sort();
            System.out.println(root.left.val);
        }

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if((preorder == null) || (preorder.length == 0) || (inorder == null) || (inorder.length == 0))  {
                return null;
            }

            return buildTreeUtil(preorder, inorder, 0, inorder.length - 1);
        }

        private TreeNode buildTreeUtil(int[] preorder, int[] inorder, int lo, int hi)    {
            if((lo > hi) || (index >= preorder.length))    {
                return null;
            }

            int item = preorder[index++];
            int inorderIndex = lookup(inorder, item, lo, hi);
            if(inorderIndex == -1)  {
                return null;
            }

            TreeNode root = new TreeNode(item);
            root.left = buildTreeUtil(preorder, inorder, lo, inorderIndex - 1);
            root.right = buildTreeUtil(preorder, inorder, inorderIndex + 1, hi);
            return root;
        }

        private int lookup(int[] inorder, int item, int lo, int hi) {
            if(lo > hi) {
                return -1;
            }

            for(int i = lo; i <= hi; i++)   {
                if(inorder[i] == item)  {
                    return i;
                }
            }

            return -1;
        }

      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

}
