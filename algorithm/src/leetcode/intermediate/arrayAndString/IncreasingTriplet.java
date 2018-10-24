package leetcode.intermediate.arrayAndString;

/**
 * 给定一个未排序的数组，请判断这个数组中是否存在长度为 3 的递增的子序列。
 * <p>
 * 正式的数学表达如下:
 * <p>
 * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 * 要求算法时间复杂度为 O(n)，空间复杂度为 O(1) 。
 * <p>
 * 示例:
 * 输入 [1, 2, 3, 4, 5],
 * 输出 true.
 * <p>
 * 输入 [5, 4, 3, 2, 1],
 * 输出 false.
 */
public class IncreasingTriplet {
    public static void main(String[] args) {
        int[] nusm = {1, 1, -2, 6};
        boolean b = increasingTriplet(nusm);
        System.out.println(b);
    }

    /**
     * 可用动态规划来做
     *
     * @param nums
     * @return
     */
    public static boolean increasingTriplet2(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int n1 = Integer.MAX_VALUE;
        int n2 = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < n1) n1 = nums[i];
            else if (nums[i] > n1 && nums[i] < n2) n2 = nums[i];
            else if (nums[i] > n2) return true;
        }
        return false;
    }


    /**
     * @param nums
     * @return
     */
    public static boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int n1;
        int n2;
        for (int i = 0; i < nums.length - 1; i++) {
            n1 = nums[i];
            n2 = nums[i + 1];
            if (n1 >= n2) continue;
            for (int j = i + 2; j < nums.length; j++) {
                if (nums[j] > n2) return true;
                if (nums[j] > n1) n2 = nums[j];
            }
        }
        return false;
    }
}
