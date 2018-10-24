package leetcode.primary.dynamicPlanning;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Maxprofit {
    public static void main(String[] args) {
        int[] prices = {12, 11, 8, 9, 2, 1};
        int i = maxProfit2(prices);
        System.out.println(i);
    }

    /**
     * 第三版:更简洁
     * 代码一目明了
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - min > profit) {
                profit = prices[i] - min;
            } else if (prices[i] < min) {
                min = prices[i];
            }
        }
        return profit;
    }

    /**
     * 动态规划:保存第n天以来的最小值,和该最小值以来的最大值,比较
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
                max = Integer.MIN_VALUE; //该最小值之前的最大值丢弃,重新查找最大值
            }
            if (prices[i] > max) {
                max = prices[i];
            }
            if (max - min > maxProfit) maxProfit = max - min;
        }
        return maxProfit;
    }

    /**
     * 暴力法,时间复杂度太高
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > maxProfit) maxProfit = prices[j] - prices[i];
            }
        }
        return maxProfit;
    }
}
