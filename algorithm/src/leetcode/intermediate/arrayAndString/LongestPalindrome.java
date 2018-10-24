package leetcode.intermediate.arrayAndString;

import java.util.LinkedList;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * 单字符也是回文
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "fjkdljflabcbcbajljl";
        String string = longestPalindrome3(s);
        System.out.println(string);
        String s1 = longestPalindrome4(s);
        System.out.println(s1);

    }


    public static String longestPalindrome4(String s) {
        if (s == null || s.length() < 2) return s;
        char[] chars = s.toCharArray();
        String longestPLD = "";
        for (int i = 0; i < chars.length; i++) {
            String str = getLongestPalindrome(chars, i);
            if (str.length() > longestPLD.length()) longestPLD = str;
        }
        return longestPLD;
    }

    /**
     * 从某个字符开始寻找最长的回文串
     *
     * @param chars 字符数组
     * @param n     从该字符位置开始寻找最长的回文串
     * @return
     */
    public static String getLongestPalindrome(char[] chars, int n) {
        StringBuilder sb = new StringBuilder();
        int i = n - 1;
        int j = n + 1;
        //先拼接连续相等的字符
        while (i >= 0) {
            if (chars[i] == chars[n]) i--;
            else break;
        }
        while (j < chars.length) {
            if (chars[j] == chars[n]) j++;
            else break;
        }
        while (i >= 0 && j < chars.length) {
            if (chars[i] == chars[j]) {
                i--;
                ++j;
            } else {
                for (int k = i + 1; k < j; k++) sb.append(chars[k]);
                break;
            }
        }
        return sb.toString();
    }


    /**
     * 我是怎么写出狗屎一样的代码的.........
     *
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s) {
        if (s == null)
            return s;
        char[] chars = s.toCharArray();
        int longest = 1; //最长的回文子串的长度
        int start = 0; //最长的回文子串的起始索引
        for (int i = 0; i <= chars.length - 2; i++) {
            int left = i - 1;
            int right = i + 2;
            boolean flag = true;
            int length;
            if (chars[i] == chars[i + 1]) {
                length = 2;
                while (left >= 0 || right < chars.length) {
                    if (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                        left--;
                        right++;
                        length += 2;
                        flag = false;
                    } else if (flag && left >= 0 && chars[left] == chars[i]) {
                        left--;
                        length++;
                    } else if (flag && right < chars.length && chars[right] == chars[i]) {
                        right++;
                        length++;
                    } else {
                        break;
                    }
                }
                if (length > longest) {
                    longest = length;
                    start = ++left;
                }
            }
        }
        for (int j = 0; j <= chars.length - 3; j++) {
            int left = j - 1;
            int right = j + 3;
            int length;
            if (chars[j] == chars[j + 2]) {
                length = 3;
                while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                    left--;
                    right++;
                    length += 2;
                }
                if (length > longest) {
                    longest = length;
                    start = ++left;
                }
            }
        }
        if (longest > 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = start; i < start + longest; i++) {
                sb.append(chars[i]);
            }
            return sb.toString();
        } else {
            return "" + chars[0];
        }
    }


    /**
     * 穷举法:动态规划
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 2) {
            if (chars[0] == chars[1])
                return s;
        }
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> list2 = new LinkedList<>();
        LinkedList<Integer> list3 = new LinkedList<>();
        for (int i = 0; i <= chars.length - 2; i++) { //找出所有的长度为2的回文子串
            sb.append(chars[i]).append(chars[i + 1]);
            if (isPalindrome(sb.toString())) {
                if (sb.toString().length() == 2) {
                    list2.addLast(i + 1);
                }
            }
            sb.delete(0, sb.length());
        }
        for (int i = 0; i <= chars.length - 3; i++) { //找出所有的长度为3的回文子串
            sb.append(chars[i]).append(chars[i + 1]).append(chars[i + 2]);
            if (isPalindrome(sb.toString())) {
                if (sb.toString().length() == 3) {
                    list3.addLast(i + 2);
                }
            }
            sb.delete(0, sb.length());
        }
        sb.delete(0, sb.length());
        int longest = 0;
        int start = 0;
        if (list2.size() != 0) {
            longest = 2;
            start = 0;
            for (int j = 0; j < list2.size(); j++) { //用所有的长度为
                int[] ints = findLongestPalindrome2(chars, list2.get(j));
                if (ints[1] >= longest) {
                    longest = ints[1];
                    start = ints[0];
                }
            }
        }
        if (list3.size() != 0) {
            for (int i = 0; i < list3.size(); i++) {
                int[] ints = findLongestPalindrome3(chars, list3.get(i));
                if (ints[1] >= longest) {
                    longest = ints[1];
                    start = ints[0];
                }
            }
        }
        if (list2.size() != 0 || list3.size() != 0) {
            for (int i = start; i < start + longest; i++) {
                sb.append(chars[i]);
            }
            return sb.toString();
        } else {
            return "" + chars[0];
        }
    }

    /**
     * 通过长度为2的子串来找:返回一个只有两个整数的数组,第一个数为字符数组的起始索引,第二个数为长度
     *
     * @param chars
     * @param n
     * @return
     */
    private static int[] findLongestPalindrome2(char[] chars, int n) {
        int longest = 2;
        int start = n - 1;
        int left = n - 2;
        int right = n + 1;
        int length = 2;
        boolean flag = true;
        while (left >= 0 || right < chars.length) {
            if (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                left--;
                right++;
                length += 2;
                flag = false;
            } else {
                if (flag && left >= 0 && chars[left] == chars[n]) {
                    left--;
                    length++;
                } else if (flag && right < length && chars[right] == chars[n]) {
                    right++;
                    length++;
                } else
                    break;

            }
        }
        if (length > longest) {
            longest = length;
            start = ++left;
        }
        return new int[]{start, longest};
    }

    /**
     * 通过长度为3的子串来找:返回一个只有两个整数的数组,第一个数为字符数组的起始索引,第二个数为长度
     *
     * @param chars
     * @param n
     * @return
     */
    private static int[] findLongestPalindrome3(char[] chars, int n) {
        int longest = 3;
        int start = n - 2;
        int left = n - 3;
        int right = n + 1;
        int length = 3;
        while (left >= 0 && right < chars.length) {
            if (chars[left] == chars[right]) {
                left--;
                right++;
                length += 2;
            } else {
                break;
            }
        }
        if (length > longest) {
            longest = length;
            start = left + 1;
        }
        return new int[]{start, longest};
    }

    /**
     * 判断一个字符串是否为回文字符串
     *
     * @param s
     * @return
     */
    private static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        int length = s.length();
        if (length == 1)
            return true;
        char[] chars = s.toCharArray();
        for (int i = 0; i < length / 2; i++) {
            if (chars[i] != chars[length - 1 - i])
                return false;
        }
        return true;
    }

    /**
     * 暴力法:
     *
     * @param s
     * @return
     */
    public static String longestPalindromeExhaustive(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        String longestStr = "";
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                sb.append(chars[j]);
                if (isPalindrome(sb.toString())) {
                    if (sb.toString().length() > longestStr.length())
                        longestStr = sb.toString();
                }
            }
            if (longestStr.length() >= chars.length - i)
                break;
            sb.delete(0, sb.length());
        }
        return longestStr;
    }
}



