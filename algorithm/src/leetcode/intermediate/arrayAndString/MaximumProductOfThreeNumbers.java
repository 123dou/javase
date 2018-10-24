package leetcode.intermediate.arrayAndString;

/**
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 * <p>
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 */
public class MaximumProductOfThreeNumbers {
    public static void main(String[] args) {

    }

    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        if (nums.length == 3) return nums[0] * nums[1] * nums[2];
        int max = Integer.MIN_VALUE;
        int secMax = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int secMin = Integer.MAX_VALUE;
        int thirdMax = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i >= max) {
                thirdMax = secMax;
                secMax = max;
                max = i;
            } else if (i >= secMax) {
                thirdMax = secMax;
                secMax = i;
            } else if (i > thirdMax) {
                thirdMax = i;
            }

            if (i <= min) {
                secMin = min;
                min = i;
            } else if (i < secMin) secMin = i;
        }
        int res = Math.max(max * secMax * thirdMax, min * secMin * max);
        return res;

    }
}
