package leetcode.intermediate.arrayAndString;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5], k=4, x=3
 * 输出: [1,2,3,4]
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5], k=4, x=-1
 * 输出: [1,2,3,4]
 * <p>
 * <p>
 * 说明:
 * <p>
 * k 的值为正数，且总是小于给定排序数组的长度。
 * 数组不为空，且长度不超过 104
 * 数组里的每个元素与 x 的绝对值不超过 104
 */
public class FindKClosestElements {
    public static void main(String[] args) {
        FindKClosestElements t = new FindKClosestElements();
        int[] arr = {0, 1, 2, 2, 2, 3, 6, 8, 8, 9};
        int k = 10;
        int x = 0;
        List<Integer> closestElements = t.findClosestElements(arr, k, x);
        System.out.println(closestElements);
    }

    /**
     * 主要是边界问题
     *
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if (k == 0) return res;
        if (x <= arr[0]) {
            for (int i = 0; i < k; i++) res.add(arr[i]);
            return res;
        }
        int idx = find(arr, x);
        int right = idx;
        while (right < arr.length && arr[right] == x) right++;
        int left = idx;
        while (right - left <= k && (left >= 0 || right < arr.length)) {
            if (right >= arr.length && left >= 0) left--;
            else if (right < arr.length && left < 0) right++;
            else if (x - arr[left] <= arr[right] - x) left--;
            else right++;
        }
        for (int i = left + 1; i < right; i++) res.add(arr[i]);
        return res;
    }

    //二叉查找
    private int find(int[] arr, int x) {
        int low = 0, hi = arr.length - 1, mid;
        while (low < hi) {
            mid = (low + hi) >>> 1;
            if (x <= arr[mid]) hi = mid;
            else low = mid + 1;
        }
        return low;
    }

}
