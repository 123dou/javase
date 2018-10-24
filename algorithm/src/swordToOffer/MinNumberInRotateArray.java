package swordToOffer;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class MinNumberInRotateArray {
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) return 0;
        int sign = findGap(array, 0, array.length - 1);
        if (sign == -1) return array[0];
        return array[sign + 1];
    }


    private int findGap(int[] array, int left, int right) {
        if (left > right || left < 0 || right >= array.length) return -1;
        int mid = (left + right) >>> 1;
        if (mid + 1 < array.length) {
            if (array[mid] > array[mid + 1]) return mid;
        }
        int n = findGap(array, mid + 1, right);
        if (n != -1) return n;
        return findGap(array, left, mid - 1);
    }
}
