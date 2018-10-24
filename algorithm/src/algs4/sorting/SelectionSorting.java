package algs4.sorting;

/**
 * 选择排序:
 * 1.初始装态:无序区为R[1..n],有序区为空
 * 2.第i趟排序(i=1,2,3,...n-1)开始时,当前有序区和无序区分别为R[1...i-1],和R[i...n].该趟排序从当前无序区中选出关键字最小的记录R[k]
 * 将它与无序区的第1个记录R交换,使R[1...i]和R[i+1...n]分别 变为记录个数增加1个的新有序区和记录个数减少一个的新的无序区
 * 3.n-1趟排序,数组有序化
 *
 * @author dou
 */
public class SelectionSorting {
    public static int[] selectionSorting(int[] array) {
        //数组如果为空或者只有一个元素,返回
        if (SortingUtil.isNullOrOneElement(array))
            return array;
        //数组长度
        int length = array.length;
        //
        for (int i = 0; i < length; i++) {
            int minIndex = i; //选择第一个数为最小的
            for (int j = i + 1; j < length; j++) {
                if (array[j] < array[minIndex]) //每一次内循环都选出无序区中最的小的数
                    minIndex = j; //将最小的数的索引付给minIndex
            }
            SortingUtil.swap(array, minIndex, i); //将选出来的最小的数与当前无序区中的第一个数交换
        }
        return array;
    }
}
