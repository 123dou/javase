package leetcode.primary.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个数组，写一个方法来计算它们的交集。
 * <p>
 * 例如:
 * 给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].
 * <p>
 * 注意：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 跟进:
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class Insertsect {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] ints = intersect(nums1, nums2);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 先对数组进行排序
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        //对数据进行预处理,效果会好很多
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = start; j < nums2.length; j++) {
                if (nums1[i] < nums2[j]) break;
                if (nums1[i] == nums2[j]) {
                    list.add(nums2[j]);
                    start = j + 1;
                    break;
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) res[i] = list.get(i);
        return res;
    }


    /**
     * 先用暴力法
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null)
            return null;
        List<Integer> list = new ArrayList<>();
        int length2 = nums2.length;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < length2; j++) {
                if (nums1[i] == nums2[j]) { //输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。所以找到一个相同的元素,下次就要忽略掉
                    list.add(nums1[i]);
                    swap(nums2, j, length2 - 1);
                    --length2;
                    break;
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
