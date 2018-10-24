package algs4.string.search;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
    private long patHash; //需要匹配的子字符串的散列值
    private int M; //需要匹配的子字符串的长度
    private long Q; //一个很大的素数
    private int R = 256; //字母表的大小
    private long RM; //R^(M - 1) % Q

    public RabinKarp(String pat) {
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 0; i < M - 1; i++) {
            RM = (R * RM) % Q; //等同于R^M-1 % Q 这样写是为了防止溢出
        }
        patHash = hash(pat, M);
    }

    public int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash)
            return 0;
        for (int i = M; i < N; i++) {
            //额外加了Q是为了保证为正数
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash)
                return i - M + 1;
        }
        return -1;
    }

    /**
     * 根据key和M生成散列数
     *
     * @param key
     * @param M
     * @return
     */
    private long hash(String key, int M) {
        long h = 0;
        for (int i = 0; i < M; i++) {
            h = (R * h + key.charAt(i)) % Q;
        }
        return h;
    }

    /**
     * 生成一个31位长的素数
     *
     * @return
     */
    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    public static void main(String[] args) {
        String txt = "jfdlajfljaldkjflababackjsdlfjlalkfdjslkf";
        String pat = "ababac";
        int M = pat.length();
        RabinKarp rp = new RabinKarp(pat);
        int start = rp.search(txt);
        if (start != -1)
            System.out.println(txt.substring(start, start + M));
        else
            System.out.println("not found");
    }

}
