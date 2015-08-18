package leetcode;

import java.util.Stack;

/**
 * Created by abhinav on 29/7/15.
 */
public class TrappingRainWater {

    public static void main(String[] args)  {
        TrappingRainWater test = new TrappingRainWater();
        System.out.println(test.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(test.trap(new int[]{3,0,5}));

        System.out.println(test.trapV1(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(test.trapV1(new int[]{3,0,5}));
    }

    public int trap(int[] height) {
      if((height == null) || (height.length == 0) || (height.length == 1))  {
          return 0;
      }

      Stack<Integer> stack = new Stack<Integer>();
      stack.push(height[0]);
      int max = height[0];
      int result = 0;

      for(int i = 1; i < height.length; i++) {
         if(height[i] > max)    {
             while(!stack.isEmpty())    {
                 int item = stack.pop();
                 result += max - item;
             }
             stack.push(height[i]);
             max = height[i];
         }else  {
             stack.push(height[i]);
         }
      }

      if(stack.isEmpty())   {
          return result;
      }else {
          int top = stack.pop();
          while(!stack.isEmpty())    {
            int item = stack.pop();
            if(item > top)  {
                top = item;
            }else   {
                result += top - item;
            }
          }
          return result;
      }
    }

    public int trapV1(int[] height) {
        if ((height == null) || (height.length == 0) || (height.length == 1)) {
            return 0;
        }

        int N = height.length;
        int[] maxLeft = new int[N];
        int[] maxRight = new int[N];
        maxLeft[0] = height[0];
        maxRight[N-1] = height[N-1];

        for(int i = 1; i < height.length; i++)  {
            if(height[i] > maxLeft[i-1])    {
                maxLeft[i] = height[i];
            }else   {
                maxLeft[i] = maxLeft[i-1];
            }
        }

        for(int i = N - 2; i >= 0; i--)  {
            if(height[i] > maxRight[i+1])    {
                maxRight[i] = height[i];
            }else   {
                maxRight[i] = maxRight[i+1];
            }
        }

        int res = 0;
        for(int i = 1; i < N; i++)  {
            res += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }

        //System.out.println(res);
        return res;
    }

}
