package algs4.string.sort;

import java.util.Arrays;

/**
 * 高位优先排序:
 */
public class MSD {
    private static final int M = 1; //当数组小于等于这个数时,转换为插入排序
    private static int R = 26; //基数
    private static String[] aux; //数据分类的辅助数组

    public static void main(String[] args) {

        String[] strs = {"she", "shells", "seashells", "by", "the", "seashore", "the", "shells", "she", "sells", "are", "surely", "seashells"};
        System.out.println(Arrays.toString(strs));
        sort(strs);
        System.out.println(Arrays.toString(strs));
    }

    public static void sort(String[] strs) {
        int N = strs.length;
        aux = new String[N];
        sort(strs, 0, N - 1, 0);
    }

    /**
     * 以第d个子符为位键将a[lo]至a[hi]排序
     *
     * @param strs
     * @param lo
     * @param hi
     * @param d
     */
    private static void sort(String[] strs, int lo, int hi, int d) {
        //要排序的数量少于M转化为用插入排序
        if (hi <= lo + M) {
            inserSort(strs, lo, hi, d);
            return;
        }
        int[] count = new int[R + 2]; //要考虑索引超出字符串长度的情况
        for (int i = lo; i <= hi; i++) { //计算频率
            count[charAt(strs[i], d) + 2]++;
        }
        for (int r = 0; r < R + 1; r++) { //将频率转换为索引
            count[r + 1] += count[r];
        }
        for (int i = lo; i <= hi; i++) { //数据分类
            aux[count[charAt(strs[i], d) + 1]++] = strs[i];
        }
        for (int i = lo; i <= hi; i++) { //回写
            strs[i] = aux[i - lo];
        }
        //递归的以每个字符为键进行排序
        for (int r = 0; r < R; r++) {
            sort(strs, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    private static void inserSort(String[] strs, int lo, int hi, int d) {
        //从第d个字符开始对strs[lo]到strs[hi]排序
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(strs[j], strs[j - 1], d); j--) {
                exch(strs, j, j - 1);
            }
        }
    }

    private static void exch(String[] strs, int j, int i) {
        String temp = strs[i];
        strs[i] = strs[j];
        strs[j] = temp;
    }

    private static boolean less(String str, String str1, int d) {
        return str.substring(d).compareTo(str1.substring(d)) < 0;
    }

    /**
     * 若d小于str的长度则返回str中索引为d的字符,否则返回-1
     *
     * @param str
     * @param d
     * @return
     */
    private static int charAt(String str, int d) {
        assert d >= 0 && d <= str.length();
        if (d < str.length())
            return str.charAt(d) - 'a';
        else
            return -1;
    }


}
