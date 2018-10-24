package leetcode.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> lists = new ArrayList<>();
        if (k <= 0 || n > 55) return lists;
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = new ArrayList<>();
        combinationSum3(nums, n, lists, list, 0, k);
        return lists;
    }

    private void combinationSum3(int[] nums, int target, List<List<Integer>> lists, List<Integer> list, int index, int k) {
        if (target == 0 && list.size() == k) {
            lists.add(new ArrayList<Integer>(list));
            return;
        }
        if (target < 0 || index >= nums.length || nums[index] > target || list.size() >= k) return;
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > target) break;
            list.add(nums[i]);
            combinationSum3(nums, target - nums[i], lists, list, i + 1, k);
            if (!list.isEmpty()) list.remove(list.size() - 1);
        }
    }


}
