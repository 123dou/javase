package leetcode.primary.dynamicPlanning;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 * <p>
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class Rob {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int rob = rob(nums);
        System.out.println(rob);
    }

    /**
     * 更简洁清晰
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int pre2 = nums[0]; //n的前两个的最大值
        int pre1 = Math.max(nums[0], nums[1]); //n的前一个的最大值
        int res = 0;
        for (int i = 2; i < nums.length; i++) {
            res = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = res;
        }
        return res;
    }


    /**
     * 每间房屋有偷或者不偷两种情况
     * f(n) = max {f(n-1), f(n-2) + n}; 前一种情况是不偷n,后一种情况是偷n
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int maxSum;
        int sum_pre2 = nums[0];
        maxSum = Math.max(nums[1], sum_pre2);
        int temp;
        for (int i = 2; i < nums.length; i++) {
            sum_pre2 += nums[i];
            if (sum_pre2 > maxSum) {
                temp = maxSum;
                maxSum = sum_pre2;
                sum_pre2 = temp;
            } else sum_pre2 = maxSum;

        }
        return maxSum;
    }
}
