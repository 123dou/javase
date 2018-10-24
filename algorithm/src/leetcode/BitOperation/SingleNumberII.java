package leetcode.BitOperation;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 */
public class SingleNumberII {
    public static void main(String[] args) {

    }

    /**
     * 将所有的数看成二进制数,依次求32位数的每一位,32位数的每一位依次设为bit0,bit1,.....bit31,数组里面
     * 所有数的bit_i的数量和不为3的倍数的就是结果所求的数那一位
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        int setOne = 1;
        for (int i = 0; i < 32; i++) {
            int count1 = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & 1) == 1) count1++;
                nums[j] >>>= 1;
            }
            if (count1 % 3 != 0) res |= setOne;
            setOne <<= 1;
        }
        return res;
    }
}
