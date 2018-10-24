package leetcode.primary.array;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 给定数组 nums = [1,1,2],
 * <p>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0, 0, 0, 1, 2, 3, 3};
        int i = removeDuplicate2(nums);
        System.out.println(i);
    }

    /**
     * 关键是不用不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @return
     */
    public static int removeDuplicate2(int[] nums) {
        int length = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[length] != nums[i])
                nums[++length] = nums[i];
        }
        return ++length;
    }


    /**
     * 脑残做法
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null)
            return -1;
        if (nums.length == 1)
            return 1;
        int length = 0; //插入元素的位置
        int j = length + 1;
        while (j < nums.length) {
            while (j < nums.length && nums[length] >= nums[j]) {
                j++;
            }
            if (j < nums.length)
                swap(nums, ++length, j++);
            else {
                swap(nums, ++length, --j);
                break;
            }
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] <= nums[i]) {
                length = i;
                break;
            }
        }
        return ++length;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
