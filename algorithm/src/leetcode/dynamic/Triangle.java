package leetcode.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class Triangle {
    public static void main(String[] args) {

    }

    /**
     * 第n行的最优解为第n行所有点的路径和的最小值有关
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> list = new ArrayList<>();
        List<Integer> pre = triangle.get(0);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> l = triangle.get(i);
            for (int j = 0; j < l.size(); j++) {
                int n = l.get(j);
                if (j == 0) l.set(j, n + pre.get(j));
                else if (j == l.size() - 1) l.set(j, n + pre.get(j - 1));
                else l.set(j, n + Math.min(pre.get(j), pre.get(j - 1))); //转换方程
                if (i == triangle.size() - 1) res = Math.min(res, l.get((j)));
            }
            pre = l;
        }
        return triangle.size() == 1 ? triangle.get(0).get(0) : res;
    }
}
