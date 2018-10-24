package leetcode.difficult.arrayAndString;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * <p>
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * <p>
 * 你可以假设 nums1 和 nums2 不同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 中位数是 (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {


    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m >= n) {
            int[] temp = A;
            A = B;
            B = temp;
            m = A.length;
            n = B.length;
        }
        int low = 0, hi = m, half = (n + m + 1) >>> 1;
        while (low <= hi) {
            int i = (low + hi) >>> 1;
            int j = half - i;
            if (i > low && A[i - 1] > B[j]) hi = i - 1;
            else if (i < m && B[j - 1] > A[i]) low = i + 1;
            else {
                int maxLeft = 0;
                if (i == 0) maxLeft = B[j - 1];
                else if (j == 0) maxLeft = A[i - 1];
                else maxLeft = Math.max(A[i - 1], B[j - 1]);

                if (((n + m) & 1) == 1) return maxLeft;

                int minright = 0;
                if (i == m) minright = B[j];
                else if (j == n) minright = A[i];
                else minright = Math.min(A[i], B[j]);

                return ((minright + maxLeft) / 2.0);
            }
        }
        return 0.0;
    }
}
