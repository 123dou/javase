package niukewang.DynamicPlanning;

import java.util.Currency;
import java.util.Map;
import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/c996bbb77dd447d681ec6907ccfb488a
 * 来源：牛客网
 * <p>
 * 对于两个字符串，请设计一个高效算法，求他们的最长公共子序列的长度，这里的最长公共子序列定义为有两个序列 U1,U2,U3...Un 和 V1,V2,V3...Vn,
 * 其中 Ui&ltUi+1，Vi&ltVi+1。且 A[Ui] == B[Vi]。
 * <p>
 * 给定两个字符串 A 和 B，同时给定两个串的长度 n 和 m，请返回最长公共子序列的长度。保证两串长度均小于等于 300。
 * <p>
 * 测试样例：
 * "1A2C3D4B56",10,"B1D23CA45B6A",12
 * 返回：6
 */
public class LCS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String context = in.next();
        String[] split = context.split(",");
        int n = Integer.valueOf(split[1]);
        int m = Integer.valueOf(split[3]);
        char[] charsN = split[0].toCharArray();
        char[] charsM = split[2].toCharArray();
        int result = solution(n, charsN, m, charsM);
        System.out.println(result);
    }

    /**
     * 动态规划的求法:找出装态转移方程:设F(i,j)为序列X的前i个连续子序列与序列Yj的前j个连续子序列的最长公共子序列,Xi表示序列中的第i个字符,Yj表示Y序列中的第j个字符
     * F(i,j)= -->0 ,i = j = 0;
     * -->F(i-1,j-1) + 1, Xi = Yj
     * -->max{F(i-1,j), F(i,j-1)}, Xi != Yj
     * 0     1   2   3   4   5   6   7   8   9
     * <p>
     * YJ    0     3   5   7   4   8   6   7   8   2
     * <p>
     * 0     Xi    0     0   0   0   0   0   0   0   0   0
     * 1     1     0     0   0   0   0   0   0   0   0   0
     * 2     3     0     1   1   1   1   1   1   1   1   1
     * 3     4     0     1   1   1   2   2   2   2   2   2
     * 4     5     0     1   2   2   2   2   2   2   2   2
     * 5     6     0     1   2   2   2   2   3   3   3   3
     * 6     7     0     1   2   3   3   3   3   4   4   4
     * 7     7     0     1   2   3   3   3   3   4   4   4
     * 8     8     0     1   2   3   3   4   4   4   5   5
     *
     * @param n
     * @param arrayN
     * @param m
     * @param arrayM
     * @return
     */
    public static int solution(int n, char[] arrayN, int m, char[] arrayM) {
        int[][] result = new int[n + 1][m + 1]; //result[i][j] 表示n的前i个连续子序列,和m的前j个连续子序列的最成公共子序列
        //本来java的数组的int的类型new出来的时候,默认值就是0
//        for (int i = 0; i < n + 1; i++) { //初始化,当n的子序列为0时,公共子序列为0
//            result[i][0] = 0;
//        }
//        for (int i = 0; i < m + 1; i++) { //初始化,当m的子序列为0时,公共子序列为0
//            result[0][i] = 0;
//        }1A2C3D4B56,10,B1D23CA45B6A,12
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (arrayN[i - 1] == arrayM[j - 1]) result[i][j] = result[i - 1][j - 1] + 1;
                else result[i][j] = Math.max(result[i - 1][j], result[i][j - 1]);
            }
        }
        return result[n][m];
    }

}
