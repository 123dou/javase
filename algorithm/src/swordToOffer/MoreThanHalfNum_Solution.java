package swordToOffer;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class MoreThanHalfNum_Solution {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        MoreThanHalfNum_Solution t = new MoreThanHalfNum_Solution();
        int i = t.MoreThanHalfNum_Solution2(array);
        System.out.println(i);
    }

    /**
     * @param nums
     * @return
     */
    public int MoreThanHalfNum_Solution(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int pre = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == pre) count++;
            else {
                count--;
                if (count == 0) {
                    pre = nums[i];
                    count = 1;
                }
            }
        }
        if (checkMoreThanHalf(nums, pre)) return pre;
        return 0;
    }

    /**
     * 先求出中位数,再检验中位数的数量是否过半
     *
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution2(int[] array) {
        if (array == null || array.length == 0) return 0;
        int mid = array.length >>> 1;
        int low = 0;
        int hi = array.length - 1;
        int pivot = partion(array, low, hi);
        while (pivot != mid) {
            if (pivot > mid) {
                hi = pivot - 1;
                pivot = partion(array, low, hi);
            } else {
                low = pivot + 1;
                pivot = partion(array, low, hi);
            }
        }
        if (checkMoreThanHalf(array, array[pivot])) {
            return array[pivot];
        }
        return 0;
    }

    /**
     * 常规分区
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    private int partion(int[] array, int left, int right) {
        int piovt = (int) (left + Math.random() * (right - left + 1));
        swap(array, piovt, right);
        int pre = left - 1;
        for (int i = left; i <= right; i++) {
            if (array[i] <= array[right]) {
                pre++;
                if (i > pre) swap(array, i, pre);
            }
        }
        return pre;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    private boolean checkMoreThanHalf(int[] nums, int target) {
        int mid = nums.length / 2;
        int count = 0;
        for (int i : nums) {
            if (i == target) count++;
        }
        return count > mid;
    }
}
