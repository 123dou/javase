package algs4.sorting;

/**
 * 快速排序:
 * 1.从数列中挑出一个元素,称为"基准"(pivot)
 * 2.重新排序数列,所有元素比基准值小的摆放在基准前面,所有元素比基准值大的摆在基准的后面(相同的数可以放到任一边).在这个分区退出之后,该基准就处于数列
 * 的中间位置.这个称为分区(partition)操作(分区的实现方法有很多种)
 * 3.递归地(recursive)把小于基准值元素的子数列和大于基准值元素的子数接排序
 *
 * @author dou
 */
public class QuickSorting {
    private static final int M = 10;

    public static void quickSorting(int[] array, int low, int high) {
        //如果array为null和只有一个元素,或low<0,或high>array.length
        if (SortingUtil.isNullOrOneElement(array) || low < 0 || high > array.length)
            return;
        if (high <= low + M) {
            InsertionSorting(array, low, high);
            return;
        }
        int pivot = partition(array, low, high);
        if (pivot > low)
            quickSorting(array, low, pivot - 1);
        if (high > pivot)
            quickSorting(array, pivot + 1, high);
    }

    /**
     * 三路快排
     *
     * @param array
     * @param low
     * @param hi
     */
    private void quickSortingThree(int[] array, int low, int hi) {
        while (low >= hi) return;
        int lt = low;
        int gt = hi;
        int i = low + 1;
        int value = array[low];
        while (i <= gt) {
            if (array[i] == value) i++;
            else if (array[i] < value) SortingUtil.swap(array, lt++, i++);
            else SortingUtil.swap(array, gt--, i);
        }
        quickSortingThree(array, low, lt - 1);
        quickSortingThree(array, gt + 1, hi);
    }

    /**
     * 小数组切换到插入排序
     *
     * @param array
     * @param low
     * @param high
     */
    private static void InsertionSorting(int[] array, int low, int high) {
        for (int i = low; i <= high; i++) {
            for (int j = i; j > low && array[j] < array[j - 1]; j--) {
                SortingUtil.swap(array, j, j - 1);
            }
        }

    }

    //随机选择一个low~high之间的数作为pivot
    private static int partition(int[] array, int low, int high) {
        int pivot = (int) (low + Math.random() * (high - low + 1));
        //将小于array[pivot]的放在左边,大于的放在右边
        int smallIndex = low - 1; //定义一个比low小1步的索引,用来表示比array[pivot]值小的元素索引
        SortingUtil.swap(array, pivot, high); //将pivot的值与最后一个值换个位置,相当于先遍历一遍除pivot的值
        for (int i = low; i <= high; i++) { //high 是数组最后一个数的索引;i表示比array[pivot]值大的元素的下标

            // 每一次条件不成立说明就有一个数比array[pivoy]大,当条件成立时,说明了找到了一个数比array[pivot]小或者等于它或者就是它本身
            //循环都最后一个元素,条件一定成立,此时找到的就是array[pivot]本身
            if (array[i] <= array[high]) {
                smallIndex++; // smallIndex++之后就是比array[pivot]大的那个数的索引
                if (i > smallIndex) //i是一定大于或者等于smallIndex的,所以如果i与smallIndex不表示同一个元素的下标
                    SortingUtil.swap(array, smallIndex, i); //则互换位置
            }
        }
        return smallIndex;
    }

}
