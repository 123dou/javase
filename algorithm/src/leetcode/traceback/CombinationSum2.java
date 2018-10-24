package leetcode.traceback;

import java.util.*;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class CombinationSum2 {
    public static void main(String[] args) {

    }


    public void combinationSum2_2(int[] nums, int target,
                                  List<List<Integer>> lists,
                                  List<Integer> list, int index) {
        if (target == 0) {
            lists.add(new ArrayList<>(list));
            return;
        }
        if (target < 0) return;
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > target || (i > index && nums[i] == nums[i - 1])) continue;
            list.add(nums[i]);
            combinationSum2(nums, target - nums[i], lists, list, i + 1);
            if (!list.isEmpty()) list.remove(list.size() - 1);
        }
    }


    /**
     * 递归配合一个set
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return lists;
        List<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i : candidates) min = Math.min(min, i);
        Arrays.sort(candidates);
        combinationSum2(candidates, target, lists, list, 0);
        return lists;
    }

    Set<Integer> set = new HashSet<>();

    private void combinationSum2(int[] nums, int target,
                                 List<List<Integer>> lists, List<Integer> list, int index) {
        if (target == 0) {
            List<Integer> l = new ArrayList<>();
            int key = 1;
            for (int i : list) {
                key = 31 * key + i;
                l.add(i);
            }
            if (!set.contains(key)) {
                lists.add(l);
                set.add(key);
            }
            return;
        }
        if (index >= nums.length || target < 0 || nums[index] > target) return;
        list.add(nums[index]);
        combinationSum2(nums, target - nums[index], lists, list, index + 1);
        if (!list.isEmpty()) list.remove(list.size() - 1);
        combinationSum2(nums, target, lists, list, index + 1);
    }

}
