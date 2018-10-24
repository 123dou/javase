package algs4.string.search;

/**
 * KMP子字符串查找算法:适合前缀重复较多的情况
 * 难点是在构造dfa[][]有限状态机数组:
 */
public class KMP {
    private int R = 256; //输入的字符全都在[0, R)这个范围内
    private int dfa[][]; //有限状态机
    private int X; //不匹配时,依次输入pat.charAt([1, j - 1])后的状态,初始状态为0
    private String pat; //需要匹配的子字符串

    public KMP(String pat) { //初始化有限状态机
        this.pat = pat;
        int M = pat.length();
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int i = 1; i < M; i++) {
            for (int r = 0; r < R; r++) dfa[r][i] = dfa[r][X]; //不匹配时,回溯到X的状态
            dfa[pat.charAt(i)][i] = i + 1; //匹配时直接到下一个状态
            X = dfa[pat.charAt(i)][X]; //迭代更新回溯状态
        }
    }

    /**
     * kmp算法:
     *
     * @param txt
     * @return
     */
    public int search(String txt) {
        int i, N = txt.length();
        int j, M = this.pat.length();
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[txt.charAt(i)][j]; //txt.chatAt(i) == pat.charAt(j)时就直接令j=下一个状态
        if (j == M)
            return i - M;
        else
            return -1;
    }

    public static void main(String[] args) {
        String txt = "helgfsdgsfdgsdfgsllo";
        String pat = "ll";
        KMP kmp = new KMP(pat);
        System.out.println(txt.substring(kmp.search(txt), kmp.search(txt) + pat.length()));
    }
}
