package leetcode;

import java.util.*;

/**
 * 给定一组独特的单词， 找出在给定列表中不同 的索引对(i, j), 使得关联的两个单词，例如：words[i] + words[j]形成回文。
 * <p>
 * 示例 1:
 * 给定 words = ["bat", "tab", "cat"]
 * 返回 [[0, 1], [1, 0]]
 * 回文是 ["battab", "tabbat"]
 * <p>
 * 示例 2:
 * 给定 words = ["abcd", "dcba", "lls", "s", "sssll"]
 * 返回 [[0, 1], [1, 0], [3, 2], [2, 4]]
 * 回文是 ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class PalindromePairs {
    public static void main(String[] args) {
        String[] words = new String[]{"a", "a"};
        List<List<Integer>> listList = palindromePairs1(words);
        System.out.println(listList);
    }

    /**
     * @param words
     * @return
     */
    public static List<List<Integer>> palindromePairs3(String[] words) {
        List<List<Integer>> listList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        StringBuilder sb_head = new StringBuilder();
        StringBuilder sb_tail = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey("") && map.get("") != i) {
                int k = map.get("");
                List<Integer> l1 = new ArrayList<>();
                l1.add(i);
                l1.add(k);
                listList.add(l1);
                List<Integer> l2 = new ArrayList<>();
                l2.add(k);
                l2.add(i);
                listList.add(l2);
            }
            char[] chars = words[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                sb_head.append(chars[j]).reverse();
                int n = -1;
                if (map.containsKey(sb_head.toString()) && ((n = map.get(sb_head.toString())) != i)
                        && (isPalindrome(words[i].substring(j + 1)))) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(n);
                    listList.add(list);
                }
                sb_head.reverse();
                sb_tail.append(chars[chars.length - 1 - j]);
                if (j != chars.length - 1) {
                    int m = -1;
                    if (map.containsKey(sb_tail.toString()) && (m = map.get(sb_tail.toString())) != i
                            && isPalindrome(words[i].substring(0, chars.length - 1 - j))) {
                        List<Integer> list = new ArrayList<>();
                        list.add(m);
                        list.add(i);
                        listList.add(list);
                    }
                }
            }
            sb_head.delete(0, sb_head.length());
            sb_tail.delete(0, sb_tail.length());
        }
        return listList;
    }

    /**
     * @param words
     * @return
     */
    public static List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        for (int i = 0; i < words.length; i++) {
            int l = 0, r = 0;
            while (l <= r) {
                String s = words[i].substring(l, r);
                Integer j = map.get(new StringBuilder(s).reverse().toString());
                if (j != null && i != j && isPalindrome(words[i].substring(l == 0 ? r : 0, l == 0 ? words[i].length() : l)))
                    res.add(Arrays.asList(l == 0 ? new Integer[]{i, j} : new Integer[]{j, i}));
                if (r < words[i].length()) ++r;
                else ++l;
            }
        }
        return res;
    }

    /**
     * 暴力法
     *
     * @param words
     * @return
     */
    public static List<List<Integer>> palindromePairs1(String[] words) {
        if (words == null)
            return null;
        List<List<Integer>> listList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= words.length - 2; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isPalindrome(sb.append(words[i]).append(words[j]).toString())) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    listList.add(list);
                }
                sb.delete(0, sb.length());
                if (isPalindrome(sb.append(words[j]).append(words[i]).toString())) {
                    List<Integer> list = new ArrayList<>();
                    list.add(j);
                    list.add(i);
                    listList.add(list);
                }
                sb.delete(0, sb.length());
            }

        }

        return listList;

    }

    public static boolean isPalindrome(String s) {
        if (s == null)
            return false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i])
                return false;
        }
        return true;
    }

}
