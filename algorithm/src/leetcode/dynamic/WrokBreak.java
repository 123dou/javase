package leetcode.dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class WrokBreak {
    public static void main(String[] args) {
        String s = "goalspecial";
        List<String> wordDict = Arrays.asList("go", "goal", "goals", "special");
        boolean b = workBreak(s, wordDict);
        System.out.println(b);
    }

    Set<String> map = new HashSet();

    public boolean wordBreak2(String s, List<String> wordDict) {
        if (wordDict.contains(s)) return true;
        if (map.contains(s)) return false;
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (wordBreak2(s.substring(word.length()), wordDict)) return true;
            }
        }
        map.add(s);
        return false;
    }


    /**
     * dp[i]表示0~i个字符可以由字典构成,则dp[i] = dp[j] && s.substring(dp[j+1], dp[i+1]) 在字典里面
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean workBreak(String s, List<String> wordDict) {
        if (s == null) return true;
        if (wordDict == null) return false;
        Set<String> set = new HashSet<>();
        set.addAll(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder(s.substring(0, i + 1));
            for (int j = 0; j <= i; j++) {
                if (dp[j] && set.contains(sb.toString())) {
                    dp[i + 1] = true;
                    break;
                }
                sb.deleteCharAt(0);
            }
        }
        return dp[s.length()];
    }
}
