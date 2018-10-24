package algs4.sorting;

/**
 * 冒泡排序:
 * 1.比较相邻的一对元素,然后交换位置
 * 2.对每一对相邻的元素作相同的工作,从开始到第一对到接尾最后一对,这样在最后的元素就一定是最大的(或者最小的)
 * 3.针对所有的元素重复以上的排序,除了最后一个
 * 重1~3步到排序完成
 *
 * @author dou
 */
public class Bubble {
    public static int[] bubbleSorting(int[] array) {
        //如果数组为空或者只有一个元素就返回
        if (SortingUtil.isNullOrOneElement(array))
            return array;
        //数组长度
        int length = array.length;
        //每一次外层循环就选出一个最大值或者最小值
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) { //内层循环排除掉已排好序的i个
                if (array[j + 1] < array[j]) //从小到大排
                    SortingUtil.swap(array, j, j + 1); //交换数组内下表分别为i,j的两个元素的值
            }
        }
        return array;
    }
}
