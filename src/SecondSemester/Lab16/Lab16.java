package SecondSemester.Lab16;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab16 {

    static int[][] A;
    static List<Integer> ans;
    static int[] w;

    public static void findAns(int k, int s) {
        if (A[k][s] == 0) {
            return;
        }
        if (A[k - 1][s] == A[k][s]) {
            findAns(k - 1, s);
        } else {
            findAns(k - 1, s - w[k-1]);
            ans.add(k);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int W = scanner.nextInt();

        A = new int[N + 1][W + 1];
        w = new int[N];
        ans = new ArrayList<>();

        int[] p = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = scanner.nextInt();
        }

        for (int i = 0; i < N; i++) {
            w[i] = scanner.nextInt();
        }

        for (int k = 1; k <= N; k++) {
            for (int s = 1; s <= W; s++) {
                if (s >= w[k-1]) {
                    A[k][s] = Math.max(A[k - 1][s], A[k - 1][s - w[k-1]] + p[k-1]);
                } else {
                    A[k][s] = A[k - 1][s];
                }
            }
        }

        findAns(N, W);
        System.out.println(ans);
    }

}
