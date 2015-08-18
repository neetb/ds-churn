package hackerrank.warmup;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by abhinav on 15/5/15.
 */
public class JimAndTheSkyCrapers {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] heights = new int[N];

        String heightsLine = sc.nextLine();
        String[] heightVals = heightsLine.split(" ");

        for(int i = 0;i < N; i++)    {
            heights[i] = Integer.parseInt(heightVals[i]);
        }

        Solution s = new Solution();
        long start = System.currentTimeMillis();
        System.out.println(findPossiblePathsV5(heights));
        System.out.println("time taken by v1 : " + (System.currentTimeMillis() - start));


    }

    private static int findPossiblePathsV1(int[] heights)    {
        int count = 0;
        for(int i = 0; i < heights.length - 1; i++) {
            for(int j = i+1; j < heights.length; j++) {
               if(heights[i] == heights[j]) {
                   count++;
                   continue;
               }

               if(heights[j] > heights[i])  {
                   break;
               }
            }
        }
        return count*2;
    }

    private static int findPossiblePathsV2(int[] heights)    {
        int count = 0;
        int max = Integer.MIN_VALUE;
        Map<Integer,Integer> startIndexes = new HashMap<Integer, Integer>();
        Map<Integer,Integer> counts = new HashMap<Integer, Integer>();
        int maxIndex = -1;
        for(int i = 0; i < heights.length; i++) {
                int currentHt = heights[i];

                if(currentHt > max) {
                    max = currentHt;
                    maxIndex = i;
                }

                Integer val = counts.get(currentHt);
                if(val != null)  {
                    int startIndex = startIndexes.get(currentHt);
                    if(startIndex >= maxIndex)   {
                        count += val;
                        counts.put(currentHt, val + 1);
                    } else  {
                        startIndexes.put(currentHt, i);
                        counts.put(currentHt, 1);
                    }
                }else   {
                    counts.put(currentHt, 1);
                    startIndexes.put(currentHt, i);
                }
        }
        return count*2;
    }

    private  int findPossiblePathsV3(int [] heights) {
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

                if ((!stack.isEmpty()) && (stack.peek().value == heights[i])){
                    Item item = stack.peek();
                    pathCount += item.count;
                    item.count++;
                }
            }
        }
        return pathCount * 2;
    }

    private static int findPossiblePathsV4(int[] input)    {
        Stack<Integer> st = new Stack<Integer>();
        Stack<Integer> temp = new Stack<Integer>();

        int count  = 0;
        for(int i = 0; i < input.length; i++)   {
            if(st.isEmpty())    {
                st.push(input[i]);
            }else   {
                while(!(st.isEmpty()) && (input[i] > st.peek())) {
                    st.pop();
                }

                while(!(st.isEmpty()) && (input[i] == st.peek())) {
                    temp.push(st.pop());
                    count++;
                }

                while(!temp.isEmpty())  {
                    st.push(temp.pop());
                }

                st.push(input[i]);
            }
        }
        return count*2;
    }

    private static long findPossiblePathsV5(int[] input)    {
        Stack<Item> st = new Stack<Item>();

        long count  = 0;
        for(int i = 0; i < input.length; i++)   {
            int currentCount = 0;
            if(st.isEmpty())    {
                st.push(new Item(input[i], currentCount));
            }else   {
                while(!(st.isEmpty()) && (input[i] > st.peek().value)) {
                    st.pop();
                }

                if(!(st.isEmpty()) && (input[i] == st.peek().value)) {
                    st.peek().count++;
                    count += st.peek().count;
                    continue;
                }

                st.push(new Item(input[i], currentCount));
            }
        }
        return count*2;
    }


    static class Item  {
        int value;
        int count;

        public Item(int value, int count)   {
            this.value = value;
            this.count = count;
        }


    }
}
