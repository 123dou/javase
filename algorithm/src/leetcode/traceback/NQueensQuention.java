package leetcode.traceback;

import java.util.LinkedList;
import java.util.List;

public class NQueensQuention {
    public static void main(String[] args) {
        int n = 8;
        int[][] marked = new int[n][n];
        String[][] strs = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) strs[i][j] = ".";
        }
        List<List<String>> lists = new LinkedList<>();
        solveNQueens(n, marked, lists, strs, 0);
        for (List<String> l : lists) System.out.println(l);
        System.out.println(lists.size());
    }

    /**
     * 有一个值得关注的点是marked用的int类型的数组不是boolean类型的,如果用boolean类行的话,当某个点被标记多次之后,
     * 记录不了这个多次标记的状态,回溯的时候会把所有的点都当做只标记了一次处理,那么就会误删标记点
     *
     * @param n      皇后的数量
     * @param marked n * n 的int类型数组, 用来标记却定一个皇后以后, 其它皇后不能取的位置 -- 列,左对角线,右对角线
     * @param lists  存储解法
     * @param strs   n * n 的字符串数组, 所有元素初始为"."
     * @param row    确定第row行皇后的位置
     */
    public static void solveNQueens(int n, int[][] marked, List<List<String>> lists, String[][] strs, int row) {
        for (int i = 0; i < n; i++) {
            if (marked[row][i] == 0) {
                strs[row][i] = "Q";
                if (row == n - 1) { //递归终止的条件
                    List<String> l = new LinkedList<>();
                    for (int r = 0; r < n; r++) {
                        StringBuilder sb = new StringBuilder();
                        for (int c = 0; c < n; c++) sb.append(strs[r][c]);
                        l.add(sb.toString());
                    }
                    lists.add(l);
                    strs[row][i] = ".";
                    return;
                }

                //标记右下角对角线
                int rR = row, cR = i;
                while (rR < n && cR < n) {
                    marked[rR][cR++]++;
                    marked[rR++][i]++;
                }

                //标记列
                int r2 = rR;
                while (r2 < n) marked[r2++][i]++;
                //标记左下角对角线
                int rL = row + 1, cL = i - 1;
                while (rL < n && cL >= 0) marked[rL++][cL--]++;

                solveNQueens(n, marked, lists, strs, row + 1);

                strs[row][i] = ".";
                //右下角对角线回溯
                cR--;
                rR--;
                r2--;
                while (r2 > rR) marked[r2--][i]--;
                while (rR >= row && cR >= i) {
                    marked[rR][cR--]--;
                    marked[rR--][i]--;
                }
                //左下角对角线回溯
                rL--;
                cL++;
                while (rL > row && cL < i) marked[rL--][cL++]--;
            }
        }
    }
}
