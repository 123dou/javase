package swordToOffer;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class PrintMinNumber {
    public static void main(String[] args) {
        int[] numbers = {32, 32, 34, 3};
        PrintMinNumber pn = new PrintMinNumber();
        String s = pn.PrintMinNumber(numbers);
        System.out.println(s);
    }

    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        String[] nums = integerToString(numbers);
        int maxlen = getMaxLen(nums);
        MSD(nums, 0, 0, nums.length - 1, maxlen);
        StringBuilder sb = new StringBuilder();
        for (String s : nums) sb.append(s);
        return sb.toString();
    }

    /**
     * 3, 32, 34 ---> 32 > 3 > 34   //比较规则
     *
     * @param strs
     * @param index
     * @param low
     * @param hi
     * @param maxlen
     */
    private void MSD(String[] strs, int index, int low, int hi, int maxlen) {
        if (low >= hi || index >= maxlen) return; //index >= maxlen条件避免栈溢出
        int[] fre = new int[12];
        String[] aid = new String[strs.length];
        for (int i = low; i <= hi; i++) {
            //计算频率
            fre[charAt(strs[i], index) + 1]++;
        }
        //频率转化起始索引
        for (int i = 0; i < 11; i++) fre[i + 1] += fre[i];
        //排序
        for (int i = low; i <= hi; i++) aid[low + fre[charAt(strs[i], index)]++] = strs[i];
        //回写
        for (int i = low; i <= hi; i++) strs[i] = aid[i];
        //递归排序
        for (int i = 0; i < 10; i++)
            MSD(strs, index + 1, low + fre[i], low + fre[i + 1] - 1, maxlen);
    }

    /**
     * 当index大于等于s.length(),返回最后一个字符,循环比较最后一个字符
     *
     * @param s
     * @param index
     * @return
     */
    private int charAt(String s, int index) {
        return index >= s.length() ? s.charAt(s.length() - 1) - '0' : s.charAt(index) - '0';
    }

    private String[] integerToString(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = Integer.toString(nums[i]);
        }
        return strs;
    }

    private int getMaxLen(String[] strs) {
        int max = -1;
        for (String s : strs) max = Math.max(max, s.length());
        return max;
    }

}

