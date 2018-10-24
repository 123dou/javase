package leetcode.intermediate.sortAndSearch;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 例如，
 * <p>
 * 给定数组 [1,1,1,2,2,3] , 和 k = 2，返回 [1,2]。
 * <p>
 * 注意：
 * <p>
 * 你可以假设给定的 k 总是合理的，1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
public class TopKFrequent {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 2, 3, 3, 3, 3, 0, 0, 0, 0};
        int k = 2;
        List<Integer> list = topKFrequent(nums, k);
        System.out.println(list);
    }

    /**
     * 1.先用计数算法,算出,每个数字的频率
     * 2.再用计数算法,给每个数字的频率排序
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        int nums_min = Integer.MAX_VALUE;
        int nums_max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            nums_min = Math.min(nums_min, nums[i]);
            nums_max = Math.max(nums_max, nums[i]);
        }
        int[] freq = new int[nums_max - nums_min + 1]; //计算频率
        for (int i = 0; i < nums.length; i++) {
            freq[nums[i] - nums_min]++;
        }
        int freq_min = Integer.MAX_VALUE;
        int freq_max = Integer.MIN_VALUE;
        for (Integer f : freq) {
            if (f != 0) { //忽略频率为0的数
                freq_min = Math.min(freq_min, f);
                freq_max = Math.max(freq_max, f);
            }
        }
        int[] sort_freq = new int[freq_max - freq_min + 1]; //频率的排序
        for (int i = 0; i < freq.length; i++) {
            //忽略频率为0的数
            if (freq[i] != 0) sort_freq[freq[i] - freq_min]++;
        }
        int count = 0;
        int fre = 0; //选出合适的频率
        for (int i = sort_freq.length - 1; i >= 0; i--) {
            if (sort_freq[i] != 0) {
                count += sort_freq[i];
                if (count >= k) {
                    fre = i + freq_min;
                    break;
                }
            }
        }
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] >= fre) list.add(i + nums_min);
        }
        return list;
    }
}
