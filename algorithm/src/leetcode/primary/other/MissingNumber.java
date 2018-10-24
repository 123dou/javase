package leetcode.primary.other;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 * 说明:
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 */
public class MissingNumber {
    public static void main(String[] args) {
        int[] nums = {0};
        int i = missingNumber(nums);
        System.out.println(i);
    }

    public int missingNumber2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int sum = (n * (n + 1)) / 2;
        for (int i : nums) sum -= i;
        return sum;
    }


    public static int missingNumber(int[] nums) {
        int[] aid = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            aid[nums[i]]++;
        }
        int result = 0;
        for (int i = 0; i < aid.length; i++) {
            if (aid[i] == 0) result = i;
        }
        return result;
    }

}
