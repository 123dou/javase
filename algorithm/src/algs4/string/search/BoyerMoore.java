package algs4.string.search;

import java.util.Arrays;

/**
 * 与KMP子字符串查找算法的实现一样,我们会根据匹配失败时文本和模式中的字符来决定下一步的行动
 */
public class BoyerMoore {
    private int R = 256;
    private int[] right;
    private String pat;

    public BoyerMoore(String pat) { //初始化right
        this.pat = pat;
        right = new int[R];
        int M = pat.length();
        Arrays.fill(right, -1);
        for (int i = 0; i < M; i++) {
            right[pat.charAt(i)] = i;
        }
    }

    /**
     * @param txt
     * @return
     */
    public int search(String txt) {
        int i;
        int j;
        int skip;
        for (i = 0; i <= txt.length() - pat.length(); i += skip) {
            skip = 0;
            for (j = pat.length() - 1; j >= 0; j--) {
                if (txt.charAt(i + j) != this.pat.charAt(j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) {
                        skip = 1;
                        break;
                    }
                }
            }
            if (skip == 0) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String txt = "aabraacadabraacaadabra";
        String pat = "aacaa";
        int M = pat.length();
        BoyerMoore bm = new BoyerMoore(pat);
        int start = bm.search(txt);
        System.out.println(txt.substring(start, start + M));
    }
}
