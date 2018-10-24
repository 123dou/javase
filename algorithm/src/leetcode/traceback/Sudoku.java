package leetcode.traceback;

import java.util.Arrays;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * <p>
 * <p>
 * 一个数独。
 * <p>
 * <p>
 * <p>
 * 答案被标成红色。
 * <p>
 * Note:
 * <p>
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
public class Sudoku {
    public static void main(String[] args) {
        char[][] board = {
                {'.', '.', '5', '3', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                {'.', '7', '.', '.', '1', '.', '5', '.', '.'},
                {'4', '.', '.', '.', '.', '5', '3', '.', '.'},
                {'.', '1', '.', '.', '7', '.', '.', '.', '6'},
                {'.', '.', '3', '2', '.', '.', '.', '8', '.'},
                {'.', '6', '.', '5', '.', '.', '.', '.', '9'},
                {'.', '.', '4', '.', '.', '.', '.', '3', '.'},
                {'.', '.', '.', '.', '.', '9', '7', '.', '.'}
        };
        solveSudoku(board);
        for (int i = 0; i < 9; i++) System.out.println(Arrays.toString(board[i]));
    }

    public static void solveSudoku(char[][] board) {
        solveSudoku(board, 0, 0);
    }

    /**
     * 1.确定边界
     * 2.确定下一步的选择
     * 3.回溯
     * 4.剪枝
     *
     * @param board
     * @param n
     * @param m
     * @return
     */
    public static boolean solveSudoku(char[][] board, int n, int m) {
        if (board[n][m] != '.') {
            if (m == 8) {
                if (n == 8) return true;
                else return solveSudoku(board, n + 1, 0);
            } else return solveSudoku(board, n, m + 1);
        } else {
            boolean[] sign = new boolean[9];
            checkZone(board, sign, n, m);
            for (int k = 0; k < 9; k++) {
                if (!sign[k]) {
                    board[n][m] = (char) ('1' + k);
                    if (m == 8) {
                        if (n == 8) return true;
                        if (solveSudoku(board, n + 1, 0)) return true;
                        else board[n][m] = '.';
                    } else {
                        if (solveSudoku(board, n, m + 1)) return true;
                        else board[n][m] = '.';
                    }
                }
            }
        }
        return false;
    }

    /**
     * 标记不能选的元素
     *
     * @param board
     * @param sign
     * @param i
     * @param j
     */
    private static void checkZone(char[][] board, boolean[] sign, int i, int j) {
        for (int k = 0; k < 9; k++) {
            if (board[i][k] != '.') sign[board[i][k] - '1'] = true;
            if (board[k][j] != '.') sign[board[k][j] - '1'] = true;
        }
        i = getStart(i);
        j = getStart(j);
        for (int n = i; n < i + 3; n++) {
            for (int m = j; m < j + 3; m++) {
                if (board[n][m] != '.') sign[board[n][m] - '1'] = true;
            }
        }
    }

    private static int getStart(int i) {
        if (i >= 0 && i < 3) return 0;
        else if (i >= 3 && i < 6) return 3;
        else return 6;
    }

}
