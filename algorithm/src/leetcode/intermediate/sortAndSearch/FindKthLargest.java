package leetcode.intermediate.sortAndSearch;

import java.util.Arrays;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class FindKthLargest {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        int result = findKthLargest(nums, k);
        System.out.println(result);
    }

    /**
     * 1.建立一个大小为K的最小堆
     * 2.用nums数组的前k个元素去初始话最小堆
     * 3.从nums数组的第K+1个元素去遍历
     * 4.当遍历到的元素大于堆的根结点时,用其替换根结点,调整堆
     * 5.遍历完成,跟结点即为结果
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        int[] minHead = Arrays.copyOfRange(nums, 0, k);
        initialMinHeap(minHead);
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHead[0]) {
                minHead[0] = nums[i];
                adjustMinHead(minHead, 0);
            }
        }
        return minHead[0];
    }

    /**
     * 初始化最小堆,向上堆化
     *
     * @param heap
     */
    public static void initialMinHeap(int[] heap) {
        for (int i = 0; i < heap.length; i++) {
            int curr = i;
            int parent = (curr - 1) / 2;
            while (parent >= 0 && curr > parent) {
                if (heap[curr] < heap[parent]) {
                    swap(heap, curr, parent);
                    curr = parent;
                    parent = (curr - 1) / 2;
                } else break;
            }
        }
    }

    /**
     * 调整堆,向下堆化元素
     *
     * @param heap
     * @param root
     */
    public static void adjustMinHead(int[] heap, int root) {
        int leftChild = 0;
        int rightChild = 0;
        int min = root;
        while (leftChild < heap.length || rightChild < heap.length) {
            leftChild = 2 * root + 1;
            rightChild = 2 * root + 2;
            if (leftChild < heap.length && heap[leftChild] < heap[root]) min = leftChild;
            if (rightChild < heap.length && heap[rightChild] < heap[min]) min = rightChild;
            if (root < min) {
                swap(heap, root, min);
                root = min;
            } else break;
        }
    }

    /**
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
