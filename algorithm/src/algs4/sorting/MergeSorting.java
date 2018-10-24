package algs4.sorting;

/**
 * 归并排序:
 * 1.把长度为n的输入序列分成两个长度为n/2的子序列
 * 2.对这两个子序列分别采用归并排序
 * 3.将两个排序好的子序列合并成一个最终的排序序列
 *
 * @author dou
 */
public class MergeSorting {

    public static int[] mergeSorting(int[] array) {
        //如果array为null,或长度为1则返回
        if (SortingUtil.isNullOrOneElement(array)) return array;
        //数组长度
        int length = array.length;
        //将数组分成元素相同的两个数组
        int mid = length / 2;
        int[] left = new int[mid];
        System.arraycopy(array, 0, left, 0, mid);
        int[] right = new int[length - mid];
        System.arraycopy(array, mid, right, 0, length - mid);
        //递归调用
        return merge(mergeSorting(left), mergeSorting(right));
    }

    //将两个无序的数组合并成一个有序的数组
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length]; //将要返回的数组,里面将要存放left,right排序后的元素
        int l = 0;
        int r = 0;
        int len = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) result[len++] = left[l++];
            else result[len++] = right[r++];
        }
        while (l < left.length) result[len++] = left[l++];
        while (r < right.length) result[len++] = right[r++];
        return result;
    }

}
