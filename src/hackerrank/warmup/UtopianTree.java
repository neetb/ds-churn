package hackerrank.warmup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ramneet on 14/5/15.
 */
public class UtopianTree {

    public static void main(String[] args) {
        List<Integer> cycles = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        int numberOfTests = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= numberOfTests; i++) {
            cycles.add(Integer.parseInt(sc.nextLine()));
        }

        for(Integer cycle : cycles)    {
            System.out.println(findHeight(cycle));
        }

    }

    private static int findHeight(int cycles) {
        if (cycles == 0) {
            return 1;
        }

        if (cycles % 2 == 0) {
            return 1 + findHeight(cycles - 1);
        } else {
            return 2 * (findHeight(cycles - 1));
        }
    }
}
