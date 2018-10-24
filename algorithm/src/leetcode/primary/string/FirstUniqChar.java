package leetcode.primary.string;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 案例:
 * <p>
 * s = "leetcode"
 * 返回 0.
 * <p>
 * s = "loveleetcode",
 * 返回 2.
 * <p>
 * <p>
 * 注意事项：您可以假定该字符串只包含小写字母。
 */
public class FirstUniqChar {
    public static void main(String[] args) {
        String s = "loveleetcode";
        int i = firstUniqChar(s);
        System.out.println(i);
    }

    /**
     * 借鉴基数排序
     *
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] chars = s.toCharArray();
        char[] aid = new char[26]; //辅助数组,用于记录s中每个字符出现的次数,索引表示一个字符
        for (int i = 0; i < chars.length; i++) {
            aid[chars[i] - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (aid[chars[i] - 'a'] == 1)
                return i;
        }
        return -1;
    }
}
