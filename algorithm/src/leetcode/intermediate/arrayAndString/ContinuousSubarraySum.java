package leetcode.intermediate.arrayAndString;

/**
 * 给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [23,2,4,6,7], k = 6
 * 输出: True
 * 解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 示例 2:
 * <p>
 * 输入: [23,2,6,4,7], k = 6
 * 输出: True
 * 解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 */
public class ContinuousSubarraySum {
    public static void main(String[] args) {

    }

    //直接暴力法,注意要避免除零以及零可以除任何数
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) return false;
        //直接暴力
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum != 0 && sum < k) continue;
                if (sum == k || (k != 0 && sum % k == 0)) return true;
            }
        }
        return false;
    }
}
