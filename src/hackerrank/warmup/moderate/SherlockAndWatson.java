package hackerrank.warmup.moderate;

import java.util.Scanner;

/**
 * Created by abhinav on 27/5/15.
 */
public class SherlockAndWatson {

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int Q = sc.nextInt();

        sc.nextLine();
        int[] input = new int[N];
        int[] query = new int[Q];
        for(int i = 0; i < N; i++)  {
           input[i] = sc.nextInt();
        }

        sc.nextLine();

        for(int i = 0; i < Q; i++)  {
            int indexToQuery = Integer.parseInt(sc.nextLine());
            query[i] = indexToQuery;
        }

        for(int i = 0; i < Q; i++) {
           System.out.println(findIndex(input, K, query[i]));
        }

        /*System.out.println(findIndex(new int[]{1,2,3,4,5,6,7}, 2, 0));
        System.out.println(findIndex(new int[]{1,2,3,4,5,6,7}, 2, 1));
        System.out.println(findIndex(new int[]{1,2,3,4,5,6,7}, 3, 2));
        System.out.println(findIndex(new int[]{1,2,3,4,5,6,7}, 3, 3));
        System.out.println(findIndex(new int[]{1,2,3,4,5,6,7}, 4, 4));*/
    }

    private static int findIndex(int[] orig, int k, int index) {
        int N = orig.length;
        int k1 = k % N;

        int prevIndex = index - k1;
        if (prevIndex < 0) {
            prevIndex = N + prevIndex;
            return orig[prevIndex];
        } else {
            return orig[prevIndex];
        }

    }
}
