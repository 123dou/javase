package leetcode.traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */
public class SubsetsII {
    public static void main(String[] args) {

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        lists = new ArrayList<>();
        list = new ArrayList<>();
        lists.add(list);
        Arrays.sort(nums);
        if (nums == null || nums.length == 0) return lists;
        for (int i = 1; i <= nums.length; i++) subSet(nums, 0, i);
        return lists;
    }

    private List<List<Integer>> lists;
    private List<Integer> list;

    private void subSet(int[] nums, int index, int depth) {
        if (list.size() == depth) {
            lists.add(new ArrayList(list));
            return;
        }
        if (index >= nums.length) return;
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            subSet(nums, i + 1, depth);
            if (list.size() != 0) list.remove(list.size() - 1);
        }
    }

}
