package testDemo;

import java.util.Scanner;

public class T {
    private static int res = 1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 1; i <= n / 2 + 1; i++) {
            getCount(n, i, 1, 0);
        }
        System.out.println(res);
    }

    private static void getCount(int n, int depth, int index, int count) {
        if (count == depth) {
            res++;
            return;
        }
        if (index > n) return;
        for (int i = index; i <= n; i++) {
            getCount(n, depth, i + 2, count + 1);
        }
    }
}