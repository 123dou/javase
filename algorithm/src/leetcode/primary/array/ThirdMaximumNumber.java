package leetcode.primary.array;

/**
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3, 2, 1]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 第三大的数是 1.
 * 示例 2:
 * <p>
 * 输入: [1, 2]
 * <p>
 * 输出: 2
 * <p>
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 * 示例 3:
 * <p>
 * 输入: [2, 2, 3, 1]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 */
public class ThirdMaximumNumber {
    public static void main(String[] args) {

    }

    /**
     * 注意边界,所以要用long类型
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE, second = Long.MIN_VALUE;
        long three = Long.MIN_VALUE;
        boolean hasThird = false;
        for (int i : nums) {
            if (i >= first) {
                if (i == first) continue;
                three = second;
                second = first;
                first = i;
            } else if (i >= second) {
                if (i == second) continue;
                three = second;
                second = i;
            } else if (i > three) three = i;
        }
        return three == Long.MIN_VALUE ? (int) first : (int) three;
    }
}
