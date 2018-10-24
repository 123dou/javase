package leetcode.intermediate.dynamicPlanning;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {216, 94, 15, 86};
        int amount = 5372;
        int i = coinChange4(coins, amount);
        System.out.println(i);
    }

    /**
     * 动态规划最优解
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange4(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            procedureCompletePack(amount, dp, coin);
        }
        return dp[amount] >= amount + 1 ? -1 : dp[amount];
    }

    /**
     * @param amount 兑换的面额
     * @param dp     保存前一轮的最优结果
     * @param coin   每枚硬币的面额
     */
    private static void procedureCompletePack(int amount, int[] dp, int coin) {
        for (int i = coin; i < amount + 1; i++) {
            if (coin < amount + 1) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }


    /**
     * 动态规划解法
     * 设f(i,v)为前i个硬币里面刚好可以兑换v的最小硬币数,则f(i,v) = min{f(i-1,v), f(i-1,v-k*coins[i]) + k*coins[i] | 0 < k*coins[i] < v}
     * /                         \
     * 这是不选第i个元素               第i个元素选k个
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange3(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1]; //索引为额度,值为该额度的最小硬币数
        for (int i = 1; i < amount + 1; i++) { //初始化coins[0]
            if (i % coins[0] == 0) dp[i] = i / coins[0];
            else dp[i] = -1;
        }
        //时间复杂度还可以再优化
        for (int i = 1; i < coins.length; i++) { //前i枚硬币
            for (int j = 1; j < amount + 1; j++) { //规划前i枚硬币 //这里有冗余,只需要从j=coins[i] 算起即可,而且当j是coins[i]的整数倍时,也会有冗余
                for (int k = 1; k * coins[i] <= j; k++) { //选k个元素
                    int pre = j - k * coins[i];
                    if (dp[pre] != -1) {
                        if (dp[j] != -1) dp[j] = Math.min(dp[pre] + k, dp[j]);
                        else dp[j] = dp[pre] + k;
                    }
                }
            }
        }
        return dp[amount];
    }


    /**
     * 用回溯法,并进行了剪枝,加入了硬币数少于当前最小的数目的时候才进行递归
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange2(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins == null || coins.length == 0 || coins.length == 1 && amount % coins[0] != 0) return -1;
        Arrays.sort(coins);
        int n = BSearch(coins, amount);
        if (coins[n] == amount) return 1;
        if (n == 0 && coins.length != 1) return -1;
        if (coins[n] > amount) coinChange2(coins, n - 1, amount, 0);
        else coinChange2(coins, n, amount, 0);
        if (minCount != Integer.MAX_VALUE) return minCount;
        else return -1;
    }

    static int minCount = Integer.MAX_VALUE; //记录最少的硬币数

    public static void coinChange2(int[] coins, int curr, int amount, int count) {
        if (amount == 0) minCount = Math.min(count, minCount);
        if (curr == 0) if (amount % coins[curr] == 0) minCount = Math.min(count + amount / coins[curr], minCount);
        else
            for (int i = amount / coins[curr]; i >= 0 && count + i < minCount; i--)
                coinChange2(coins, curr - 1, amount - i * coins[curr], count + i);
    }

    /**
     * @param coins
     * @param target
     * @return
     */
    public static int BSearch(int[] coins, int target) {
        int low = 0;
        int high = coins.length - 1;
        int mid;
        while (low < high) {
            mid = (low + high) >>> 1;
            if (target > coins[mid]) low = mid + 1;
            else high = mid;
        }
        return high;
    }
}
