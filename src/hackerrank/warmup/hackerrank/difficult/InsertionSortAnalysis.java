package hackerrank.warmup.hackerrank.difficult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ramneet on 18/6/15.
 */
public class InsertionSortAnalysis {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        List[] input = new List[T];
        long[] output = new long[T];
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(sc.nextLine().trim());
            String[] inputStr = sc.nextLine().trim().split(" ");
            /*input[i] = new ArrayList<Integer>();
            for (int j = 0; j < N; j++) {
                input[i].add(Integer.parseInt(inputStr[j]));
            }*/
            //output[i] = findTotalInversions((ArrayList<Integer>) input[i]);
            output[i] = findTotalInversions(inputStr);

        }

        for (int i = 0; i < T; i++) {
            System.out.println(output[i]);
        }
    }
    public static Long findTotalInversions(String[] input) {
        Node root = null;
        long count = 0;
        long N = input.length;

        if(isSorted(input))  {
            return 0L;
        }

        if(isReverseSorted(input))  {
            count = (N * (N - 1)) / 2;
            return count;
        }

        for(int i = 0; i < input.length; i++)   {
            int num = Integer.parseInt(input[i]);
            Result res = new Result(num, 0);
            root = insert(root, num, res);
            count = count + res.count;
        }

        return count;
    }

    private static boolean isReverseSorted(String[] input) {
        for(int i = 0; i < input.length - 1; i++)   {
            if(Integer.parseInt(input[i+1].trim()) > Integer.parseInt(input[i].trim()))   {
                return false;
            }
        }
        return true;
    }


    private static boolean isSorted(String[] input) {
        for(int i = 0; i < input.length - 1; i++)   {
            if(Integer.parseInt(input[i+1].trim()) < Integer.parseInt(input[i].trim()))   {
                return false;
            }
        }
        return true;
    }

    public static Node insert(Node root, int num, Result res)  {
        if(root == null)    {
            root = new Node(num);
            return root;
        }

        if(num < root.item) {
            root.left = insert(root.left, num, res);
            res.count = res.count + root.rSize + root.frequency;
        }else if(num > root.item) {
            root.right = insert(root.right, num, res);
            root.rSize = root.rSize + 1;
        }else{
             res.count = res.count + root.rSize;
             root.frequency++;
        }
        return root;
    }

    static class Node  {
        int item;
        Node left;
        Node right;
        int rSize;
        int frequency;

        public Node(int item)   {
            this.item = item;
            this.left = null;
            this.right = null;
            this.rSize = 0;
            this.frequency = 1;
        }

    }

    static class Result    {
        int item;
        long count;

        public Result(int item, long count)  {
            this.item = item;
            this.count = count;
        }
    }

}


