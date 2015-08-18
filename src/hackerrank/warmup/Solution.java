package hackerrank.warmup;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by abhinav on 15/5/15.
 */
public class Solution {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] heights = new int[N];

        String heightsLine = sc.nextLine();
        String[] heightVals = heightsLine.split(" ");

        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(heightVals[i]);
        }

        Solution s = new Solution();
        System.out.println(s.findPossiblePathsV3(heights));
    }


    private int findPossiblePathsV3(int[] heights) {
        Stack<Item> stack = new Stack<Item>();
        int pathCount = 0;
        for (int i = 0; i < heights.length; i++) {
            if (stack.isEmpty()) {
                stack.push(new Item(heights[i], 1));
                continue;
            }

            if (stack.peek().value > heights[i]) {
                stack.push(new Item(heights[i], 1));
            } else {
                while ((!stack.isEmpty()) && (stack.peek().value < heights[i])) {
                    stack.pop();
                }

                if ((!stack.isEmpty()) && (stack.peek().value == heights[i])) {
                    Item item = stack.peek();
                    pathCount += item.count;
                    item.count++;
                   // stack.push(item);
                }else   {
                    stack.push(new Item(heights[i], 1));
                }
            }
        }
        return pathCount * 2;
    }


    class Item {
        int value;
        int count;

        public Item(int value, int count) {
            this.value = value;
            this.count = count;
        }


    }
}
