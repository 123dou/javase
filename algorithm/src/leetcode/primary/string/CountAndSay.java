package leetcode.primary.string;

public class CountAndSay {
    public static void main(String[] args) {
        int n = 3;
        String s = countAndSay(50);
        System.out.println(s);
    }

    /**
     * @param n
     * @return
     */
    public String countAndSay2(int n) {
        if (n <= 1) return "1";
        String pre = "1";
        for (int i = 2; i <= n; i++)
            pre = countAndSay(pre);
        return pre;
    }


    private String countAndSay(String pre) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pre.length(); ) {
            char c = pre.charAt(i);
            int n = 0;
            while (i < pre.length() && pre.charAt(i) == c) {
                n++;
                i++;
            }
            sb.append(n).append(c);
        }
        return sb.toString();
    }


    /**
     * 关键在于通过n-1找n
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if (n == 1)
            return "" + 1;
        else if (n == 2)
            return "" + 11;
        String s;
        StringBuilder sb = new StringBuilder("11");
        int count;
        for (int i = 2; i < n; i++) {
            s = sb.toString();
            sb.delete(0, sb.length());
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; ) {
                count = 1;
                while (j < chars.length - 1 && chars[j] == chars[j + 1]) {
                    j++;
                    count++;
                }
                sb.append(count).append(chars[j]);
                if (j == chars.length && chars[chars.length - 2] != '1')
                    sb.append(1).append(1);
                j++;
            }
        }
        return sb.toString();
    }
}
