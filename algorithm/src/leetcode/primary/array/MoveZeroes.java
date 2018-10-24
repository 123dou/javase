package leetcode.primary.array;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class MoveZeroes {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 0, 0, 0, 0, 0, 0, 3, 12, 0, 0};
        moveZeroes3(nums);
    }

    /**
     * 先直接将不是零的数排到前面,再将后面的初始化为0
     *
     * @param nums
     */
    public static void moveZeroes3(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != 0) nums[len++] = nums[i];
        while (len < nums.length) nums[len++] = 0;
        System.out.println(Arrays.toString(nums));

    }


    /**
     * 借鉴快排的分区
     *
     * @param nums
     */
    public static void moveZeroes2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int not0 = i;
            while (not0 < nums.length && nums[not0] == 0) {
                not0++;
            }
            if (not0 < nums.length && not0 != i) {
                swap(nums, i, not0);
            }
        }
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 时间复杂度有点高:借鉴插入排序
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int curr = i;
            int pre = i - 1;
            int currValue = nums[i];
            while (curr >= 0 && pre >= 0 && nums[pre] == 0) {
                nums[pre + 1] = 0;
                curr--;
                pre--;
            }
            if (curr != i)
                nums[curr] = currValue;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
