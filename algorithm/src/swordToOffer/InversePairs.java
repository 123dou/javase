package swordToOffer;

import java.util.function.IntPredicate;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 * <p>
 * 数据范围：
 * <p>
 * 对于%50的数据,size<=10^4
 * <p>
 * 对于%75的数据,size<=10^5
 * <p>
 * 对于%100的数据,size<=2*10^5
 * <p>
 * 示例1
 * 输入
 * 复制
 * 1,2,3,4,5,6,7,0
 * 输出
 * 复制
 * 7
 */
public class InversePairs {
    public static void main(String[] args) {
        int[] array =
                {7, 5, 6, 4};
        InversePairs ip = new InversePairs();
        int i = ip.InversePairs2(array);
        System.out.println(i);
    }

    /**
     * 基于二叉的查找
     *
     * @param array
     * @return
     */
    public int InversePairs(int[] array) {
        if (array == null || array.length <= 1) return 0;
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            if (value > array[i - 1]) continue;
            int index = findLeftMin(array, i - 1, value);
            int num = i - index;
            System.arraycopy(array, index, array, index + 1, num);
            array[index] = value;
            count += num;
            count %= 1000000007;
        }
        return count;
    }

    /**
     * 基于归并的
     *
     * @param array
     * @return
     */
    public int InversePairs2(int[] array) {
        if (array == null || array.length < 2) return 0;
        int[] count = {0};
        mergeSort(array, count);
        return count[0];
    }


    /**
     * 基于归并的
     * 这个会快很多
     *
     * @param array
     * @return
     */
    public int[] mergeSort(int[] array, int[] count) {
        if (array.length == 1) return array;
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);
        return mergeCore(mergeSort(left, count), mergeSort(right, count), count);
    }

    private int[] mergeCore(int[] a1, int[] a2, int[] count) {
        int[] res = new int[a1.length + a2.length];
        int i = a1.length - 1, j = a2.length - 1;
        int len = res.length - 1;
        while (i >= 0 && j >= 0) {
            if (a1[i] > a2[j]) {
                count[0] += j + 1;
                count[0] %= 1000000007;
                res[len--] = a1[i--];
            } else {
                res[len--] = a2[j--];
            }
        }
        while (i >= 0) res[len--] = a1[i--];
        while (j >= 0) res[len--] = a2[j--];
        return res;
    }


    private int findLeftMin(int[] array, int len, int target) {
        int low = 0;
        int hi = len;
        int mid;
        while (low < hi) {
            mid = (low + hi) >>> 1;
            if (target <= array[mid]) hi = mid;
            else low = mid + 1;
        }
        return low;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
