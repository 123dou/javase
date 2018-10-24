package leetcode.traceback;

import javax.swing.*;
import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class permutations2 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2};
        permutations2 permutations2 = new permutations2();
        List<List<Integer>> listList = permutations2.permuteUnique2(nums);
        System.out.println(listList);
    }

    /**
     * 当有大面积的相同元素时,效率极其低
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new LinkedList<>();
        if (nums == null || nums.length == 0) return lists;
        Set<Integer> set = new HashSet<>();
        permuteUnique(nums, 0, lists, set);
        return lists;
    }

    /**
     * 笨方法,基本没有起到剪枝的作用
     *
     * @param nums
     * @param n
     * @param lists
     * @param set
     */
    private void permuteUnique(int[] nums, int n, List<List<Integer>> lists, Set<Integer> set) {
        if (n == nums.length - 1) {
            List<Integer> l = new LinkedList<>();
            int key = Arrays.hashCode(nums);
            if (set.contains(key)) return;
            set.add(key);
            for (Integer i : nums) l.add(i);
            lists.add(l);
        }

        for (int i = n; i < nums.length; i++) {
            swap(nums, n, i);
            permuteUnique(nums, n + 1, lists, set);
            swap(nums, n, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 剪枝了
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> lists = new LinkedList<>();
        if (nums == null || nums.length == 0) return lists;
        permuteUnique(nums, 0, lists);
        return lists;
    }

    /**
     * 带剪枝的
     *
     * @param nums
     * @param n
     * @param lists
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums, int n, List<List<Integer>> lists) {
        if (n == nums.length - 1) {
            List<Integer> l = new LinkedList<>();
            for (Integer i : nums) l.add(i);
            lists.add(l);
        }

        for (int i = n; i < nums.length; i++) {
            if (!isSame(nums, n, i)) {
                swap(nums, n, i);
                permuteUnique(nums, n + 1, lists);
                swap(nums, n, i);
            }
        }
        return lists;
    }

    /**
     * 当该区间有相同的元素时,则该序列相同
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private boolean isSame(int[] nums, int start, int end) {
        for (int i = start; i < end; i++) {
            if (nums[i] == nums[end]) return true;
        }
        return false;
    }


}
