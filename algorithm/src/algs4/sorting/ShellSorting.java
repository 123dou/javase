package algs4.sorting;

/**
 * 希尔法:改进版的插入法,希尔排序是把记录按下表的一定增量分组,对每组使用直接插入排序算法排序,随着增量逐渐减少,每组包含的关键词越来越多,当增量减至1时
 * 整个文件恰被分成一组,算法终止
 * 1.选择一个增量序列t1,t2,...tk,其中ti>tj,tk=1;
 * 2.按增量序列个数k,对序列进行k趟排序
 * 3.每趟排序,根据对应的增量ti,将待排序分割成若干个长度为m的子序列,分别对各子表进行直接插入排序,仅增量因子为1时,整个表作为一个表来处理,表长度即
 * 整个序列的长度
 *
 * @author dou
 */
public class ShellSorting {

    public static int[] shellSorting(int[] array) {
        //如果数组为null或者数组长度为1,则返回
        if (SortingUtil.isNullOrOneElement(array))
            return array;
        //数组长度
        int length = array.length;
        //增量大小,初始增量是1/2length,每次缩小1/2
        int increment = length / 2;
        while (increment > 0) { //当增量大于零就循环
            for (int i = increment; i < length; i++) { //增量为increment的插入法
                int preIndex = i - increment;
                int currValue = array[i];
                while (preIndex >= 0 && currValue < array[preIndex]) {
                    array[preIndex + increment] = array[preIndex];
                    preIndex -= increment;
                }
                array[preIndex + increment] = currValue;
            }
            increment = increment / 2;
        }
        return array;
    }

}
