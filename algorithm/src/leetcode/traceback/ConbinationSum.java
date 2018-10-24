package leetcode.traceback;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class ConbinationSum {
    public static void main(String[] args) {

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new LinkedList<>();
        if (candidates == null || candidates.length == 0) return lists;
        Arrays.sort(candidates);
        LinkedList<Integer> list = new LinkedList<>();
        combinationSum(candidates, target, 0, lists, list);
        return lists;
    }

    /**
     * @param candidates 给定的数组
     * @param target     当target为0时递归结束,找到一组数据
     * @param index      遍历到的数组位置
     * @param lists
     * @param list
     */
    private void combinationSum(int[] candidates, int target, int index,
                                List<List<Integer>> lists, LinkedList<Integer> list) {
        if (target == 0) {
            LinkedList<Integer> l = new LinkedList<>();
            l.addAll(list);
            lists.add(l);
            return;
        }
        if (target < 0 || index >= candidates.length || candidates[index] > target) return;
        int c = target / candidates[index]; //该元素最多取c个
        int offset = 0;
        for (int i = 1; i <= c; i++) {
            offset += candidates[index];
            list.add(candidates[index]);
            combinationSum(candidates, target - offset, index + 1, lists, list);
        }
        for (int i = 1; i <= c; i++) list.removeLast(); //回溯
        combinationSum(candidates, target, index + 1, lists, list); //一个也没取
    }
}
