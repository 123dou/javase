package leetcode.intermediate.sortAndSearch;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import javax.swing.text.TabableView;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出 0、1 和 2 元素的个数，然后按照 0、1、2 的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class SortColors {
    public static void main(String[] args) {
        int[] nusm = {2, 0, 2, 1, 1, 0};
        sortColor2(nusm);
    }

    /**
     * 计数排序
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int[] freq = new int[3];
        for (int i = 0; i < nums.length; i++) {
            freq[nums[i]]++;
        }
        int length = 0;
        for (int i = 0; i < freq.length; i++) {
            while (freq[i] >= 1) {
                nums[length++] = i;
                freq[i]--;
            }
        }
    }

    /**
     * @param nums
     */
    public static void sortColor2(int[] nums) {
        //用三路快排的分区来解决
        int lt = 0;
        int gt = nums.length - 1;
        for (int i = 0; i < nums.length; ) {
            if (nums[i] == 1) i++;
            else if (nums[i] < 1) {
                if (i > lt) swap(nums, i++, lt++);
                else nums[lt++] = nums[i++];
            } else {
                if (gt > i) swap(nums, i, gt--);
                else break;
            }
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
