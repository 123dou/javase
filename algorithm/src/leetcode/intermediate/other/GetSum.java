package leetcode.intermediate.other;

/**
 * 不使用运算符 + 和-，计算两整数a 、b之和。
 * <p>
 * 示例：
 * 若 a = 1 ，b = 2，返回 3。
 * <p>
 * 致谢：
 * 特别感谢 @fujiaozhu 添加这道问题并创建测试用例。
 */
public class GetSum {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int sum = getSum(a, b);
        System.out.println(sum);
        switch (1) {
            case 1:
            default:
                System.out.println(1);
        }
    }

    /**
     * 直接按位操作
     *
     * @param a
     * @param b
     * @return
     */
    public static int getSum(int a, int b) {
        int sum = 0;
        boolean count = false;
        int A;
        int B;
        for (int i = 0; i < 32; i++) {
            A = a & 1;
            B = b & 1;
            if ((A & B) == 1) {
                if (count) sum = sum | (1 << i);
                else count = true;
            } else if ((A | B) == 0) {
                if (count) {
                    sum = sum | (1 << i);
                    count = false;
                }
            } else {
                if (!count) sum = sum | (1 << i);
            }
            a >>>= 1;
            b >>>= 1;
        }
        return sum;
    }
}
