package leetcode.intermediate.arrayAndString;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    /**
     * 维持一个小于target的最大三数之和与一个大于target的最小的三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int less = nums[0] + nums[1] + nums[2];
        int more = nums[n - 3] + nums[n - 2] + nums[n - 1];
        if (less >= target)
            return less;
        if (more <= target)
            return more;
        for (int i = 0; i < n - 2; i++) {
            int min = nums[i] + nums[i + 1] + nums[i + 2];
            int max = nums[i] + nums[n - 2] + nums[n - 1];
            if (min > target) {
                more = Math.min(more, min);
                break;
            }
            if (max < target) {
                less = Math.max(less, max);
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) return target;
                else if (sum > target) {
                    more = Math.min(more, sum);
                    while (--k > j && nums[k] == nums[k + 1]) ;
                } else {
                    less = Math.max(less, sum);
                    while (++j < k && nums[j] == nums[j - 1]) ;
                }
            }
        }
        return more - target > target - less ? less : more;
    }
}
