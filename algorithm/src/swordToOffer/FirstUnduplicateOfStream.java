package swordToOffer;

import java.util.LinkedList;

/**
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 */
public class FirstUnduplicateOfStream {

    private int[] map = new int[256];
    private LinkedList<Character> q = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        map[ch]++;
        q.add(ch);
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        while (!q.isEmpty()) {
            char c = q.peek();
            if (map[c] == 1) return c;
            else q.poll();
        }
        return '#';
    }


}
