package leetcode.primary.math;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class CountPrimes {
    public static void main(String[] args) {
        int n = 1500000;
        int i = countPrimes(n);
        System.out.println(i);
    }

    /**
     * 大于5的素数一定在6的倍数的左右
     * 一般的方法
     *
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        if (n <= 2) return 0;
        int count = 1;
        boolean[] isPremes = new boolean[n];
        for (int i = 3; i < n; i++) {
            if ((i & 1) == 0) isPremes[i] = false;
            else isPremes[i] = true;
        }
        for (int i = 3; i <= Math.sqrt(n); i++) {
            if (isPremes[i]) {
                ++count;
                for (int j = i + i; j < n; j += i) {
                    isPremes[j] = false;
                }
            }
        }
        for (int i = (int) Math.sqrt(n) + 1; i < n; i++) {
            if (isPremes[i]) ++count;
        }
        return count;
    }

    /**
     * @param n
     * @return
     */
    public static int countPrimes2(int n) {
        if (n <= 2) return 0;
        int count = 1;
        for (int i = 3; i < n; i++) {
            for (int j = 3; j < Math.sqrt(i); j++) {
                if (isPrime(i)) ++count;
            }
        }
        return count;
    }

    /**
     * 暴力的
     *
     * @param n
     * @return
     */
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        boolean flag = true;
        int sqrt_n = (int) Math.sqrt(n);
        for (int j = 2; j <= sqrt_n; j++) {
            if (n % j == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 定理1:先有一个引理，如果有：X%Z=0，即 X 能被 Z 整除，则有：(X+Y)%Z=Y%Z
     * 设有 X、Y 和 Z 三个正整数，则必有：(X*Y)%Z=((X%Z)*(Y%Z))%Z
     * 蒙格马利:快速幂模算法
     *
     * @param n
     * @param p
     * @param m
     * @return
     */
    public static int Montgomer(int n, int p, int m) {
        int k = 1;
        n %= m;
        while (p > 1) {
            if (0 != (p & 1)) k = (k * n) % m;
            n = (n * n) % m;
            p >>= 1;
        }
        return (n * k) % m;
    }
}
