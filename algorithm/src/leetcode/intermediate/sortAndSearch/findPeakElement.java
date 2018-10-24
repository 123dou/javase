package leetcode.intermediate.sortAndSearch;

/**
 * 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * <p>
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * <p>
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * <p>
 * 你的解法应该是 O(logN) 时间复杂度的。
 */
public class findPeakElement {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        int result = findPeakElement(nums);
        System.out.println(result);
    }

    /**
     * 时间复杂度大了点
     *
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums.length == 2) {
            if (nums[0] > nums[1]) return 0;
            else return 1;
        }
        return findPeakElement(nums, 0, 1, 2);
    }

    /**
     * @param nums
     * @param i
     * @param j
     * @param k
     * @return
     */
    public static int findPeakElement(int[] nums, int i, int j, int k) {
        if (i == 0 && nums[j] < nums[i]) return i;
        if (j == nums.length - 1) return j;
        if (nums[i] < nums[j] && nums[j] > nums[k]) return j;
        return findPeakElement(nums, i + 1, j + 1, k + 1);
    }

    /**
     * 有条件的查找,可以使用二叉
     *
     * @param nums
     * @return
     */
    public static int findPeakElement2(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[nums.length - 2] < nums[nums.length - 1]) return nums.length - 1;
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (true) {
            mid = low + (high - low) / 2;
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) return mid;
            if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) low = mid;
            else high = mid;
        }
    }

}
