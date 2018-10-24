package swordToOffer;

import java.util.ArrayList;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class GetLeastNumbers_Solution {
    public static void main(String[] args) {
        int[] input = {4, 5, 1, 6, 2, 7, 3, 8};
        GetLeastNumbers_Solution gs = new GetLeastNumbers_Solution();
        ArrayList<Integer> list = gs.GetLeastNumbers_Solution2(input, 4);
        System.out.println(list);
    }

    /**
     * 基于快排的partion
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution2(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k <= 0) return list;
        if (k > input.length) throw new IllegalArgumentException();
        k--;
        int low = 0, hi = input.length - 1;
        int pivot = partion(input, low, hi);
        while (pivot != k) {
            if (pivot < k) {
                low = pivot + 1;
                pivot = partion(input, low, hi);
            } else {
                hi = pivot - 1;
                pivot = partion(input, low, hi);
            }
        }
        for (int i = 0; i <= pivot; i++) list.add(input[i]);
        return list;
    }


    private int partion(int[] input, int low, int hi) {
        int pivot = (int) (low + Math.random() * (hi - low + 1));
        swap(input, pivot, hi);
        int pre = low - 1;
        for (int i = low; i <= hi; i++) {
            if (input[i] <= input[hi]) {
                ++pre;
                if (i > pre) swap(input, pre, i);
            }
        }
        return pre;
    }


    /**
     * 基于最大堆的
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k == 0) return list;
        int[] maxHeap = new int[k];
        initMaxHead(maxHeap, input, k);
        for (int i = k; i < input.length; i++) {
            if (input[i] < maxHeap[0]) {
                maxHeap[0] = input[i];
                sink(maxHeap, 0);
            }
        }
        for (int n : maxHeap) list.add(n);
        return list;
    }

    private void initMaxHead(int[] maxHeap, int[] input, int k) {
        for (int i = 0; i < k; i++) {
            maxHeap[i] = input[i];
            swim(maxHeap, i);
        }
    }

    private void swim(int[] maxHeap, int curr) {
        int parent = curr / 2;
        while (curr > 0 && maxHeap[curr] > maxHeap[parent]) {
            swap(maxHeap, curr, parent);
            curr = parent;
            parent /= 2;
        }
    }

    private void sink(int[] maxHeap, int curr) {
        while (curr * 2 + 1 < maxHeap.length) {
            int t = curr * 2 + 1;
            if (t + 1 < maxHeap.length && maxHeap[t + 1] > maxHeap[t]) t++;
            if (maxHeap[curr] > maxHeap[t]) break;
            swap(maxHeap, curr, t);
            curr = t;
        }
    }


    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
