package leetcode.primary.string;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc"
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class ReverseWordsInAStringiii {
    public static void main(String[] args) {

    }

    public String reverseWords(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != ' ') {
                int t = i++;
                while (i < arr.length && arr[i] != ' ') i++;
                reverse(arr, t, i - 1);
            }
        }
        return String.valueOf(arr);
    }

    private void reverse(char[] arr, int low, int hi) {
        while (low < hi) {
            swap(arr, low++, hi--);
        }
    }

    private void swap(char[] arr, int i, int j) {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }
}
