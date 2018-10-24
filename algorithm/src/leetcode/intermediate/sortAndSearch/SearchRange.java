package leetcode.intermediate.sortAndSearch;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
public class SearchRange {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] ints = searchRange(nums, target);
    }

    /**
     * 先找出小于等于目标值的第一个数,再找到大于等于目标值的第一个数
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int[] result = new int[2];
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (target <= nums[mid]) high = mid;
            else low = mid + 1;
        }
        if (nums[low] != target) {
            return new int[]{-1, -1};
        }
        result[0] = low;
        high = nums.length - 1;
        while (low < high) {
            mid = low + (high - low) / 2 + 1; //防止死循环,让求mid偏向high
            if (target >= nums[mid]) low = mid;
            else high = mid - 1;
        }
        result[1] = low;
        return result;
    }
}
