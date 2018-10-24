package leetcode.dynamic;

/**
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * <p>
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 * <p>
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 * <p>
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 * <p>
 * 示例:
 * <p>
 * n = 10, 我选择了8.
 * <p>
 * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
 * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
 * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
 * <p>
 * 游戏结束。8 就是我选的数字。
 * <p>
 * 你最终要支付 5 + 7 + 9 = 21 块钱。
 * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 */
public class GuessNumberHigherOrLowerII {

    public static void main(String[] args) {
        GuessNumberHigherOrLowerII t = new GuessNumberHigherOrLowerII();
        int i = t.getMoneyAmount(1000);
        System.out.println(i);
    }

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return needMoneyMustWin(1, n, dp);
    }

    /**
     * 对于12345(n=5)来说 最少的花费是4+2 而不是3+4
     * 由上可知: 统计查询的次数都是一样的2次 , 但是花费是不同的 , 所以计算费用不能像二分查找那么做
     * 并且选择的数落在高区间[3,4,5]计算的值要 > 落在[1,2]区间的值 , 所以我们直接计算高区间[3,4,5]就可以了
     * <p>
     * 问题定义: 需要多少钱(结果) 保证能赢(条件)
     * 阶段: 猜[from,to]区间中某个数
     * 状态: dp[from][to], 当猜[from,to]区间时 需要多少钱(结果) 保证能赢(条件)。     //状态的定义是"阶段的定义" + "问题定义"的一个子问题
     * 状态转移: dp[from][to] = min(dp[from][to] , max(i + max(dp[from][i-1] , 0 , dp[i+1][to]))); i∈[from , to]  //在当前区间猜 需要多少钱 保证赢
     */
    public int needMoneyMustWin(int from, int to, int dp[][]) {
        if (from >= to) {
            return 0;
        }
        if (from >= to - 2) {  //如: [1,2] [1,2,3]
            return to - 1;
        }
        if (dp[from][to] != 0) {
            return dp[from][to];
        }
        int min = Integer.MAX_VALUE;
        //在[from,to]区间中 需要多少钱 保证赢? 只要假设选择的数落在了上半区间[from-to的中间开始 , to] 计算出来的数就是满足的题意的
        for (int i = (from + to) / 2 - 1; i <= to; i++) {
            int left = needMoneyMustWin(from, i - 1, dp);
            int right = needMoneyMustWin(i + 1, to, dp);
            int max = i + Math.max(left, right);
            min = Math.min(max, min);
        }
        dp[from][to] = min;
        return dp[from][to];
    }
}