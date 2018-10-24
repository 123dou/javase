package algs4.string.sort;

import java.util.Arrays;

/**
 * '
 * 三向字符串快速排序: 在将字符串数组strings排序时,根据他们的首字母进行三向切分,然后递归的将得到的三个字符数组排序:
 * -->一个含有所有首字母小于切分字符的字符串子数组
 * -->一个含有所有首字母等于切分字符的字符串的子数组(排序时忽略他们的首字母)
 * -->一个含有所有首字母大于切分字符的字符串的子数组
 */
public class Quick3String {
    public static void main(String[] args) {
        String[] strs = {"she", "shells", "seashells", "by", "the", "seashore", "the", "shells", "she", "sells", "are", "surely", "seashells"};
        System.out.println(Arrays.toString(strs));
        sort(strs);
        System.out.println(Arrays.toString(strs));
    }

    private static void sort(String[] strs) {
        sort(strs, 0, strs.length - 1, 0);
    }


    private static void sort(String[] strings, int low, int high, int d) {
        if (high <= low)
            return;
        int temp = (int) (low + Math.random() * (high - low));
        exch(strings, low, temp);
        int lt = low; //小于lt的索引的字符串的第d个关键值都比第一个索引小
        int gt = high; //大于gt的索引的字符串的第d个关键值都比第一个索引的大
        int v = chartAt(strings[low], d);
        int i = low + 1;
        while (i <= gt) {
            int t = chartAt(strings[i], d); //第i个索引的字符串的第dg 关键值
            if (t < v)
                exch(strings, lt++, i++);
            else if (t > v)
                exch(strings, i, gt--);
            else
                i++;
        }
        sort(strings, low, lt - 1, d); //递归排序比第一个索引的关键值小的字符串的第d个关键值
        if (v >= 0)
            sort(strings, lt, gt, d + 1); //递归排序跟第一个索引的关键值相同的字符串第d+1个关键值
        sort(strings, gt + 1, high, d); //递归排序比第一个索引的关键值大的字符串第d个关键值
    }

    private static void exch(String[] strings, int i, int j) {
        String temp = strings[i];
        strings[i] = strings[j];
        strings[j] = temp;
    }

    /**
     * 返回string中字符索引为d的字符
     *
     * @param string
     * @param d
     * @return
     */
    private static int chartAt(String string, int d) {
        if (d < string.length())
            return string.charAt(d);
        else
            return -1;
    }
}
