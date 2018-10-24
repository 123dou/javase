package leetcode.intermediate.dynamicPlanning;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class CanJump {
    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0};
        boolean b = canJump3(nums);
        System.out.println(b);
    }

    /**
     * 算法思路:
     * 1.从起点开始一条路径一条路径用试,不通再回溯,时间复杂度过高
     * 2.设Final为终点位置,curr为当前位置, nums数组值为当前位置可以走的步数
     * 3.可以从终点往前考虑: 令curr = nums.length - 2, Final = nums.length - 1;
     * 4.如果nums[curr] == 0 或着nums[curr] < Final - i,则忽略该位置,i--;
     * 5,如果i == 0,还不满足4,则没有怎么走都不可能才起点走到终点
     * 6.则如果nums[curr] >= Final - i,则可以到达curr的就一定可以到达最终位置,令Final = curr, curr = curr - 1; 继续往前找直到Final==0,即起始位置
     *
     * @param nums 位置数组
     * @return
     */
    public static boolean canJump3(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        return canJump(nums, nums.length - 2, nums.length - 1);
    }

    /**
     * @param nums
     * @param i
     * @param Final
     * @return
     */
    public static boolean canJump(int[] nums, int i, int Final) {
        if (Final == 0) return true; //终点为起始位置,反回true
        while (i > 0 && (nums[i] == 0 || nums[i] < Final - i)) i--; //忽略该位置
        if (i == 0) {
            if (nums[i] < Final - i) return false; //当起始位置仍然不能到达终点时反回false
            else return true; //否则返回true
        }
        return canJump(nums, i - 1, i);
    }

    /**
     * 令fastPos为从起点能走到的最远位置,记算能走到的最远位置是否大于最后一个元素即可
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int len = nums.length;
        int i, fastPos = 0; //能走到的最远位置
        for (i = 0; i < len; i++) {
            if (i > fastPos || fastPos >= len - 1) break;
            fastPos = Math.max(fastPos, nums[i] + i);
        }
        return fastPos >= len - 1;
    }


    /**
     * 直接用回溯法:时间复杂度过高
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        return canJump(nums, 0);
    }

    /**
     * 稍稍优化了回溯法:把走过不通的路径设个标置,下次不再走
     *
     * @param nums
     * @param i
     * @return
     */
    public static boolean canJump2(int[] nums, int i) {
        if (i < nums.length - 1 && nums[i] == 0) return false; //当前不是终点,且当前可以走的步数为0时,不可到达路径
        if (nums[i] >= nums.length - 1 - i) return true; //当前可以走的步数大于到达终点的步数时,可以到达终点
        int n = 1;
        while (n <= nums[i]) { //最多可以最n步
            if (!canJump(nums, i + n)) {
                nums[i + n] = 0;
                n++;
            } else return true;
        }
        return false;
    }


    /**
     * 直接回溯
     *
     * @param nums
     * @param i
     * @return
     */
    public static boolean canJump(int[] nums, int i) {
        if (i < nums.length - 1 && nums[i] == 0) return false; //当前不是终点,且当前可以走的步数为0时,不可到达路径
        if (nums[i] >= nums.length - 1 - i) return true; //当前可以走的步数大于到达终点的步数时,可以到达终点
        int n = 1;
        while (n <= nums[i]) { //最多可以最n步
            if (!canJump(nums, i + n)) n++;
            else return true;
        }
        return false;
    }
}
