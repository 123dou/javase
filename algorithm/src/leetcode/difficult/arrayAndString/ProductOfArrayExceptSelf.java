package leetcode.difficult.arrayAndString;

import java.util.Arrays;

/**
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
 * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] ints = productExceptSelf2(nums);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 原地算法: 延迟初始化第二个数组
     *
     * @param nums
     * @return
     */
    public static int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = 1; i <= len; i++) {
            res[len - i] *= right;
            right *= nums[len - i];
        }
        return res;
    }

    /**
     * 空间复杂度为2*nums.length
     *
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] order = new int[len];
        int[] reorder = new int[len];
        order[0] = 1;
        reorder[0] = 1;
        for (int i = 1; i < len; i++) {
            order[i] = order[i - 1] * nums[i - 1];
            reorder[i] = reorder[i - 1] * nums[len - i];
        }
        for (int i = 1; i <= len; i++) {
            nums[i - 1] = reorder[len - i] * order[i - 1];
        }
        return nums;
    }

}
