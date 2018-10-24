package swordToOffer;

/**
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
 * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class ReverseSentence {
    public static void main(String[] args) {
        ReverseSentence rs = new ReverseSentence();
        String string = "wonderful";
        String s = rs.ReverseSentence(string);
        System.out.println(s);
    }

    public String ReverseSentence(String str) {
        if (str == null || str.length() == 0) return str;
        char[] chars = str.toCharArray();
        reverse(chars, 0, chars.length - 1);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                int left = i;
                while (i < chars.length && chars[i] != ' ') i++;
                if (i <= chars.length)
                    reverse(chars, left, i - 1);
            }
        }
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            swap(chars, left++, right--);
        }
    }


    private void swap(char[] chars, int i, int j) {
        char c = chars[i];
        chars[i] = chars[j];
        chars[j] = c;
    }
}
