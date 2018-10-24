package leetcode.primary.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * <p>
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] ints = twoSum2(nums, target);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 从nums中查找target-nums[i]的值:借鉴基数排序的方法
     * 用空间换时间:兼容性不态好
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) min = nums[i];
            if (nums[i] > max) max = nums[i];
        }

        int[] index = new int[max - min + 1];
        for (int i = 0; i < nums.length; i++) {
            index[nums[i] - min]++;
        }
        int other;
        for (int i = 0; i < nums.length; i++) {
            other = target - nums[i];
            if (other < min || other > max)
                continue;
            if (index[other - min] > 0) {
                for (int j = 0; j < nums.length; j++) {
                    if (j != i && nums[j] == other)
                        return new int[]{i, j};
                }
            }
        }
        return null;
    }


    /**
     * 较好的思路:从nums中查找target-nums[i]的值
     * 这里用的是HashMap来保存已遍历的元素
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(target - nums[i]))
                map.put(nums[i], i);
            else {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return null;
    }


    /**
     * 暴力搜索法:遍力所有的结果
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
