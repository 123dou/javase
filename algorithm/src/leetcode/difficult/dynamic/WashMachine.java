package leetcode.difficult.dynamic;

import sun.text.resources.cldr.naq.FormatData_naq;

/**
 * 假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 * <p>
 * 在每一步操作中，你可以选择任意 m （1 ≤ m ≤ n） 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 * <p>
 * 给定一个非负整数数组代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的最少的操作步数。如果不能使每台洗衣机中衣物的数量相等，则返回 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: [1,0,5]
 * <p>
 * 输出: 3
 * <p>
 * 解释:
 * 第一步:    1     0 <-- 5    =>    1     1     4
 * 第二步:    1 <-- 1 <-- 4    =>    2     1     3
 * 第三步:    2     1 <-- 3    =>    2     2     2
 * 示例 2：
 * <p>
 * 输入: [0,3,0]
 * <p>
 * 输出: 2
 * <p>
 * 解释:
 * 第一步:    0 <-- 3     0    =>    1     2     0
 * 第二步:    1     2 --> 0    =>    1     1     1
 * 示例 3:
 * <p>
 * 输入: [0,2,0]
 * <p>
 * 输出: -1
 * <p>
 * 解释:
 * 不可能让所有三个洗衣机同时剩下相同数量的衣物。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n 的范围是 [1, 10000]。
 * 在每台超级洗衣机中，衣物数量的范围是 [0, 1e5]。
 */
public class WashMachine {
    public static void main(String[] args) {
        int[] machines = {1, 0, 5};
        int i = findMinMoves(machines);
        System.out.println(i);
    }

    /**
     * 思路:设i为第i个位置,则考量第i个位置的左边(不包括i)需要的是输出还是输入,然后再考量i的(也不包括i)右边需要的是输出还是输入
     * 令l代表左边,r代表右边,大于零代表输出,小于零代表输入
     * 则当
     *
     * @param machines
     * @return
     */
    public static int findMinMoves(int[] machines) {
        if (machines == null) return -1;
        if (machines.length == 0 || machines.length == 1) return 0;
        int sum = 0;
        for (int m : machines) {
            sum += m;
        }
        if (sum % machines.length != 0) return -1;
        int average = sum / machines.length;
        int minMove = 0;
        int presum = machines[0];
        int l, r;
        for (int i = 1; i < machines.length; i++) {
            l = presum - average * i;
            r = sum - presum - machines[i] - (average * (machines.length - i - 1));
            if (l < 0 && r < 0) minMove = Math.max(minMove, -l - r); //当这种情况是时,i位置是不能同时输入和输出的
            else minMove = Math.max(minMove, Math.max(Math.abs(l), Math.abs(r))); //其他情况,都可以同时进行,故取最大值即可
            presum += machines[i];
        }
        return minMove;
    }
}
