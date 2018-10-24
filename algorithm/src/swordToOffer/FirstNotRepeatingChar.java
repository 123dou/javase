package swordToOffer;

/**
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
 * 如果没有则返回 -1（需要区分大小写
 */
public class FirstNotRepeatingChar {
    public static void main(String[] args) {

    }


    public int FirstNotRepeatingChar(String str) {
        int[] fre = new int[52];
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if (c >= 'a' && c <= 'z') fre[c - 'a']++;
            else fre[c - 'A' + 26]++;
        }
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                if (fre[c - 'a'] == 1) return i;
            } else {
                if (fre[c - 'A' + 26] == 1) return i;
            }
        }
        return -1;
    }

}
