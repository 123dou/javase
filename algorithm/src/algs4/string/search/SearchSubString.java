package algs4.string.search;

public class SearchSubString {

    public static void main(String[] args) {
        String txt = "fjaldjlfjldfajdlkfjalsdjflkasjdlfjalsdjfl";
        String pat = "jlf";
        int search = search(pat, txt);
        System.out.println(search);
        int i = search2(pat, txt);
        System.out.println(i);
    }


    /**
     * 暴力法查找子符串
     *
     * @param pat 要匹配的子串
     * @param txt 在该串中查找子串
     * @return
     */
    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        //用i跟踪txt,用j跟踪pat
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++)
                if (txt.charAt(i + j) != pat.charAt(j)) break;
            if (j == M) return i;
        }
        return N; //未找到匹配
    }

    /**
     * 暴力的法的另一种实现
     * 使用i跟踪txt,使用j跟踪pat,在i和j指向的字符相匹配时,代码进行的比较和上一个实现相同.
     * 这段代码中的i值相当于上一段代码中的i+j,它指向的是文本已经匹配过的字符序列的末端(i以前指向的是这个序列的开头).
     * 如果i和j指向的字符不匹配了,那么需要回退这两个指针的值:将j重新指向模式的开头,将i指向本只匹配的开始的下一个字符.
     *
     * @param pat
     * @param txt
     * @return
     */
    public static int search2(String pat, String txt) {
        int j, M = pat.length();
        int i, N = txt.length();
        for (i = 0, j = 0; j < M && i < N; i++) {
            if (pat.charAt(j) == txt.charAt(i)) j++;
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == M)
            return i - M;
        return N;
    }
}
