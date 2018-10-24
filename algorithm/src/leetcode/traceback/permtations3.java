package leetcode.traceback;

import java.util.ArrayList;
import java.util.List;

public class permtations3 {
    public static void main(String[] args) {
        permtations3 p = new permtations3();
        int[] nums = {1, 1, 2, 2};
        List<List<Integer>> listList = p.permuteUnique(nums);
        System.out.println(listList);
    }

    public List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {

        if (nums.length == 0) return result;
        getList(nums, 0);
        return result;
    }

    private void getList(int[] nums, int idx) {

        if (idx == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (Integer integer : nums) {
                list.add(integer);
            }
            result.add(list);
            return;
        }
        int temp = 0;
        for (int i = idx; i < nums.length; i++) {
            if (!isSame(nums, idx, i)) {
                temp = nums[idx];
                nums[idx] = nums[i];
                nums[i] = temp;
                getList(nums, idx + 1);
                //递归回溯之后，需要把状态全部还原回来。
                temp = nums[idx];
                nums[idx] = nums[i];
                nums[i] = temp;
            }
        }
    }

    //在 [start end) 这个区间内有相同值，那就是重复排列。
    private boolean isSame(int[] nums, int start, int end) {
        for (int i = start; i < end; i++) {
            if (nums[i] == nums[end])
                return true;
        }
        return false;
    }
}
