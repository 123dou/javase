package algs4.sorting;

import static algs4.sorting.SortingUtil.swap;

public class HeapSorting {
    /**
     * 从小到大排序就构建最大堆,从大到小排就构建最小堆
     * 最大堆的父结点必需满足父结点的元素大于子结点的元素,因此根元素永远是堆里最大的元素,最小堆的父结点必需满足父结点元素小于子结点,所以根元素永远是堆里面最小的元素
     * 所以堆排序:只要把元素插入堆中然后,逐一删除跟元素就行了
     * 堆排序有两个在重要的操作:插入元素和删除元素,插入元素的关键操作是向上堆化元素,删入元素的关键操作是向下堆化元素
     *
     * @param array
     */
    public static void headSorting(int[] array) {
        if (array == null && array.length == 1) return;
        int length = array.length;
        int lastIndex = array.length - 1;
        for (int i = 1; i < length; i++) { //堆化数组
            int curr = i;  //当前结点
            int parent = (curr - 1) / 2; //当前结点的父结点
            while (curr > 0 && array[curr] > array[parent]) { //向上堆化元素,
                swap(array, curr, parent);
                curr = parent;
                parent = (curr - 1) / 2;
            }
        }

        for (int i = 0; i < length; i++) { //删除堆,向下堆化元素
            swap(array, 0, lastIndex);
            lastIndex--;
            int j = 0;
            while (2 * j + 1 <= lastIndex) {
                int t = 2 * j + 1;
                if (t + 1 <= lastIndex && array[t + 1] > array[t]) t++;
                if (array[t] <= array[j]) break;
                swap(array, j, t);
                j = t;
            }
        }
    }

}
