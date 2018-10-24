package leetcode.intermediate.Backtracking;

import org.hamcrest.core.Is;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.geom.RectangularShape;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class SubSets {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 1, 1, 5, 5, 6, 7, 1, 2, 3, 4, 5, 1, 4, 7, 5, 6};
        List<List<Integer>> lists = subSets2(nums);
        System.out.println(lists.size());
    }

    /**
     * @param nums
     * @return lists, 一个包含所有子集的列表
     */
    public static List<List<Integer>> subSets(int[] nums) {
        List<List<Integer>> lists = new LinkedList<>();
        lists.add(new LinkedList<>());
        if (nums == null || nums.length == 0) return lists;
        for (int i = 1; i <= nums.length; i++) {
            subSets(nums, i, 0, lists, new LinkedList<>());
        }
        return lists;
    }

    /**
     * 获取所有的给定个数的组合
     *
     * @param nums  给定数数组
     * @param k     遍历的最大深度, 即给定的个数
     * @param index 遍历的深度:当其等与k时遍历结束
     * @param lists //最终的返回结果
     * @param list  //遍历的记录
     */
    public static void subSets(int[] nums, int k, int index, List<List<Integer>> lists, LinkedList<Integer> list) {
        if (index == k) { //list.size()==k,说明够数了,
            List<Integer> l = new LinkedList<>();
            l.addAll(list);
            lists.add(l);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            subSets(nums, k, i + 1, lists, list);
            if (list.size() > 0)
                list.removeLast(); //回溯
        }
    }

    static List<List<Integer>> res = new LinkedList<>();

    /**
     * 从后往前
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        boolean[] marked = new boolean[nums.length];
        addOneCondition(nums, 0, marked);
        return res;
    }

    /**
     * @param nums
     * @param index  走过数组的索引
     * @param marked 标记遍历过的index
     */
    public static void addOneCondition(int[] nums, int index, boolean[] marked) {
        if (index >= nums.length) {
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < nums.length; i++) if (marked[i]) list.add(nums[i]);
            res.add(list);
            return;
        }
        marked[index] = true;
        addOneCondition(nums, index + 1, marked);
        marked[index] = false;
        addOneCondition(nums, index + 1, marked);
    }

    /**
     * 二进制求法
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subSets2(int[] nums) {
        int n = nums.length;
        List<List<Integer>> lists = new LinkedList<>();
        for (int i = 0; i < (1 << n); i++) {//循环2^n次
            List<Integer> list = new LinkedList<>();
            String s = Integer.toBinaryString(i);//将int值转换成二进制值的字符串
            int unChoose = n - s.length();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1')//1表示被选中，0表示没有被选中
                    list.add(nums[unChoose + j]);
            }
            lists.add(list);
        }
        return lists;
    }


}
