package leetcode;

import java.util.Arrays;

/**
 * Created by abhinav on 23/7/15.
 */
public class LongestCommonPrefix {

    private Node root;

    public static void main(String[] args)  {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        int[] nums = new int[]{1,2,3,4,5,5,5,6,6,7,7};
        System.out.println(lcp.removeDuplicates(nums));
        for(int i = 0; i < nums.length; i++)    {
            System.out.println(nums[i]);
        }
     /*   System.out.println(lcp.longestCommonPrefix(new String[]{"abcf","aghghj","abioiio"}));
        System.out.println(lcp.longestCommonPrefix(new String[]{}));
        System.out.println(lcp.longestCommonPrefix(new String[]{"abcf"}));
        System.out.println(lcp.longestCommonPrefix(new String[]{"abcf","abcdffff","aaaaaa"}));
        System.out.println(lcp.longestCommonPrefix(new String[]{"dd","d","abioiio"}));
        System.out.println(lcp.longestCommonPrefix(new String[]{"dd","d"}));*/
    }

    public int removeDuplicates(int[] nums) {
        int dups = 0;
        if((nums == null) || (nums.length == 0))    {
            return dups;
        }

        int N = nums.length;
        for(int i = 0; i < N; i++)    {
            int dupCount = 0;
            int k = i;

            //find duplicates for the ith element
            while((k < N-1) && (nums[k] == nums[k+1]))    {
                dupCount++;
                k++;
            }

            // dupCount duplicate elements
            // shift elements starting after i, jump dupCount element while shifting
            if(dupCount > 0)    {
                for(int j = i+1; j < N-dupCount; j++)   {
                    nums[j] = nums[j + dupCount];
                }
                N = N - dupCount;
            }
        }
        return N;
    }

    public String longestCommonPrefixv1(String[] strs) {
        String prefix = "";
        if((strs == null) || (strs.length == 0))  {
           return prefix;
        }

        Arrays.sort(strs);

        if(strs.length == 1)    {
            return strs[0];
        }

        int prefixLen = findPrefix(strs[0], strs[1]);
        prefix = strs[0].substring(0, prefixLen);

        for(int i = 2; i < strs.length; i++)    {
            int len = findPrefix(prefix, strs[i]);
            if(len < prefix.length()) {
                prefix = prefix.substring(0, len);
            }

            if(len == 0)    {
                break;
            }
        }

        return prefix;
    }

    private int findPrefix(String str1, String str2)    {
        int result = 0;
        if((str1 == null) || (str1.isEmpty()) || (str2 == null) || (str2.isEmpty()))    {
            return result;
        }

        int len = str1.length() < str2.length() ? str1.length() : str2.length();
        for(int i = 0; i < len ; i++)   {
            if(str1.charAt(i) == str2.charAt(i))    {
                result++;
            }else   {
                break;
            }
        }

        return result;
    }

    //Using ternery search trees
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";
        if((strs == null) || (strs.length == 0))  {
            return prefix;
        }


        if(strs.length == 1)    {
            return strs[0];
        }

        if(strs.length == 2) {
            int prefixLen = findPrefix(strs[0], strs[1]);
            prefix = strs[0].substring(0, prefixLen);
            return prefix;
        }

        for(int i = 0; i < strs.length; i++)    {
            String input = strs[i];
            for(int j = 0; j < input.length(); j++)   {

            }
        }
        Node root = new Node(strs[0]);
        return null;


    }

    class Node  {
        Node left;
        Node mid;
        Node right;
        String data;

        public Node(String data)    {
            this.data = data;
        }


    }
}
