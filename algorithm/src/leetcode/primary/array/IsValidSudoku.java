package leetcode.primary.array;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * <p>
 * <p>
 * 上图是一个部分填充的有效的数独。
 * <p>
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 */
public class IsValidSudoku {
    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '2', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        boolean b = isValiSudoku(board);
        System.out.println(b);
    }

    /**
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] signs = new int[3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;
                int n = 1 << (board[i][j] - '1');
                int cubeIndex = i / 3 * 3 + j / 3;
                if ((signs[0][i] & n) != 0 || (signs[1][j] & n) != 0
                        || (signs[2][cubeIndex] & n) != 0)
                    return false;
                signs[0][i] |= n;
                signs[1][j] |= n;
                signs[2][cubeIndex] |= n;
            }
        }
        return true;
    }


    /**
     * 先解决9个数能否成立的问题,再解决本问题
     *
     * @param board
     * @return
     */
    public static boolean isValiSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) { //检验行
            if (!isValue(board[i]))
                return false;
        }
        char[] aid = new char[9];
        for (int i = 0; i < 9; i++) { //检验列
            for (int j = 0; j < 9; j++) {
                aid[j] = board[j][i];
            }
            if (!isValue(aid))
                return false;
        }
        for (int i = 0; i < 9; i += 3) { //3*3的格子
            for (int j = 0; j < 9; j += 3) {
                int len = 0;
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        aid[len++] = board[k][l];
                    }
                }
                if (!isValue(aid))
                    return false;
            }
        }
        return true;
    }

    /**
     * 判断给定一个数组是否有校
     *
     * @param array
     * @return
     */
    private static boolean isValue(char[] array) {
        int[] aid = new int[9];
        for (int i = 0; i < array.length; i++) {
            if ('.' != array[i]) {
                aid[array[i] - 49]++;
            }
        }
        for (int i = 0; i < aid.length; i++) {
            if (aid[i] > 1)
                return false;
        }
        return true;
    }
}
