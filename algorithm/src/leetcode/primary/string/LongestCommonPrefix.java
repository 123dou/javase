package leetcode.primary.string;

import java.awt.datatransfer.StringSelection;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"f"};
        String s = longestCommonPrefix3(strs);
        System.out.println(s);
    }


    /**
     * 结合二叉查找
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;
        for (String s : strs) minLen = Math.min(minLen, s.length());
        int low = 0;
        int hi = minLen - 1;
        int mid;
        while (low <= hi) {
            mid = (low + hi) >>> 1;
            if (isCommonPrefix(strs, strs[0].substring(0, mid))) low = mid + 1;
            else hi = mid - 1;
        }
        String res = strs[0].substring(0, low);
        if (isCommonPrefix(strs, res)) return res;
        return strs[0].substring(0, low - 1);
    }

    /**
     * 判断数组中是否有该公共前缀
     *
     * @param strs
     * @param str
     * @return
     */
    private static boolean isCommonPrefix(String[] strs, String str) {
        for (String s : strs) {
            if (!s.startsWith(str)) return false;
        }
        return true;
    }


    /**
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int min = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < strs[min].length()) min = i;
        }

        int dup;
        for (dup = 0; dup < strs[min].length(); dup++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(dup) != strs[min].charAt(dup)) return strs[min].substring(0, dup);
                if (dup == strs[min].length() - 1 && j == strs.length - 1) return strs[min];
            }
        }
        return "";
    }


    /**
     * 先找出数组中单词长度最小的单词,然后拿该单词跟剩下单词比较同样的位置
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        int commonPrefixLen = 0;
        for (int i = 1; i < strs.length; i++) {
            if (strs[0].length() > strs[i].length()) {
                String temp = strs[0];
                strs[0] = strs[i];
                strs[i] = temp;
            }
        }
        boolean flag = true;
        for (int j = 0; j < strs[0].length(); j++) {
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(j) != strs[0].charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (!flag)
                break;
            else
                commonPrefixLen++;
        }
        if (commonPrefixLen != 0)
            return strs[0].substring(0, commonPrefixLen);
        else
            return "";
    }
}
