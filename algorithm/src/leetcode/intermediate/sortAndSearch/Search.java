package leetcode.intermediate.sortAndSearch;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class Search {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int i = search2(nums, 0);
        System.out.println(i);
    }

    /**
     * 先找到断层的最后一个数,然后再用二叉查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int breakpoint = searchKey(nums, 0, nums.length - 1);
        if (breakpoint == -1) return BSsearch(nums, 0, nums.length - 1, target);
        if (target >= nums[0] && target <= nums[breakpoint]) return BSsearch(nums, 0, breakpoint, target);
        else return BSsearch(nums, breakpoint + 1, nums.length - 1, target);
    }


    public static int BSsearch(int[] nums, int low, int high, int target) {
        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            if (target > nums[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }


    /**
     * 搜索断层
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int searchKey(int[] nums, int start, int end) {
        if (end == start) return -1;
        if (end - start == 1 && nums[start] > nums[end]) return start;
        if (end - start == 1 && nums[start] <= nums[end]) return -1;
        int mid = start + (end - start) / 2;
        if (nums[mid] < nums[0]) return searchKey(nums, start, mid);
        else return searchKey(nums, mid, end);
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    public int search3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1)
            if (nums[0] != target) return -1;
            else return 0;
        int left_bound = getLeftBound(nums, 0, nums.length - 1); //先找出左边界
        if (left_bound == -1) return binarySearch(nums, 0, nums.length - 1, target);
        else if (target >= nums[0] && target <= nums[left_bound]) return binarySearch(nums, 0, left_bound, target);
        else return binarySearch(nums, left_bound + 1, nums.length - 1, target);
    }

    public int binarySearch(int[] nums, int low, int high, int target) {
        int mid;
        while (low <= high) {
            mid = (low + high) >>> 1;
            if (target == nums[mid]) return mid;
            if (target > nums[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    //利用二分法进行所有元素的搜索
    public int getLeftBound(int[] nums, int low, int high) {
        if (low > high || low < 0 || high >= nums.length) return -1;
        int mid = (low + high) >>> 1;
        //if (mid == 0) return 0;
        if (mid == nums.length - 1) return -1;
        if (nums[mid] > nums[mid + 1]) return mid;
        int res = getLeftBound(nums, low, mid - 1);
        if (res != -1) return res;
        return getLeftBound(nums, mid + 1, high);
    }

    /**
     * 暴力搜索,不优雅
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }
}
