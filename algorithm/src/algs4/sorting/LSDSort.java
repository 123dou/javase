package algs4.sorting;

public class LSDSort {
    private static final int[] SIZE_OF_NUMBER = {9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE};

    /**
     * 相当与字符串的低位排序
     *
     * @param array
     */
    public static void lsdSort(int[] array) {
        int max = array[0];
        for (Integer i : array) max = max > i ? max : i;
        int count;
        count = sizeOfNumber(max);
        int[] lsd = new int[array.length];
        for (int i = 0; i < count; i++) {
            int[] aux = new int[11];
            //计算频率
            for (int j = 0; j < array.length; j++) {
                aux[getIndex(array[j], i) + 1]++;
            }
            //频率转化为索引
            for (int j = 0; j < 9; j++) aux[j + 1] += aux[j];
            //数字分类排序
            for (int j = 0; j < lsd.length; j++) lsd[aux[getIndex(array[j], i)]++] = array[j];
            //回写
            for (int j = 0; j < array.length; j++) array[j] = lsd[j];
        }
    }

    private static int sizeOfNumber(int x) {
        for (int i = 0; ; i++) {
            if (x <= SIZE_OF_NUMBER[i]) {
                return i + 1;
            }
        }
    }

    /**
     * @param i     参与排序的数
     * @param index i的第index位参与排序
     * @return
     */
    private static int getIndex(int i, int index) {
        int divisor = 1;
        for (int n = 0; n < index; n++) divisor *= 10;
        i /= divisor;
        return i % 10;
    }
}
