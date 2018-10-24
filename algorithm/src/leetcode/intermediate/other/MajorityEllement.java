package leetcode.intermediate.other;

import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class MajorityEllement {
    public static void main(String[] args) {
        int[] nums = {3};
        int i = majorityElement(nums);
        System.out.println(i);
    }

    /**
     * 根据众数的数量>nums.length / 2
     *
     * @param nums
     * @return
     */
    public static int majorityElement2(int[] nums) {
        int result = nums[0];
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                result = num;
                count++;
            } else {
                if (result == num) count++;
                else count--;
            }
        }
        return result;
    }


    /**
     * 暴力法
     * 用一个map集合来保存之前遍历过的数,值为之前的数的个数
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                int count = map.get(num);
                if (++count > nums.length / 2) return num;
                map.put(num, count);
            } else map.put(num, 1);
        }
        return -1;
    }
}
