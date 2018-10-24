package niukewang;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/bf877f837467488692be703735db84e6
 * 来源：牛客网
 * <p>
 * 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容量为 w。
 * 牛牛家里一共有 n 袋零食, 第 i 袋零食体积为 v[i]。
 * 牛牛想知道在总体积不超过背包容量的情况下, 他一共有多少种零食放法 (总体积为 0 也算一种放法)。
 * <p>
 * 输入描述:
 * 输入包括两行
 * 第一行为两个正整数n和w(1 <= n <= 30, 1 <= w <= 2 * 10^9),表示零食的数量和背包的容量。
 * 第二行n个正整数v[i](0 <= v[i] <= 10^9),表示每袋零食的体积。
 * <p>
 * <p>
 * 输出描述:
 * 输出一个正整数, 表示牛牛一共有多少种零食放法。
 * 示例 1
 * 输入
 * 3 10
 * 1 2 4
 * 输出
 * 8
 * 说明
 * 三种零食总体积小于10,于是每种零食有放入和不放入两种情况，一共有2*2*2 = 8种情况。
 * <p>
 * 链接：https://www.nowcoder.com/questionTerminal/d94bb2fa461d42bcb4c0f2b94f5d4281
 * 来源：牛客网
 * <p>
 * 21 1165911996
 * 842104736 130059605 359419358 682646280 378385685 622124412 740110626 814007758 557557315 40153082 542984016 274340808
 * 991565332 765434204 225621097 350652062 714078666 381520025 613885618 64141537 783016950
 */
public class CowCowBackpack {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int w = in.nextInt();
        long[] v = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            v[i] = in.nextInt();
            sum += v[i];
        }
        if (sum <= w) { //特殊情况
            System.out.println((long) Math.pow(2, n));
            return;
        }
        Arrays.sort(v);
        for (int i = 1; i <= v.length; i++) { //每次组合的个数
            getSum(0, v, 0, w, i, 0);
        }
        System.out.println(SUM + 1);
    }

    public static int SUM = 0; //总放法

    /**
     * 时间复杂度过高
     * 抽取所有的个数depth的所有组合
     *
     * @param depth 当前递归的深度
     * @param v     当前给定的物品的重量
     * @param sum   子集的和
     * @param w     允许物品的最大总重量
     * @param count 本次递归允许的最大深度
     * @param index 遍历的数组索引
     */
    public static int getSum(int depth, long[] v, long sum, int w, int count, int index) {
        if (sum > w) return -1;
        if (depth == count) {
            SUM++;
            return 0;
        }
        for (int i = index; i < v.length; i++) {
            sum += v[i];
            //v数组是排好序的,所以当sum > w时,后面的就可以不用执行了
            if (getSum(depth + 1, v, sum, w, count, i + 1) == -1) break;
            if (sum != 0) sum -= v[i]; //回溯
        }
        return 0;
    }

    /**
     * 复杂度还是太高了
     *
     * @param v      v[i]表示物品的重量
     * @param index  当前递归的深度
     * @param w      给定的最大重量
     * @param marked 记录遍历过的值
     */
    public static void getSum(int[] v, int index, int w, boolean[] marked) {
        if (index == v.length) {
            int sum = 0;
            for (int i = 0; i < v.length; i++) if (marked[i]) sum += v[i];
            if (sum <= w) SUM++;
            return;
        }
        marked[index] = true; //走过的点设为true;
        getSum(v, index + 1, w, marked);
        marked[index] = false; //回溯
        getSum(v, index + 1, w, marked);
    }
}
