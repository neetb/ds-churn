package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav on 29/7/15.
 */
public class GenerateParentheses {
    private static final char[] col = new char[]{'(',')'};

    public static void main(String[] args)  {
        GenerateParentheses test = new GenerateParentheses();
        test.generateParenthesis(3);
    }

    public List<String> generateParenthesis(int n) {
        String result = "";
        List<String> list = new ArrayList<String>();
        generateParenthesisUtil(result, 0, 0, n, list);
        System.out.println(list);
        return list;
    }

    public void generateParenthesisUtil(String result, int lcount, int rcount, int n, List<String> list) {
         if((lcount == n) && (rcount == n))  {
             list.add(result);
             return;
         }

        if(lcount < n) {
            generateParenthesisUtil(result.concat("("), lcount + 1, rcount, n, list);
        }

        if(lcount > rcount)    {
            generateParenthesisUtil(result.concat(")"), lcount, rcount + 1, n, list);
        }


      /*  for(int i = 0; i < 2; i++)  {

            if((i == 0) && (lcount == n )){
                continue;
            }

            if((i == 1) && (lcount <= rcount )){
                continue;
            }

            generateParenthesisUtil(result.concat(""+col[i]), lcount + 1 - i, rcount + i, n, list);
        }*/



        /*
        generateParenthesisUtil(result.concat("("), lcount + 1, rcount, n, list);
        generateParenthesisUtil(result.concat(")"), lcount + 1, rcount, n, list);
        ;

        if(lcount < n) {
             result.concat("(");
             generateParenthesisUtil(result, lcount + 1, rcount, n, list);
         }

         if(lcount > rcount)    {
             result.concat(")");
             generateParenthesisUtil(result, lcount, rcount + 1, n, list);
         }
*/
        //return list;
    }

}
