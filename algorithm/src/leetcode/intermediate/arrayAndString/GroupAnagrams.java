package leetcode.intermediate.arrayAndString;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"k"};
        List<List<String>> lists = groupAnagrams(strs);
        System.out.println(lists);
    }

    /**
     * 先对每个子符串重新排序,再重新散列
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null) return Collections.emptyList();
        List<List<String>> lists = new LinkedList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            int i = Arrays.hashCode(chars);
            List<String> list = map.get(i);
            if (list == null)
                list = new LinkedList<>();
            list.add(str);
            map.put(i, list);
        }
        lists.addAll(map.values());
        return lists;
    }


    /**
     * 虽然过了,但是太耗时
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) return Collections.emptyList();
        List<List<String>> lists = new LinkedList<>();
        int length = strs.length;
        for (int i = 0; i < length; i++) {
            List<String> list = new LinkedList<>();
            list.add(strs[i]);
            for (int j = i + 1; j < length; j++) {
                if (isAnagram(strs[i], strs[j])) {
                    list.add(strs[j]);
                    swap(strs, j, length - 1);
                    --length;
                    --j;
                }
            }
            lists.add(list);
        }
        return lists;
    }

    public static void swap(String[] strs, int i, int j) {
        String temp = strs[i];
        strs[i] = strs[j];
        strs[j] = temp;
    }


    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        char[] char1 = new char[26];
        char[] char2 = new char[26];
        for (int i = 0; i < str1.length(); i++) {
            char1[str1.charAt(i) - 'a']++;
            char2[str2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (char1[i] != char2[i]) return false;
        }
        return true;
    }

}
