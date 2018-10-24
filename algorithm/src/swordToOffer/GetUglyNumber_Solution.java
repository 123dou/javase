package swordToOffer;

/**
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class GetUglyNumber_Solution {
    public static void main(String[] args) {
        GetUglyNumber_Solution gs = new GetUglyNumber_Solution();
        int i = gs.GetUglyNumber_Solution(11);
        System.out.println(i);
    }


    private final int TWO = 2;
    private final int THREE = 3;
    private final int FIVE = 5;

    /**
     * 用三个指针分别跟踪2, 3, 5生成的丑数
     *
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if (index < 1) return 0;
        int[] dp = new int[index];
        dp[0] = 1;
        int two = 0, three = 0, five = 0, len = 1;
        while (len < index) {
            int a = dp[two] * TWO;
            int b = dp[three] * THREE;
            int c = dp[five] * FIVE;
            int min = Math.min(Math.min(a, b), c);
            if (a == min) two++;
            if (b == min) three++;
            if (c == min) five++;
            dp[len++] = min;
        }
        return dp[index - 1];
    }


}
