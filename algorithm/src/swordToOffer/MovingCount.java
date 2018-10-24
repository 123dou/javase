package swordToOffer;

/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），
 * 因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class MovingCount {
    public static void main(String[] args) {

    }

    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0) return 0;
        boolean[][] marked = new boolean[rows][cols];
        movingCount(threshold, 0, 0, marked);
        return count;
    }

    private int count = 0;

    private void movingCount(int k, int r, int c, boolean[][] marked) {
        if (r < 0 || r >= marked.length || c < 0 || c >= marked[0].length || marked[r][c])
            return;
        int sum = 0;
        int t = r;
        while (t > 0) {
            sum += t % 10;
            t /= 10;
        }
        t = c;
        while (t > 0) {
            sum += t % 10;
            t /= 10;
        }
        if (sum > k) return;
        count++;
        marked[r][c] = true;
        movingCount(k, r + 1, c, marked);
        movingCount(k, r - 1, c, marked);
        movingCount(k, r, c + 1, marked);
        movingCount(k, r, c - 1, marked);
    }
}
