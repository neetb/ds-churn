package hackerrank.warmup;

import java.util.Scanner;

/**
 * Created by ramneet on 14/5/15.
 */
public class ServiceLane {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int T = scanner.nextInt();
        int[] widths = new int[N];

        //as nextInt does not read the line-end
        scanner.nextLine();
        String nextLine = scanner.nextLine();

        String[] widthValues = nextLine.split(" ");
        for (int i = 0; i < N; i++) {
            widths[i] = Integer.parseInt(widthValues[i]);
        }

        String[] inputLines = new String[T];
        int j = 0;
        while (j < T) {
            String input = scanner.nextLine();
            inputLines[j++] = input;
        }

        for (int i = 0; i < T; i++) {
            String[] indexes = inputLines[i].split(" ");
            System.out.println(find(widths, Integer.parseInt(indexes[0]), Integer.parseInt(indexes[1])));
        }

    }

    private static int find(int[] widths, int start, int end) {
        int minValue = 4;
        for (int i = start; i <= end; i++) {
            if (widths[i] < minValue) {
                minValue = widths[i];
            }
        }
        return minValue;
    }
}
