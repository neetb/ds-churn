package hackerrank.warmup.moderate;

import java.util.*;

/**
 * Created by abhinav on 27/5/15.
 */
public class NumberList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCaseCount = Integer.parseInt(sc.nextLine());
        int[] inputKs = new int[testCaseCount];
        List[] inputList = new List[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            String inputOne = sc.nextLine();
            String[] inputOneParts = inputOne.split(" ");
            int N = Integer.parseInt(inputOneParts[0]);
            int K = Integer.parseInt(inputOneParts[1]);
            inputKs[i] = K;
            inputList[i] = new ArrayList<Integer>();
            for (int j = 0; j < N; j++) {
                inputList[i].add(sc.nextInt());
            }
            if(i < testCaseCount - 1) {
                sc.nextLine();
            }
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println(find(inputList[i], inputKs[i]));

        }
    }

    private static int find(List<Integer> inputList, int k)   {
        int sum = 0;
        Integer[] input = new Integer[inputList.size()];
        input = inputList.toArray(input);
        Arrays.sort(input);
        if(input[input.length - 1] < k) {
            return sum;
        }

        int kIndex = binSearch(0, input.length-1, input, k);
        int start = kIndex < 0 ? Math.abs(kIndex): kIndex + 1;
        int end = input.length - 1;
        System.out.println(input[start-1]);
        System.out.println(input[start]);
        System.out.println(input[start+1]);

        for(int i = start; i <= end; i++)   {
           sum += i + 1;
        }

        return sum;
    }

    private static int binSearch(int lo, int hi, Integer[] input, int key) {
        if (lo > hi) {
            return 0 - lo;
        }

        int mid = (lo + hi) / 2;

        if (input[mid] > key) {
            return binSearch(lo, mid - 1, input, key);
        } else if (input[mid] < key) {
            return binSearch(mid + 1, hi, input, key);
        } else {
            return mid;
        }

    }

}
