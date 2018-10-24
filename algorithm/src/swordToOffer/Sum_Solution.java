package swordToOffer;

/**
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Sum_Solution {
    public static void main(String[] args) {

    }

    public int Sum_Solution(int n) {
        return sum(n);
    }

    private int sum(int n) {
        int res = n;
        boolean b = (res > 1) && (res += sum(n - 1)) > 1;
        return res;
    }

}
