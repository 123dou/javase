package swordToOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class JumpFloorII {
    //f(n) = f(n - 1) + f(n - 2) +...f(2) + f(1);
    public int JumpFloorII(int target) {
        if (target == 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (int i = 3; i <= target; i++) {
            int s = sum(list);
            int next = s + 1;
            list.add(next);
        }
        return list.get(list.size() - 1);
    }

    private int sum(List<Integer> list) {
        int sum = 0;
        for (int i : list) sum += i;
        return sum;
    }
}
