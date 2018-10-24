package leetcode.primary.dynamicPlanning;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = {-2, -1, 3, 4, -1, 2, 8, -5, -4};
        int i = maxSubArray2(nums);
        int j = maxSubArray(nums);
        System.out.println(i + " " + j);
    }

    /**
     * 设F(n)为前n个数字的最大和,则F(n) = max(F(n-1) + nums[n], F(n-1));
     *
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        if (nums.length == 1) return nums[0];
        int sum = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            result = Math.max(result, Math.max(nums[i], sum));
            if (nums[i] > sum) sum = nums[i];
        }
        return result;
    }


    /**
     * 暴力法:时间复杂度高
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int max = 0;
            for (int j = i; j < nums.length; j++) {
                max += nums[j];
                if (max > result) result = max;
            }
        }
        return result;
    }
}
