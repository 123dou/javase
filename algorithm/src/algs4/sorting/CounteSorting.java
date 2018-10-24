package algs4.sorting;

import java.util.Arrays;

/**
 * 计数法:只适用于整数,且知道该数组中的最大值
 * 1.找出待排序的数组中最大和最小的元素；
 * 2.统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 * 3.对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 * 4.反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
 *
 * @author dou
 */
public class CounteSorting {

    //这是不考虑空间复杂度的简单算法
    public static int[] counteSorting1(int[] array, int max) {
        int[] temp = new int[max]; //新建一个临时数组,大小为排序数组的最大值
        Arrays.fill(temp, 0); //初始化临时数组为0
        for (int i = 0; i < array.length; i++) {
            temp[array[i]]++;  //用temp的下标与array[i]相同,且temp下标对应的值表示arra[i]个相同的值
        }
        for (int i = 0, j = 0; i < temp.length; i++) { //重新填充array
            while (temp[i] > 0) {
                array[j] = i;
                j++;
                temp[i]--;
            }
        }
        return array;
    }

    //这是最省空间的算法
    public static int[] counteSorting(int[] array) {
        //如果数组为空或者只有一个元素则返回
        if (SortingUtil.isNullOrOneElement(array))
            return array;
        //数组长度
        int length = array.length;
        int min = array[0];
        int max = array[0];
        for (int i = 0; i < length; i++) { //求出数组的最小值与最大值
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }
        int[] temp = new int[max - min + 1]; //新建一个临时数组,大小为排序数组的最大值与最小值之差
        for (int i = 0; i < array.length; i++) {
            temp[array[i] - min]++;  //用temp的下标与array[i]相同,且temp下标对应的值表示arra[i]个相同的值
        }
        for (int i = 0, j = 0; i < temp.length; i++) { //重新填充array
            while (temp[i] > 0) {
                array[j] = i - min;
                j++;
                temp[i]--;
            }
        }
        return array;
    }

}
