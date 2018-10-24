package leetcode.intermediate.Backtracking;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class Permute {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> li = permute(nums);
        System.out.println(li);
    }

    /**
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();
        List<List<Integer>> listList = new LinkedList<>();
        permute(nums, 0, listList);
        return listList;
    }

    /**
     * @param nums  数字数组
     * @param k     递归的深度
     * @param lists 存储符合条件的排列
     */
    public static void permute(int[] nums, int k, List<List<Integer>> lists) {
        if (k == nums.length) {
            List<Integer> list = new LinkedList<>();
            for (int num : nums) {
                list.add(num);
            }
            lists.add(list);
            return;
        }
        for (int i = k; i < nums.length; i++) {
            swap(nums, k, i);
            permute(nums, k + 1, lists);
            swap(nums, k, i);
        }
    }

    /**
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
