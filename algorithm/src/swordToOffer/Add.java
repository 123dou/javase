package swordToOffer;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class Add {
    public static void main(String[] args) {

    }

    /**
     * 二进制加法:
     * 1. a =1 && b =1 : 若有进位 ；若无进位
     * 2. a = 0 && b = 0  若有进位 ；若无进位
     * 3. a = 1, b = 0 || a = 0, b = 1  若有进位 ；若无进位
     *
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1, int num2) {
        int sum = 0;
        int point = 1;
        boolean count = false;
        for (int i = 0; i < 32; i++) {
            int a = (num1 & 1);
            int b = (num2 & 1);
            if ((a & b) == 1) {
                if (!count) count = true;
                else sum |= point;
            } else if ((a | b) == 0) {
                if (count) {
                    count = false;
                    sum |= point;
                }
            } else {
                if (!count) sum |= point;
            }
            point <<= 1;
            num1 >>>= 1;
            num2 >>>= 1;
        }
        return sum;
    }
}
