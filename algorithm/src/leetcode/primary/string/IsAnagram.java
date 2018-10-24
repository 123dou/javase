package leetcode.primary.string;

public class IsAnagram {
    public static void main(String[] args) {
        String s = "aabbbb";
        String t = "bbaaaa";
        boolean b = isAnagram2(s, t);
        System.out.println(b);
    }

    /**
     * 还是借鉴基数排序
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram2(String s, String t) {
        if (s == null || t == null || s.length() != t.length())
            return false;
        if (s.length() == 0 && t.length() == 0)
            return true;
        char[] s_ch = s.toCharArray();
        char[] t_ch = t.toCharArray();
        char[] aid_s = new char[26];
        char[] aid_t = new char[26];
        for (int i = 0; i < s_ch.length; i++) {
            aid_s[s_ch[i] - 'a']++;
            aid_t[t_ch[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (aid_s[i] != aid_t[i])
                return false;
        }
        return true;
    }
}
