package niukewang.DynamicPlanning.backpackProblem;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 01背包问题: 有N件物品和一个容量为V的背包,第i件物品的体积是c[i],价值是w[i].求解将哪些物品装入背包可使其价值最大.
 * 解 :1.F(i,V)表示前i件物品V恰好放入一个容量为V的背包可以获得的最大价值
 * 2.那么第i件物品有放和不放两种选择 --> 2.1如果不放,那么这个问题就可以转化为前i-1件物品放入容量为V的背包可以获得的最大值问题
 * --> 2.2如果放,那么这个问题就可以转化为前i-1件物品放入容量为V-c[i]的背包+加放入的第i件物品的w[i]的价值
 * 3.结合1,2可以得出其状态转移方程:F(i,V) = max{F(i-1,V), F(i-1,V-c[i])+w[i]}
 * 4.边界:当i为1时,如果c[i]大于V那么,F(1,V)= 0;如果c[i]不大于V,那么F(1,V) = w[i]
 * 测试数据:
 * 300 10
 * 95 89
 * 75 59
 * 23 19
 * 73 43
 * 50 100
 * 22 72
 * 6 44
 * 57 16
 * 89 7
 * 98 64
 */
public class Backpack01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int V = in.nextInt();
        int N = in.nextInt();
        int[] c = new int[N];
        int[] w = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = in.nextInt();
            w[i] = in.nextInt();
        }
