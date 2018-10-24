package leetcode.primary.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 9};
        boolean b = containDuplicate(nums);
        System.out.println(b);
    }

    /**
     * 利用HashMap中的key不允许相同来判定
     *
     * @param nums
     * @return
     */
    public static boolean containDuplicate2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                return true;
            else map.put(nums[i], 1);
        }
        return false;
    }


    /**
     * 利用基数排序:
     *
     * @param nums
     * @return
     */
    public static boolean containDuplicate(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max)
                max = nums[i];
            if (nums[i] < min)
                min = nums[i];
        }
        int length = max - min + 1;
        int increment = 0 - min;
        int[] array = new int[length];
        for (int i = 0; i < nums.length; i++) {
            array[nums[i] + increment]++;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 1)
                return true;
        }
        return false;
    }
}
