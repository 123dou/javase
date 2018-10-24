package swordToOffer;

import java.util.ArrayList;

/**
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 */
public class FindContinuousSequence {
    public static void main(String[] args) {
        int n = 3;
        FindContinuousSequence fcs = new FindContinuousSequence();
        ArrayList<ArrayList<Integer>> lists = fcs.findContinuousSequence(n);
        System.out.println(lists);
    }

    /**
     * 最优解:双指针,一前一后当指针范围内的数小于sum时就right指针往右移,否则left指针往左移
     *
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        int a = 1;
        int s = 1;
        for (int i = 2; i < sum; i++) {
            s += i;
            if (s == sum) {
                ArrayList<Integer> l = new ArrayList<>();
                for (int j = a; j <= i; j++) l.add(j);
                lists.add(l);
            } else if (s > sum) {
                while (s > sum) {
                    s -= a;
                    a++;
                }
                if (s == sum) {
                    ArrayList<Integer> l = new ArrayList<>();
                    for (int j = a; j <= i; j++) l.add(j);
                    lists.add(l);
                }
            }
        }
        return lists;
    }

    /**
     * 连续序列的和也就是公差为1的等差数列求和, s = a1 * n + (n * (n - 1)) / 2;
     * 然后就可以用二叉查找a1
     *
     * @param n
     * @return
     */
    private ArrayList<ArrayList<Integer>> findContinuousSequence(int n) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (n <= 2) return lists;
        int maxCnt = (int) Math.sqrt(2 * n);
        for (int i = maxCnt; i >= 2; i--) {
            int t = n - (i * (i - 1) / 2);
            if (t % i != 0) continue;
            int a1 = t / i;
            findAndAdd(n / 2 + 1, a1, i, lists);
        }
        return lists;
    }

    private void findAndAdd(int n, int a1, int c, ArrayList<ArrayList<Integer>> lists) {
        int low = 0, hi = n, mid;
        while (low <= hi) {
            mid = (low + hi) >>> 1;
            if (mid == a1) {
                ArrayList<Integer> l = new ArrayList<>();
                for (int i = mid; i < mid + c; i++) l.add(i);
                lists.add(l);
                return;
            }
            if (a1 > mid) low = mid + 1;
            else hi = mid - 1;
        }
    }


}
