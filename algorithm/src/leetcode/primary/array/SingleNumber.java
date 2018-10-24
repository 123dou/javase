package leetcode.primary.array;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 3, 4, 3, 5, 4, 5};
        int i = singleNumber(nums);
        System.out.println(i);
    }

    /**
     * 利用一个数异或本身为0,和0异或任何整数都为其本身
     *
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            number ^= nums[i];
        }
        return number;
    }


    /**
     * 暴力法
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int result = -1;
        for (int i = 0; i < nums.length; i++) {
            boolean flag = false;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] == nums[j])
                    flag = true;
            }
            if (!flag)
                result = nums[i];
        }

        return result;
    }
}
