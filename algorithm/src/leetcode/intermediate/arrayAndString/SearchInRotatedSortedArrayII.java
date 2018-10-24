package leetcode.intermediate.arrayAndString;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * <p>
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 * <p>
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class SearchInRotatedSortedArrayII {
    public static void main(String[] args) {

    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int gap = findGap(nums, 0, nums.length - 1);
        if (nums[gap] == target) return true;
        if (find(nums, gap + 1, nums.length - 1, target)) return true;
        return find(nums, 0, gap - 1, target);
    }

    private int findGap(int[] arr, int start, int end) {
        if (start > end) return -1;
        int mid = (start + end + 1) >>> 1; //像3,4的这种情况的时候使mid取右边的值
        if (mid == 0) return 0;
        if (arr[mid] < arr[mid - 1]) return mid;
        int right = findGap(arr, mid + 1, end);
        if (right >= 0) return right;
        return findGap(arr, start, mid - 1);
    }

    private boolean find(int[] arr, int start, int end, int target) {
        int mid;
        while (start <= end) {
            mid = (start + end) >>> 1;
            if (arr[mid] == target) return true;
            if (target < arr[mid]) end = mid - 1;
            else start = mid + 1;
        }
        return false;
    }
}
