package swordToOffer;

import java.util.Arrays;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class ReOrderArray {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ReOrderArray t = new ReOrderArray();
        System.out.println(Arrays.toString(array));
    }

    //类似插入排序
    public void reOrderArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (isOdd(array[i])) {
                int curr = array[i];
                int pre = i - 1;
                while (pre >= 0 && !isOdd(array[pre])) {
                    array[pre + 1] = array[pre];
                    pre--;
                }
                array[pre + 1] = curr;
            }
        }
    }


    private boolean isOdd(int n) {
        return (n & 1) == 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
