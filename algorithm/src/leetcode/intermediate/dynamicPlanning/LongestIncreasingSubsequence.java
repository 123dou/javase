package leetcode.intermediate.dynamicPlanning;

import com.sun.prism.shader.DrawPgram_ImagePattern_AlphaTest_Loader;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = {4, 10, 4, 3, 8, 9};
        int i = longestOfLIS2(nums);
        System.out.println(i);
    }

    /**
     * 设L(i)为上升子序列的位置,然后查找前i个的max{L(i)}
     *
     * @param nums
     * @return
     */
    public static int longestOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * nlogn的解法
     * 思路看代码,挺清楚的了
     * 原地构建一个上升子序列,且将上升子序列中的元素换成nums中的最小值
     *
     * @param nums
     * @return
     */
    public static int longestOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int length = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[length]) nums[++length] = nums[i];
            else {
                int c = BSearch(nums, length, nums[i]);
                if (nums[c] > nums[i]) nums[c] = nums[i];
            }
        }
        return length + 1;
    }

    public static int BSearch(int[] nums, int length, int target) {
        int low = 0;
        int high = length;
        int mid;
        while (low < high) {
            mid = (low + high) >>> 1;
            if (target <= nums[mid]) high = mid;
            else low = mid + 1;
        }
        return high;
    }
}
