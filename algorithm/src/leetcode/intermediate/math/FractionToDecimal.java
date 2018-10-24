package leetcode.intermediate.math;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 * <p>
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * <p>
 * 示例 1:
 * <p>
 * 输入: numerator = 1, denominator = 2
 * 输出: "0.5"
 * 示例 2:
 * <p>
 * 输入: numerator = 2, denominator = 1
 * 输出: "2"
 * 示例 3:
 * <p>
 * 输入: numerator = 2, denominator = 3
 * 输出: "0.(6)"
 */
public class FractionToDecimal {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(2, 11));
        System.out.println(2.0 / 11);
    }


    //余数有重复出现则必然是循环小数
    public String fractionToDecimal3(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0))
            sb.append("-");
        //防止溢出
        long ln = Math.abs((long) numerator);
        long ld = Math.abs((long) denominator);
        long posi = ln / ld;
        long remainder = ln % ld;
        if (remainder == 0) return sb.append(posi).toString();
        sb.append(posi).append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (!map.containsKey(remainder)) {
            map.put(remainder, sb.length());
            long n = remainder * 10;
            remainder = n % ld;
            sb.append(n / ld);
            if (remainder == 0) return sb.toString();
        }
        int index = map.get(remainder);
        sb.insert(index, "(");
        sb.append(")");
        return sb.toString();
    }


    /**
     * 当有两个余数相同时,小数就开始进入循环
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) return null;
        StringBuilder sb = new StringBuilder();
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) sb.append("-");
        long dividend = Math.abs((long) numerator); //避免溢出
        long divisor = Math.abs((long) denominator); //避免溢出
        long i = dividend / divisor; //整数部分
        sb.append(i);
        long remainder = dividend % divisor; //余数
        if (remainder == 0) return sb.toString();
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>(); //记录余数,及循环的开始
        map.put(remainder, sb.length());
        while (remainder != 0) {
            remainder *= 10;
            sb.append(remainder / divisor);
            remainder = remainder % divisor;
            if (map.containsKey(remainder)) {
                Integer index = map.get(remainder);
                sb.insert(index, "(").append(")");
                return sb.toString();
            }
            map.put(remainder, sb.length());
        }
        return sb.toString();
    }
}
