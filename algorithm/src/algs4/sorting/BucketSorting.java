package algs4.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * 桶计数法:
 * 1.人为的设置一个bucketSize,作为每个桶所能放置多少个不同数值(例如当bucketSize==5)时,该桶可以存放{1,2,3,4,5,}这几种数字,但是容量不算,
 * 即可以存放1003
 * 2.遍里输入数据,并且把数据一个一个放到对应的桶里去
 * 3.对每个不是空的桶进行排序,可以使用其它排序方法,也可以递归使用桶排序
 * 4.从不是空的桶里把排好的数据拼接起来
 *
 * @author dou
 */
public class BucketSorting {

    public static List<Integer> bucketSorting(List<Integer> array, int bucketSize) {
        //如果数组为空,或者长度小于2,返回
        if (array == null || array.size() < 2)
            return array;
        int min = array.get(0);
        int max = array.get(0);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max) max = array.get(i);
            if (array.get(i) < min) min = array.get(i);
        }
        int bucketCounte = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCounte);
        List<Integer> result = new ArrayList<>(array.size());
        for (int i = 0; i < bucketCounte; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int i = 0; i < array.size(); i++) {
            int index = (array.get(i) - min) / bucketSize;
            ArrayList<Integer> bucket = buckets.get(index);
            bucket.add(array.get(i));
        }
        for (int i = 0; i < buckets.size(); i++) {
            ArrayList<Integer> bucket = buckets.get(i);
            insertSort(bucket);
            result.addAll(bucket);
        }
        return result;
    }

    /**
     * 对桶里的元素使用插入排序
     *
     * @param list
     */
    private static void insertSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int currValue = list.get(i);
            int pre = i - 1;
            int preValue;
            while (pre >= 0 && currValue < (preValue = list.get(pre))) {
                list.set(pre + 1, preValue);
                pre--;
            }
            list.set(pre + 1, currValue);
        }
    }

}
