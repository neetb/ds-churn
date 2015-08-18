package hackerrank.warmup.hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by abhinav on 19/5/15.
 */
public class SherlockAndArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCaseCount = Integer.parseInt(sc.nextLine());
        List<int[]> inputs = new ArrayList();
        for (int i = 0; i < testCaseCount; i++) {
            int N = Integer.parseInt(sc.nextLine());
            int[] input = new int[N];
            for (int j = 0; j < N; j++) {
                input[j] = sc.nextInt();
            }
            inputs.add(input);
            if(sc.hasNextLine())    {
                sc.nextLine();
            }
        }

        for (int[] input : inputs) {
            if (containsPivotElement(input)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean containsPivotElement(int[] input) {
        int N = input.length;
        int[] leftSum = new int[N];
        int[] rightSum = new int[N];

        for (int i = 0; i < N; i++) {
            int rightIndex = N - 1 - i;
            if (i == 0) {
                leftSum[i] = 0;
                rightSum[rightIndex] = 0;
            } else {
                leftSum[i] = leftSum[i - 1] + input[i - 1];
                rightSum[rightIndex] = rightSum[rightIndex + 1] + input[rightIndex + 1];
            }
        }

        for (int i = 0; i < N; i++) {
            if (leftSum[i] == rightSum[i]) {
                return true;
            }
        }
        return false;
    }
}
