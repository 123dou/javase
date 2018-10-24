package algs4.sorting;

import static algs4.sorting.SortingUtil.swap;

/**
 * 插入法:
 * 1.从第一个元素开始,该元素可以认为已经排序
 * 2.取出下一个元素,在已排序的元素序列从后往前扫描
 * 3.如果该元素比序列中的元素小,则序列中的元素往后移一位
 * 4.重复步骤3,直到找到以排序中的元素小于或者等于该元素
 * 5.将新元素插入到该位置
 * 6.重复步骤2~5
 *
 * @author dou
 */
public class InsertionSorting {

    public static int[] insertionSorting(int[] array) {
        //如果数组为空,或长度为一,返回
        if (SortingUtil.isNullOrOneElement(array))
            return array;
        //数组的长度
        int length = array.length;
        //从第二位起遍历数组
        for (int i = 1; i < length; i++) {
            int preIndex = i - 1; //数组的前一位下标
            int curr = i; //数组当前的值
            while (preIndex >= 0 && array[preIndex] > array[curr]) { //当数组中有序数列的前一位大于当前值时和数组下标还不越界时
                swap(array, preIndex, curr);
                curr--;
                preIndex--; //数列往前走一步
            }
        }
        return array;
    }

    /**
     * 用二叉来查找插入的位置
     *
     * @param array
     */
    public static void insertionSorting2(int[] array) {
        if (array == null || array.length <= 1) return;
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            if (value >= array[i - 1]) continue;
            int index = findMinIndex(array, i - 1, value);
            System.arraycopy(array, index, array, index + 1, i - index);
            array[index] = value;
        }
    }

    /**
     * 查找第一个小于target的索引
     *
     * @param array
     * @param len
     * @param target
     * @return
     */
    private static int findMinIndex(int[] array, int len, int target) {
        int low = 0, hi = len, mid;
        while (low < hi) {
            mid = (low + hi) >>> 1;
            if (target <= array[mid]) hi = mid;
            else low = mid + 1;
        }
        return low;
    }

}
