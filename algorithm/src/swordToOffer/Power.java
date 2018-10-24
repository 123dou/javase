package swordToOffer;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class Power {
    public double Power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        return power(base, exponent);
    }

    //非递归
    private double power(double base, int exponent) {
        double b = base;
        double odd = 1;
        while (exponent > 1) {
            if ((exponent & 1) == 1) odd *= b;
            base *= base;
            exponent /= 2;
        }
        return odd * base;
    }


    //递归解法
    private double power(double base, int exponent, double b) {
        if (exponent == 1) return base;
        base *= base;
        base = power(base, exponent / 2, b);
        if ((exponent & 1) == 1) base *= b;
        return base;
    }
}
