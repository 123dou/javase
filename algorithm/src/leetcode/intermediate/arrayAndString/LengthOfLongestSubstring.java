package leetcode.intermediate.arrayAndString;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * <p>
 * 示例：
 * <p>
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是 3。
 * <p>
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是 1。
 * <p>
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是 3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "dvdf";
        int i = lengthOfLongestSubstring2(s);
        System.out.println(i);
        StringBuilder stringBuilder = new StringBuilder("fjlasdj");

    }

    /**
     * 双指针
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null) return 0;
        int max = 0;
        int count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = count; j < i; j++) {
                if (chars[i] == chars[j]) {
                    count = j + 1;
                    break;
                }
            }
            max = Math.max(max, i - count + 1);
        }
        return max;
    }


    /**
     * 用map来做辅助存储结构,时间复杂度有待提高
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int MaxLength = Integer.MIN_VALUE;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], i);
            } else {
                if (map.size() > MaxLength) MaxLength = map.size();
                Integer n = map.get(chars[i]);
                map = new HashMap<>();
                i = n;
            }
        }
        if (map.size() > MaxLength) MaxLength = map.size();
        return MaxLength;
    }
}
