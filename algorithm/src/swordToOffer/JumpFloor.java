package swordToOffer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class JumpFloor {
    //简单的动态规划
    public int JumpFloor(int target) {
        if (target == 1) return 1;
        if (target == 2) return 2;
        int a = 1;
        int b = 2;
        for (int i = 3; i <= target; i++) {
            int t = a + b;
            a = b;
            b = t;
        }
        return b;
    }
}
