package testDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * <p>
 * 找到所有出现两次的元素。
 * <p>
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * <p>
 * 示例：
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [2,3]
 */
public class FindAllDuplicatesInAnArray {
    public static void main(String[] args) {

    }

    /**
     * 解题的关键是:
     * 其中1 ≤ a[i] ≤ n （n为数组长度）
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length <= 1) return res;
        for (int i = 0; i < nums.length; i++) {
            int indx = Math.abs(nums[i]) - 1;
            if (nums[indx] < 0) {
                res.add(Math.abs(nums[i]));
            }
            nums[indx] = -nums[indx];
        }
        return res;
    }
}
