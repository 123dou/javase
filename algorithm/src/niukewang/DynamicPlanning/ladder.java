package niukewang.DynamicPlanning;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 有n个阶梯,每次只能走一步或者两步,一共有多少种走法/
 * 例如:有十个阶梯,每次走一步:1,1,1,1,1,1,1,1,1,1 是一种走法；每次走两步2,2,2,2,2也是一种走法
 * 输入描述: 2 (表示n个阶梯)
 * 输出描述: 2 (表示总的走法数)
 */
public class ladder {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(solution3(n));
    }

    /**
     * 直接递归:时间复杂度为2^n次方
     *
     * @param n
     * @return
     */
    public static int solution(int n) {
        if (n < 1)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        return solution(n - 1) + solution(n - 2);
    }

    /**
     * 备忘录算法,若有相同的结果则不继续递归,直接返回之前保存在HashMap中的值
     * :每次把不同参数的计算结果存在哈希,当遇到相同参数时,就不用重复计算了
     */
    private static HashMap<Integer, Integer> map = new HashMap<>();

    public static int solution2(int n) {
        if (n < 1)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        if (map.containsKey(n))
            return map.get(n);
        else {
            int result = solution(n - 1) + solution(n - 2);
            map.put(n, result);
            return result;
        }
    }

    /**
     * 空间复杂度更低的解法
     */
    public static int solution3(int n) {
        if (n < 1)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }

}