//        for (int i = 0; i < N; i++) {
//            w[i] = in.nextInt();
//        }
        int result1 = solution3(N, V, c, w);
        int result2 = solution2(N, V, c, w);
        int result4 = solution4(N, V, c, w);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result4);
    }

    /**
     * 递归实现:时间复杂度太高了
     *
     * @param n
     * @param v
     * @param c
     * @param w
     * @return
     */
    private static HashMap<String, Integer> map = new HashMap<>();

    public static int solution(int n, int v, int[] c, int[] w) {
        String key = "" + n + v;
        if (n == 1)
            if (c[0] > v) {
                map.put(key, 0);
                return 0;
            } else {
                map.put(key, w[0]);
                return w[0];
            }
        int r2 = 0;
        int r1 = 0;
//        if (map.containsKey(key))
//            return map.get(key);
        if (v >= c[n - 1]) { //边界条件
            r1 = solution(n - 1, v - c[n - 1], c, w) + w[n - 1];
            r2 = solution(n - 1, v, c, w);
            int temp2 = r1 > r2 ? r1 : r2;
            map.put(key, temp2);
            return temp2;
        } else {
            int temp = solution(n - 1, v, c, w);
            map.put(key, temp);
            return temp;
        }
    }

    /**
     * @param n
     * @param v
     * @param c
     * @param w
     * @return
     */
    public static int solution3(int n, int v, int[] c, int[] w) {
        int[][] f = new int[n][v + 1]; //f[i][j]表示前i个物品恰好放满了容量为j的背包里
        for (int i = 0; i <= v; i++) {   //初始化第一组数据
            if (i >= c[0])
                f[0][i] = w[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= v; j++) {
                if (j >= c[i]) //根据F(i,V) = max{F(i-1,V), F(i-1,V-c[i])+w[i]}
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - c[i]] + w[i]);
                else
                    f[i][j] = f[i - 1][j];
            }
        }
        return f[n - 1][v];
    }

    /**
     * 空间复杂度最低的解法:F(i,v)为当前i个物品刚好装到容量为v的最大价值
     * 下面是容量为10的背包的装法:序号表示可以装的个数,c为一个背包的体积,w为对应的价值
     * 当一个物品的体积为4,价值为6的时候,当体积小于4的时候,因为装不下,所以总价值为0,当体积不小于4的时候,总价值就为6
     * 则F(2,5) = max{F(1,5), F(1,5-c[1]) + w[2]} = 6, F(2,9) = max{F(1,9), F(1,9 - c[1]) + w[i]} = 10;
     * 以此类推: F(3,10) = max{F(2,10), F(2,10 - c[2]) + w[3]} = 11;
     * F(4,10) = max{F(3,10), F(3,10 - c[3]) + w[4]} = 11;
     * F(5,10) = max{F(4,10), F(4,10 - c[4]) + w[5]} = 15;
     * 序号        c           w        v1  v2  v3  v4  v5  v6  v7  v8  v9  v10
     * 1          4           6         0   0   0   6   6   6   6   6   6    6
     * 2          5           4         0   0   0   6   6   6   6   6   10   10
     * 3          6           5         0   0   0   6   6   6   6   6   10   11
     * 4          2           3         0   3   3   6   6   9   9   9   10   11
     * 5          2           3         0   3   3   6   9   9   12  12  15   15
     * 除了第一行结果:后面的结果都可以从前面的一行结果推导出来,所以只需要维持一个数组的空间就可以了
     * <p>
     * 初始化的细节问题
     * <p>
     * 我们看到的求最优解的背包问题题目中，事实上有两种不太相同的问法。有的题目要求 “恰好装满背包” 时的最优解，有的题目则并没有要求必须把背包装满。
     * 一种区别这两种问法的实现方法是在初始化的时候有所不同。
     * <p>
     * 如果是第一种问法，要求恰好装满背包，那么在初始化时除了 f[0] 为 0 其它 f[1..V] 均设为 -∞，这样就可以保证最终得到的 f[N] 是一种恰好装满背包的最优解。
     * <p>
     * 如果并没有要求必须把背包装满，而是只希望价格尽量大，初始化时应该将 f[0..V] 全部设为 0。
     * <p>
     * 为什么呢？可以这样理解：初始化的 f 数组事实上就是在没有任何物品可以放入背包时的合法状态。如果要求背包恰好装满，
     * 那么此时只有容量为 0 的背包可能被价值为 0 的 nothing“恰好装满”，其它容量的背包均没有合法的解，属于未定义的状态，它们的值就都应该是 -∞了。
     * 如果背包并非必须被装满，那么任何容量的背包都有一个合法解 “什么都不装”，这个解的价值为 0，所以初始时状态的值也就全部为 0 了。
     *
     * @param n
     * @param v
     * @param c
     * @param w
     * @return
     */
    public static int solution2(int n, int v, int[] c, int[] w) {
        int[] preResult = new int[v + 1]; //为持前一个行的结果
        for (int i = 0; i < v + 1; i++) { //初始化第一行
            if (i >= c[0])
                preResult[i] = w[0];
        }
        int[] result = new int[v + 1]; //当前行的结果
        for (int i = 1; i < n; i++) {
            for (int j = v; j >= 1; j--) {
                if (j >= c[i]) //根据F(i,V) = max{F(i-1,V), F(i-1,V-c[i])+w[i]}
                    result[j] = Math.max(preResult[j], preResult[j - c[i]] + w[i]);
                else
                    result[j] = preResult[j];
            }
            preResult = result;
        }
        return result[v];
    }

    /**
     * @param n n件物品
     * @param v 总的容量背包容量
     * @param c c[]数组表示每件物品的体积
     * @param w w[]数组表示每件物品的价值
     * @return
     */
    public static int solution4(int n, int v, int[] c, int[] w) {
        if (n == 0 || v == 0) return 0;
        int[] V = new int[v + 1];
        for (int i = 0; i < c.length; i++) //主循环,第i个物品选择放还是不放
            procedureOneTwoBackpack(w[i], c[i], V); //每个物品的处理过程
        return V[v];
    }

    /**
     * 01背包问题的抽像:一个物品的处理过程
     *
     * @param cost 表示当前物品的花费
     * @param v    v[]表示背包的体积
     * @param V    当前物品的体积
     */
    public static void procedureOneTwoBackpack(int cost, int v, int[] V) {
        for (int j = V.length - 1; j >= v; j--) { //第i个物品的结果由第i-1个推导而来
            V[j] = Math.max(V[j], V[j - v] + cost);
        }
    }
}
