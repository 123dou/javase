package niukewang.DynamicPlanning;

import java.util.Scanner;

/**
 * 有 n 个学生站成一排，每个学生有一个能力值，牛牛想从这 n 个学生中按照顺序选取 k 名学生，要求相邻两个学生的位置编号的差不超过 d，
 * 使得这 k 个学生的能力值的乘积最大，你能返回最大的乘积吗？
 * 输入描述:
 * 每个输入包含 1 个测试用例。每个测试数据的第一行包含一个整数 n (1 <= n <= 50)，表示学生的个数，接下来的一行，包含 n 个整数，
 * 按顺序表示每个学生的能力值 ai（-50 <= ai <= 50）。接下来的一行包含两个整数，k 和 d (1 <= k <= 10, 1 <= d <= 50)。
 * <p>
 * <p>
 * 输出描述:
 * 输出一行表示最大的乘积。
 * <p>
 * 输入例子 1:
 * 3
 * 7 4 7
 * 2 50
 * <p>
 * 输出例子 1:
 * 49
 * 链接：https://www.nowcoder.com/questionTerminal/661c49118ca241909add3a11c96408c8
 * 来源：牛客网
 * <p>
 * 47
 * -41 -5 -10 -31 -44 -16 -3 -33 -34 -35 -44 -44 -25 -48 -16 -32 -37 -8 -33 -30 -6 -18 -26 -37 -40 -30 -50 -32 -5 -41 -32 -12 -33 -22 -14 -34 -1 -41 -45 -8 -39 -27 -23 -45 -10 -50 -34
 * 6 3
 * <p>
 * 链接：https://www.nowcoder.com/questionTerminal/661c49118ca241909add3a11c96408c8
 * 来源：牛客网
 * <p>
 * 45
 * -26 21 -24 31 13 6 -9 -34 -44 40 10 40 -42 -32 12 -14 -12 37 -25 -8 -16 -10 36 24 0 -33 9 -28 48 13 44 -8 -44 28 -37 49 50 -34 45 41 -8 -42 3 -7 11
 * 1 2
 */
public class Choir {
    /**
     * 思路还是动态规划:
     * 链接：https://www.nowcoder.com/questionTerminal/661c49118ca241909add3a11c96408c8
     * 来源：牛客网
     * <p>
     * 一般的动态规划题目，中间使用的表的最后一个元素，dp[N][K] 就是所求的结果。但这个题目不能这样，因为如果那样建表，
     * 子问题就成了 “在前 n 个学生中，取 k 个，使乘积最大”——然而，本题目有额外的限制条件 “相邻两个学生的位置编号的差不超过 d” 就没有办法代入递推公式了，
     * 因为子问题中本身并不包含位置信息。
     * 因此将子问题定为：在前 n 个学生中，取 k 个，第 n 个必取，使乘积最大。这样的话，最终的结果就不是 dp[N][K]，而是 dp[..][K] 这一列中最大的那个值。
     * 其次，求最大乘积比求最大和的问题要复杂许多。求最大和的话，子问题中也只需要求最大和就行了。但求最大乘积的时候，在子问题中，每一步需要求最大正积和最小负积。
     * 因为如果某学生的能力值为负数，乘以前面求得的最小负积，结果才是最大乘积。
     * 再次，这个问题最后算的数据比较大，已经不是 int 型能够包含的了，需要用 long long。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        int k = in.nextInt();
        int d = in.nextInt();
        if (n < k) {
            System.out.println(0);
            return;
        }
        if (k == 1 || n == 1) {
            int max = array[0];
            for (int i = 1; i < n; i++) {
                max = array[i] > max ? array[i] : max;
            }
            System.out.println(max);
            return;
        }
        if (n == k) {
            int result = array[0];
            for (int i = 0; i < n; i++) {
                result *= array[i];
            }
            System.out.println(result);
            return;
        }
        long[][] dp_max = new long[k + 1][n + 1]; //dp_max[i][j]表示选出第i个数,且是以第array[j]结尾的满足给定条件的最大i个数乘积
        long[][] dp_min = new long[k + 1][n + 1]; //dp_min[i][j]表示选出第i个数,且是以第array[j]结尾的满组给定条件的最小i个数乘积
        //因为乘数中有负数参与,所以需要维护两个数组,一个储存最大值,一个储存最小值
        //则dp_max[i][j] = max{dp_max[i][j], max{dp_max[i - 1][k] * array[j - 1], dp_min[i - 1][k] * array[j - 1]}}其中 k > 0 && k < j && j - k <= d;
        //则dp_max[i][j] = min{dp_max[i][j], min{dp_max[i - 1][k] * array[j - 1], dp_min[i - 1][k] * array[j - 1]}}其中 k > 0 && k < j && j - k <= d;
        for (int i = 1; i < n + 1; i++) { //初始化边界
            dp_max[1][i] = array[i - 1];
            dp_min[1][i] = array[i - 1];
        }
        long result = Long.MIN_VALUE;
        for (int i = 2; i < k + 1; i++) {
            for (int j = i; j < n + 1; j++) {
                dp_max[i][j] = Long.MIN_VALUE;
                dp_min[i][j] = Long.MAX_VALUE;
                int temp = j - 1;
                while (temp > 0 && j - temp <= d) {
                    dp_max[i][j] = Math.max(dp_max[i][j], Math.max(dp_max[i - 1][temp] * array[j - 1], dp_min[i - 1][temp] * array[j - 1]));
                    dp_min[i][j] = Math.min(dp_min[i][j], Math.min(dp_max[i - 1][temp] * array[j - 1], dp_min[i - 1][temp] * array[j - 1]));
                    temp--;
                }
                result = Math.max(result, dp_max[i][j]);
            }
        }
        System.out.println(result);
    }
}
