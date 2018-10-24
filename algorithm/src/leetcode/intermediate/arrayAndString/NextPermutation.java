package leetcode.intermediate.arrayAndString;

import java.util.Arrays;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {
    public static void main(String[] args) {

    }

    /**
     * 从后往前找出第一个非递增的位置
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int minIndex = i;
                int min = nums[i];
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] > nums[i - 1]) {
                        if (nums[j] < min) {
                            min = nums[j];
                            minIndex = j;
                        }
                    }
                }
                int temp = nums[i - 1];
                nums[i - 1] = nums[minIndex];
                nums[minIndex] = temp;
                Arrays.sort(nums, i, nums.length);
                return;
            }
        }
        Arrays.sort(nums);
    }
}
