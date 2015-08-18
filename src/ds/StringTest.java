package ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav on 4/6/15.
 */
public class StringTest {


    public static void main(String[] args) {
       /* List<String> res = new ArrayList<String>();
        findPermutations("", "abcd", res);
        System.out.println("Size : " + res.size());
        for(String s : res) {
            System.out.println(s);
        }*/

        perm("abcd".toCharArray(), 4);
    }


    private static List<String> findPermutations(String prefix, String str, List<String> res) {
        int len = str.length();
        if(len == 0)    {
         // System.out.println(prefix);
            res.add(prefix);
            return res;
        }else   {
            for(int i = 0;i < len; i++) {
                res = findPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1), res);
            }
        }

        return res;
    }

    private static void perm(char[] a, int n)   {
        if(n == 1) {
            System.out.println(a);
            return;
        }

        for(int i = 0;i < n; i++)   {
         swap(a, i, n-1);
            perm(a, n-1);
            swap(a, i, n-1);
        }
    }

    private static void swap(char[] a, int i, int j)  {
      char c;
      c = a[i];
      a[i] = a[j];
      a[j] = c;

    }
}
