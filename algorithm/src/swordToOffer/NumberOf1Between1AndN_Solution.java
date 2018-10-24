package swordToOffer;

import javax.lang.model.type.IntersectionType;

/**
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 */
public class NumberOf1Between1AndN_Solution {
    public static void main(String[] args) {
        int n = 253;
        NumberOf1Between1AndN_Solution ns = new NumberOf1Between1AndN_Solution();
        int i = ns.NumberOf1Between1AndN_Solution(n);
        System.out.println(i);
    }

    /**
     * 从 1 至 10，在它们的个位数中，任意的 X 都出现了 1 次。
     * 从 1 至 100，在它们的十位数中，任意的 X 都出现了 10 次。
     * 从 1 至 1000，在它们的千位数中，任意的 X 都出现了 100 次。
     *
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n <= 0) return 0;
        int count = 0;
        for (int i = 1; i <= n; i *= 10) {
            int current = (n / i) % 10;
            int left = n / (i * 10);
            int right = n % i;
            //如果为0,出现1的次数由高位决定,等于高位数字 * 当前位数
            if (current < 1)
                count += left * i;
                //如果为1,出现1的次数由高位和低位决定,高位*当前位+低位+1
            else if (current == 1)
                count += left * i + right + 1;
                //如果大于1,出现1的次数由高位决定,//（高位数字+1）* 当前位数
            else {
                count += (left + 1) * i;
            }
        }
        return count;
    }


    public int NumberOf1Between1AndN_Solution2(int n) {
        if (n <= 0) return 0;
        int sum = 0;
        for (int i = 0; i <= n; i *= 10) {
            int current = (n / i) % 10;
            int left = n / (i * 10);
            int right = n % i;
            if (current < 1) sum += left * i;
            else if (current == 1) sum += (left * i + right + 1);
            else sum += (left + 1) * i;
        }
        return 0;
    }

}
