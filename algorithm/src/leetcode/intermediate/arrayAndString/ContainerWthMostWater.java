package leetcode.intermediate.arrayAndString;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * <p>
 * <p>
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 */
public class ContainerWthMostWater {
    public static void main(String[] args) {

    }

    /**
     * 双指针夹逼
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) return 0;
        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right) {
            int row = right - left;
            int h = height[left];

            if (height[left] <= height[right]) left++;
            else h = height[right--];

            max = Math.max(max, h * row);
        }

        return max;

    }

    /**
     * 暴力
     *
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        if (height == null || height.length <= 1) return 0;
        int max = 0;
        for (int i = 1; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                int square = (i - j) * Math.min(height[i], height[j]);
                max = Math.max(max, square);
            }
        }
        return max;
    }
}
