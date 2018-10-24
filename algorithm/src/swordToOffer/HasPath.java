package swordToOffer;

import java.util.HashMap;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
 * 例如 a b c e s f c s a d e e 这样的3 X 4 矩
 * 阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 */
public class HasPath {
    public static void main(String[] args) {
        String s = "ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS";
        String str = "SGGFIECVAASABCEHJIGQEM";
        HasPath t = new HasPath();
        boolean b = t.hasPath(s.toCharArray(), 5, 8, str.toCharArray());
        System.out.println(b);
    }

    private char[][] arrs;
    private int rows;
    private int cols;
    private char[] str;
    private boolean[][] marked;
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        if (matrix == null || matrix.length == 0 || str.length > matrix.length) return false;
        arrs = new char[rows][cols];
        marked = new boolean[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.str = str;
        for (int i = 0, j = 0; i < matrix.length; i += cols, j++) {
            for (int k = 0; k < cols; k++) {
                arrs[j][k] = matrix[k + i];
            }
        }
        boolean res;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res = hasPath(i, j, 0);
                if (res) return true;
            }
        }
        return false;
    }


    private boolean hasPath(int i, int j, int index) {
        if (index == str.length) return true;
        if (i < 0 || j < 0 || i >= rows || j >= cols
                || marked[i][j] || arrs[i][j] != str[index]) return false;
        marked[i][j] = true;
        boolean res =  hasPath(i + 1, j, index + 1)
                || hasPath(i, j + 1, index + 1)
                || hasPath(i - 1, j, index + 1)
                || hasPath(i, j - 1, index + 1);
        marked[i][j] = false;
        return res;

    }


}
