package leetcode.intermediate.arrayAndString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 */
public class FourSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        FourSum t = new FourSum();
    }


    public List<List<Integer>> FourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 4) return lists;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int t = target - nums[i] - nums[j];
                int low = j + 1, hi = nums.length - 1;
                while (low < hi) {
                    if (nums[low] + nums[hi] > t) hi--;
                    else if (nums[low] + nums[hi] < t) low++;
                    else {
                        lists.add(Arrays.asList(nums[i], nums[j], nums[low++], nums[hi--]));
                    }

                    while (low < hi && low > j + 1 && nums[low] == nums[low - 1]) low++;
                    while (low < hi && hi < nums.length - 1 && nums[hi] == nums[hi + 1]) hi--;
                }
            }
        }
        return lists;
    }
}
