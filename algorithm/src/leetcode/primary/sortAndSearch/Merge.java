package leetcode.primary.sortAndSearch;

import java.util.Arrays;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 */
public class Merge {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 0, 0, 0, 0};
        int[] nums2 = {1, 2, 5, 6};
        merge2(nums, 3, nums2, 4);
    }

    /**
     * 从后往前插入
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
        }
        int len = m + n - 1;
        int index_m = m - 1;
        int index_n = n - 1;
        while (index_m >= 0 && index_n >= 0) {
            if (nums1[index_m] > nums2[index_n]) nums1[len--] = nums1[index_m--];
            else nums1[len--] = nums2[index_n--];
        }
        if (index_m < 0)
            while (index_n >= 0) nums1[len--] = nums2[index_n--];
        System.out.println(Arrays.toString(nums1));
    }


    /**
     * 改进的插入法,时间复杂度一般
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        int pre;
        int i = 0;
        while (i < n) {
            boolean flag = false;
            pre = m - 1 + i;
            while (pre >= 0 && nums2[i] < nums1[pre]) {
                nums1[pre + 1] = nums1[pre];
                --pre;
                flag = true;
            }
            if (!flag) {
                while (i < n) nums1[++pre] = nums2[i++];
            } else nums1[++pre] = nums2[i++];
        }
        System.out.println(Arrays.toString(nums1));
    }
}
