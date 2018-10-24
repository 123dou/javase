package swordToOffer;

/**
 * 统计一个数字在排序数组中出现的次数。
 */
public class GetNumberOfK {
    public static void main(String[] args) {
        int[] array = {3, 3, 3, 3, 4, 5};
        GetNumberOfK gn = new GetNumberOfK();
        int i = gn.GetNumberOfK(array, 3);
        System.out.println(i);
    }

    /**
     * 就两个二叉查找,一个找最左边界一个找最右边界
     *
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) return 0;
        int left = findLeft(array, 0, array.length - 1, k);
        if (array[left] != k) return 0;
        int right = findRight(array, left, array.length - 1, k);
        return right - left + 1;
    }


    private int findLeft(int[] array, int low, int hi, int k) {
        int mid;
        while (low < hi) {
            mid = (low + hi) >>> 1;
            if (k <= array[mid]) hi = mid;
            else low = mid + 1;
        }
        return low;
    }

    private int findRight(int[] array, int low, int hi, int k) {
        int mid;
        while (low < hi) {
            mid = (low + hi + 1) >>> 1; //加1是为了防止死循环
            if (k >= array[mid]) low = mid;
            else hi = mid - 1;
        }
        return low;
    }
}
