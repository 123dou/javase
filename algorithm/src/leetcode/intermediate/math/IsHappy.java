package leetcode.intermediate.math;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;

import javax.naming.RefAddr;
import java.util.*;

/**
 * 编写一个算法来判断一个数是不是 “快乐数”。
 * <p>
 * 一个 “快乐数” 定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 * <p>
 * 示例:
 * <p>
 * 输入: 19
 * 输出: true
 * 解释:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class IsHappy {
    public static void main(String[] args) {
        int n = 11;
        boolean happy = isHappy(n);
        System.out.println(happy);
    }

    /**
     * 检查有没有循环或者n>
     *
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        return isHappy(n, set);
    }

    public static boolean isHappy(int n, Set<Integer> set) {
        long sum = 0;
        while (n > 0) {
            int remainder = n % 10;
            sum += remainder * remainder;
            n /= 10;
        }
        if (sum == 1) return true;
        if (sum > Integer.MAX_VALUE) return false;
        if (!set.add((int) sum)) return false;
        return isHappy((int) sum, set);
    }
}
