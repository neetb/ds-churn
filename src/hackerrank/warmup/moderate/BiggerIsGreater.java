package hackerrank.warmup.moderate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by abhinav on 19/5/15.
 */
public class BiggerIsGreater {

    public static String findNextBigPerm(String input)    {
        if((input == null) || (input.length() <= 1))   {
            return "no answer";
        }

        //replace with stringbuilders?
        String result =  nextBigPerm(input.substring(0,1),input.substring(1));
        if(result.equals(input))    {
            return "no answer";
        }
        return result;
    }

    public static String findNextBigPermv2(String input)    {
        if((input == null) || (input.length() <= 1))   {
            return "no answer";
        }

        //replace with stringbuilders?
        String result =  nextBigPermv2(input,0,input.length()-1);
        if(result.equals(input))    {
            return "no answer";
        }
        return result;
    }

    private static String nextBigPerm(String first, String rem)   {
        if(rem.length() == 1)   {
            //base case
            if(first.compareTo(rem) < 0)  {
                return rem.concat(first);
            }else   {
                return first.concat(rem);
            }
        }

        String returnedRem = nextBigPerm(rem.substring(0,1), rem.substring(1));
        StringBuilder firstSB = new StringBuilder(first);
        StringBuilder restSB = new StringBuilder(returnedRem);
        if(returnedRem.equals(rem))    {
            char[] remArray = returnedRem.toCharArray();

            //faster sort - radix sort?
            Arrays.sort(remArray);
            int len = remArray.length;
            char firstCh = first.charAt(0);

            if(firstCh > remArray[len - 1]) {
                return firstSB.append(restSB).toString();
            }

            for (int i = 0;i < remArray.length; i++)    {
                if(firstCh < remArray[i])   {
                    char temp = remArray[i];
                    remArray[i] = firstCh;
                    first = String.valueOf(temp);
                    returnedRem = String.valueOf(remArray);
                    firstSB = new StringBuilder(first);
                    restSB = new StringBuilder(returnedRem);
                    break;
                }
            }
        }
        return firstSB.append(restSB).toString();
    }

    private static String nextBigPermv2(String input, int start, int end)   {
        if(end - start == 1)   {
            //base case
            char first = input.charAt(start);
            char sec = input.charAt(end);
            if(first < sec)  {
                char[] arr = input.toCharArray();
                arr[end] = first;
                arr[start] = sec;
                return String.valueOf(arr);
            }else   {
                return input;
            }
        }

        String returnedInput = nextBigPermv2(input, start + 1, end);
        if(returnedInput.equals(input))    {
            char[] inputArr = returnedInput.toCharArray();

            //faster sort - radix sort?
            Arrays.sort(inputArr, start+1, end+1);
            int len = inputArr.length;
            char firstCh = input.charAt(start);

            if(firstCh > inputArr[len - 1]) {
                return input;
            }

            for (int i = start+1;i <= end; i++)    {
                if(firstCh < inputArr[i])   {
                    char temp = inputArr[i];
                    inputArr[i] = firstCh;
                    inputArr[start] = temp;
                    return String.valueOf(inputArr);
                }
            }
        }
        return returnedInput;
    }


    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int testCaseCount = Integer.parseInt(sc.nextLine());
        List<String> inputs = new ArrayList<String>();
        for(int i = 0; i < testCaseCount; i++)  {
            inputs.add(sc.nextLine().trim());
        }

        for(String input : inputs)  {
            long start = System.currentTimeMillis();
            System.out.println(findNextBigPerm(input));
            System.out.println(System.currentTimeMillis() - start);
        }
        for(String input : inputs)  {
           long start1 = System.currentTimeMillis();
            System.out.println(findNextBigPermv2(input));
            System.out.println(System.currentTimeMillis() - start1);
        }

        //The diff in time taken by two methods show that string concat at every step is very expensive even though it
        //was replaced by builder(did any good?).
        //Better way is to modify the underlying array and get a new string

    }

}
