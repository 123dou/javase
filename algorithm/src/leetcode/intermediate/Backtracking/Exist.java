package leetcode.intermediate.Backtracking;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.regexp.internal.RE;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中 “相邻” 单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 */
public class Exist {
    public static void main(String[] args) {
        char[][] board = {{'a', 'a', 'a'}, {'a', 'b', 'b'}, {'a', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}, {'a', 'a', 'a'},
                {'b', 'b', 'b'}, {'a', 'b', 'b'}, {'a', 'a', 'b'}, {'a', 'b', 'a'}
        };
        String word = "aabaaaabbb";
        boolean exist = exist(board, word);
        System.out.println(exist);
    }

    static boolean result = false;

    public static boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) return false;
        if (word.length() > board.length * board[0].length) return false;
        boolean[][] marked = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exist(board, marked, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    /**
     * 类似深度搜索:
     *
     * @param board  二维数组
     * @param marked 记录路径信息:路径上的值为true
     * @param word   要查找的单词
     * @param k      遍历的深度,当其到达字符串的最后一个字符的位置时,结束
     * @param i      二维数组的横
     * @param j      二维数组的行
     * @return
     */
    public static boolean exist(char[][] board, boolean[][] marked, String word, int k, int i, int j) {
        if (k >= word.length() || i >= board.length || i < 0 || j < 0) return false;
        if (j >= board[i].length || board[i][j] != word.charAt(k) || marked[i][j]) return false;
        if (k == word.length() - 1) {
            if (board[i][j] == word.charAt(k)) {
                result = true;
                return result;
            }
            return result;
        }
        if (board[i][j] == word.charAt(k)) {
            marked[i][j] = true;
            if (!exist(board, marked, word, k + 1, i + 1, j)) //向下搜
                if (!exist(board, marked, word, k + 1, i - 1, j)) //向上搜
                    if (!exist(board, marked, word, k + 1, i, j + 1)) //向右搜
                        exist(board, marked, word, k + 1, i, j - 1); //向左搜
        }
        marked[i][j] = false; //回溯
        return result;
    }
}