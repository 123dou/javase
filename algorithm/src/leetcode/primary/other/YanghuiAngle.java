package leetcode.primary.other;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * <p>
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class YanghuiAngle {
    public static void main(String[] args) {
        int numRows = 1;
        List<List<Integer>> generate = generate(numRows);
        System.out.println(generate);
    }

    public static List<List<Integer>> generate(int numRows) {
        int[][] YanghuiAngle = new int[numRows][];
        for (int i = 1; i <= numRows; i++) {
            YanghuiAngle[i - 1] = new int[i];
            YanghuiAngle[i - 1][i - 1] = 1;
        }
        for (int i = 0; i < numRows; i++) {
            YanghuiAngle[i][0] = 1;
        }
        List<List<Integer>> listList = new LinkedList<>();
        if (numRows > 2) {
            for (int i = 2; i < numRows; i++) {
                for (int j = 1; j < YanghuiAngle[i].length - 1; j++) {
                    YanghuiAngle[i][j] = YanghuiAngle[i - 1][j] + YanghuiAngle[i - 1][j - 1];
                }
            }
        }
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new LinkedList<>();
            for (int j = 0; j < YanghuiAngle[i].length; j++) {
                list.add(YanghuiAngle[i][j]);
            }
            listList.add(list);
        }
        return listList;
    }

}
