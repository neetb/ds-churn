package hackerrank.warmup.hackerrank.easy;

import java.util.Scanner;

/**
 * Created by abhinav on 27/5/15.
 */
public class PalindromeIndex {

    public static void main(String[] args)  {

        String test = "Ramneet";
        System.out.println(test.substring(0,0));
        System.out.println(test.substring(0,1));

        char[] ch = test.toCharArray();
        System.out.println((int)'A');

           int start = 0;
            int end = ch.length - start - 1;
        while(start<end){
            System.out.println(ch[start] ^ ch[end]);
            ch[start] = (char) (ch[start] ^ ch[end]);
            ch[end] = (char) (ch[start] ^ ch[end]);
            ch[start] = (char) (ch[start] ^ ch[end]);
            start++;
            end--;
         }

        System.out.println(ch);
        System.out.println(new String(ch));
      /*  Scanner sc = new Scanner(System.in);
        int testCaseCount = Integer.parseInt(sc.nextLine());
        String[] input = new String[testCaseCount];

        for(int i = 0;i < testCaseCount; i++)   {
            input[i] = sc.nextLine();
        }

        for(int i = 0;i < testCaseCount; i++)   {
            System.out.println(findPalindromeIndex(input[i]));

        }*/
    }

    private static int findPalindromeIndex(String input)   {
       char[] inputArr = input.toCharArray();
       int i = 0;
       int j = input.length() - 1;
       int index = -1;

        while(true)  {
           if(j < i)    {
             break;
           }

           if((inputArr[i] != inputArr[j]) && ((j - i) > 1))  {
              index = (inputArr[i] == inputArr[j - 1]) && (inputArr[i+1] == inputArr[j - 2]) ? j : i;
              break;
           }

           i++;
           j--;
       }

       return index;
    }


}
