package swordToOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
 * 他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
 * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class MaxInWindows {


    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        LinkedList<Integer> q = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || num.length == 0 || size == 0) return list;
        if (size == 1) {
            for (int i : num) list.add(i);
            return list;
        }
        for (int i = 0; i < size - 1; i++) {
            q.add(-num[i]);
            pq.add(-num[i]);
        }
        for (int i = size - 1; i < num.length; i++) {
            pq.add(-num[i]);
            q.add(-num[i]);
            list.add(-pq.peek());
            pq.remove(q.poll());
        }
        return list;
    }

}
