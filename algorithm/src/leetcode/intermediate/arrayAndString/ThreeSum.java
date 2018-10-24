package leetcode.intermediate.arrayAndString;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> listList = threeSum4(nums);
        System.out.println(listList);

    }

    public List<List<Integer>> threeSum6(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 3) return lists;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            int low = i + 1, hi = nums.length - 1;
            while (low < hi) {
                if (nums[low] + nums[hi] > target) hi--;
                else if (nums[low] + nums[hi] < target) low++;
                else lists.add(Arrays.asList(nums[i], nums[low++], nums[hi--]));

                //去重
                while (low < hi && low > i + 1 && nums[low] == nums[low - 1]) low++;
                while (low < hi && hi < nums.length - 1 && nums[hi] == nums[hi + 1]) hi--;
            }
        }
        return lists;
    }


    /**
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum5(int[] nums) {
        List<List<Integer>> lists = new LinkedList<>();
        if (nums == null || nums.length == 0) return lists;
        int n0 = 0; //数组中零的数量
        int n1 = 0; //数组中正数的数量
        int n1_ = 0; //数组中负数的数量
        for (int i : nums) {
            if (i == 0) n0++;
            else if (i > 0) n1++;
            else n1_++;
        }
        if (n0 >= 3) {
            lists.add(Arrays.asList(0, 0, 0));
        }
        if (n1 == 0 || n1_ == 0) return lists;
        int[] pos = new int[n1];
        int[] neg = new int[n1_];
        for (int i = 0, len1 = 0, len1_ = 0; i < nums.length; i++) {
            if (nums[i] < 0) neg[len1_++] = nums[i];
            else if (nums[i] > 0) pos[len1++] = nums[i];
        }
        Arrays.sort(pos);
        Arrays.sort(neg);
        if (n0 > 0) {
            twoSum(neg, pos, lists);
        }
        for (int i = 0; i < neg.length; i++) {
            if (i > 0 && neg[i] == neg[i - 1]) continue;
            twoSum2(pos, lists, -neg[i]);
        }
        for (int i = 0; i < pos.length; i++) {
            if (i > 0 && pos[i] == pos[i - 1]) continue;
            twoSum2(neg, lists, -pos[i]);
        }
        return lists;
    }

    private void twoSum(int[] neg, int[] pos, List<List<Integer>> lists) {
        for (int i = 0; i < neg.length; i++) {
            if (i > 0 && neg[i] == neg[i - 1]) continue;
            int t = -neg[i];
            if (t < pos[0]) break;
            for (int j = 0; j < pos.length; j++) {
                if (j > 0 && pos[j] == pos[j - 1]) continue;
                if (pos[j] == t) {
                    lists.add(Arrays.asList(neg[i], pos[j], 0));
                    break;
                } else if (pos[j] > t) break;
            }
        }
    }

    /**
     * 返回所有与target相等的两数之和,不包括重复的元素
     *
     * @param nums
     * @param lists
     * @param target
     */
    private void twoSum2(int[] nums, List<List<Integer>> lists, int target) {
        int low = 0, hi = nums.length - 1;
        while (low < hi) {
            if (nums[low] + nums[hi] > target) hi--;
            else if (nums[low] + nums[hi] < target) low++;
            else {
                lists.add(Arrays.asList(nums[low], nums[hi], -target));
                low++;
                hi--;
            }
            while (low < nums.length && low > 0 && nums[low] == nums[low - 1]) low++;
            while (hi >= 0 && hi < nums.length - 1 && nums[hi] == nums[hi + 1]) hi--;
        }
    }


    /**
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum4(int[] nums) {
        if (nums == null) return Collections.emptyList();
        if (nums.length < 3) return Collections.emptyList();
        List<List<Integer>> listList = new LinkedList<>();
        int count_0 = 0;
        int count_r = 0;
        int count_l = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int v : nums) {
            if (v < min) min = v;
            if (v > max) max = v;
            if (v > 0) ++count_r;
            else if (v < 0) ++count_l;
            else ++count_0;
        }
        if (count_0 >= 3) listList.add(Arrays.asList(0, 0, 0));
        if (count_l == 0 || count_r == 0) return listList;
        if (max > ((-min) << 1)) max = ((0 - min) << 1);
        if (min < (0 - (max << 1))) min = (0 - (max << 1));

        int[] aid = new int[max - min + 1];
        int[] r = new int[count_r];
        int[] l = new int[count_l];
        count_l = 0;
        count_r = 0;
        for (int v : nums) {
            if (v <= max && v >= min) {
                if (aid[v - min]++ == 0) { //去重
                    if (v > 0) r[count_r++] = v;
                    else if (v < 0) l[count_l++] = v;
                }
            }
        }
        Arrays.sort(r, 0, count_r);
        Arrays.sort(l, 0, count_l);
        int temp = 0;
        for (int i = count_l - 1; i >= 0; i--) {
            int lV = l[i];
            int minV = (-lV) >>> 1;
            while (temp < count_r && r[temp] < minV) temp++;
            for (int j = temp; j < count_r; j++) {
                int rV = r[j];
                int another = 0 - lV - rV;
                if (another <= rV && another >= lV) {
                    if (another == lV && aid[lV - min] > 1) listList.add(Arrays.asList(lV, lV, rV));
                    else if (another == rV && aid[rV - min] > 1) listList.add(Arrays.asList(rV, rV, lV));
                    else if (another != rV && another != lV && aid[another - min] > 0)
                        listList.add(Arrays.asList(another, lV, rV));
                } else if (another < lV) break;
            }
        }
        return listList;
    }


    /**
     * 先将数组排序,这样才好去重
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> listList = new LinkedList<>();
        if (nums == null || nums.length < 3) return listList;
        if (nums.length == 3)
            if (nums[0] + nums[1] + nums[2] == 0) {
                listList.add(Arrays.asList(nums[0], nums[1], nums[2]));
                return listList;
            }
        Arrays.sort(nums);  //关键步骤
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j > (i + 1) && nums[j] == nums[j - 1]) continue;
                int another = 0 - (nums[i] + nums[j]);
                if (map.containsKey(another) && map.get(another) > j)
                    listList.add(Arrays.asList(nums[i], nums[j], another));
            }
        }
        return listList;
    }


    /**
     * 还是超出时间限制
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> listList = new LinkedList<>();
        if (nums == null) return listList;
        if (nums.length < 3) return listList;
        if (nums.length == 3)
            if (nums[0] + nums[1] + nums[2] == 0) {
                listList.add(Arrays.asList(nums[0], nums[1], nums[2]));
                return listList;
            }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (set1.contains(nums[i])) continue;
            set2.clear();
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (set2.contains(nums[j])) continue;
                int another = 0 - (nums[i] + nums[j]);
                if (map.containsKey(another) && map.get(another) > j) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(another);
                    listList.add(list);
                    set2.add(nums[j]);
                }
            }
            set1.add(nums[i]);
        }
        deleDuplicate(listList);
        return listList;
    }


    /**
     * 超出时间限制:
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> listList = new LinkedList<>();
        if (nums == null || nums.length < 3) return listList;
        if (nums.length == 3)
            if (nums[0] + nums[1] + nums[2] == 0) {
                listList.add(Arrays.asList(nums[0], nums[1], nums[2]));
                return listList;
            }
        int sum = 0;
        boolean flag = true;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] != 0) flag = false;
        }
        if (sum == 0 && flag) {
            listList.add(Arrays.asList(0, 0, 0));
            return listList;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int another = 0 - (nums[i] + nums[j]);
                if (map.containsKey(another) && map.get(another) > j) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(another);
                    listList.add(list);
                }
            }
        }
        deleDuplicate(listList);
        return listList;
    }

    private static void deleDuplicate(List<List<Integer>> listList) {
        List<Integer> list1;
        List<Integer> list2;
        for (int i = 0; i < listList.size() - 1; i++) {
            list1 = listList.get(i);
            for (int j = i + 1; j < listList.size(); j++) {
                list2 = listList.get(j);
                if (isDuplicate(list1, list2)) {
                    listList.remove(j);
                    --j;
                }
            }
        }
    }

    /**
     * 判断是否是集合里的三个元素是否完全重复
     *
     * @param list1
     * @param list2
     * @return
     */
    public static boolean isDuplicate(List<Integer> list1, List<Integer> list2) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(list2.get(i));
        }
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list1.get(i) == list.get(j)) {
                    list.remove(j);
                    break;
                }
            }
        }
        if (list.size() == 0) return true;
        return false;
    }
}
