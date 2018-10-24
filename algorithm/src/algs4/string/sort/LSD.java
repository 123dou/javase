package algs4.string.sort;

import java.util.Arrays;

/**
 * 低位优先的字符串排序:适合用于键的长度都相同的字符串排
 * 从右到左依次使用键索引计数法
 */
public class LSD {
    public static void main(String[] args) {
        String[] strings = {"4pgc938", "2iye230", "3ci0720", "1ick750", "10hv845", "4jzy524", "1ick750", "3ci0720", "10hv845",
                "10hv845", "2rla629", "2rla629", "3atw723"};
        System.out.println(Arrays.toString(strings));
        sort(strings, 7);
        System.out.println(Arrays.toString(strings));
    }

    /**
     * 其中strings为要排序的字符串数组, w为每个字符串的长度
     *
     * @param strings
     * @param w
     */
    public static void sort(String[] strings, int w) {
        //通过前w个字符将字符串排序
        int N = strings.length;
        int R = 256; //英文字符每个字符用两个字节表示: 2^8 = 256,所有的字符都在0 ~ 255这个范围内
        String[] aux = new String[N];
        for (int i = w - 1; i >= 0; i--) {
            //计算每个字符出现的频率
            int[] freq = new int[R + 1];
            for (int j = 0; j < N; j++) freq[strings[j].charAt(i) + 1]++;
            //将频率转换为索引
            for (int j = 0; j < R; j++) {
                freq[j + 1] += freq[j];
            }
            //将元素分类
            for (int j = 0; j < N; j++) {
                aux[freq[strings[j].charAt(i)]++] = strings[j];
            }
            //回写
            for (int j = 0; j < N; j++) {
                strings[j] = aux[j];
            }
        }
        System.out.println(Arrays.toString(aux));
    }
}
