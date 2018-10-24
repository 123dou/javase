package swordToOffer;

import java.util.ArrayList;

/**
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，
 * 输出两个数的乘积最小的。
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 */
public class FindNumbersWithSum {
    public static void main(String[] args) {

    }

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length == 0) return list;
        int l = 0, r = array.length - 1;
        while (l < r) {
            int s = array[l] + array[r];
            if (s == sum) {
                list.add(array[l]);
                list.add(array[r]);
                break;
            }
            if (s > sum) r--;
            else l++;
        }
        return list;
    }
}
