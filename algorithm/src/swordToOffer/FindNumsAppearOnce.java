package swordToOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 */
public class FindNumsAppearOnce {
    public static void main(String[] args) {

    }

    //num1,num2分别为长度为1的数组。传出参数
    //将num1[0],num2[0]设置为返回结果
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length == 0) return;
        int n = 0;
        for (int i : array) n ^= i;
        List<Integer> bit1 = new ArrayList<>();
        List<Integer> bit0 = new ArrayList<>();
        for (int i : array)
            if ((i & n) == n) bit1.add(i);
            else bit0.add(i);
        for (int i : bit1) num1[0] ^= i;
        for (int i : bit0) num2[0] ^= i;
    }
}
