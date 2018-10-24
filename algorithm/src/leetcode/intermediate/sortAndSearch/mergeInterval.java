package leetcode.intermediate.sortAndSearch;

import java.util.LinkedList;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class mergeInterval {
    public static void main(String[] args) {
        List<Interval> list = new LinkedList<>();
        list.add(new Interval(2, 3));
        list.add(new Interval(0, 1));
        list.add(new Interval(1, 2));
        list.add(new Interval(3, 4));
        list.add(new Interval(4, 5));
        list.add(new Interval(1, 1));
        list.add(new Interval(0, 1));
        list.add(new Interval(4, 6));
        list.add(new Interval(5, 7));
        list.add(new Interval(1, 1));
        list.add(new Interval(3, 5));
        merge(list);
    }

    /**
     * 1.先根据interval的start给interval快速排序
     * 2.合并
     *
     * @param intervals
     * @return
     */
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0 || intervals.size() == 1) return intervals;
        Interval[] itls = new Interval[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            itls[i] = intervals.get(i);
        }
        quickSort(itls, 0, itls.length - 1);
        List<Interval> list = new LinkedList<>();
        for (int i = 0; i < itls.length - 1; i++) {
            if (itls[i].end >= itls[i + 1].start) {
                itls[i + 1].start = itls[i].start;
                itls[i + 1].end = Math.max(itls[i].end, itls[i + 1].end);
            } else {
                list.add(itls[i]);
            }
        }
        list.add(itls[itls.length - 1]);
        return list;
    }

    /**
     * 根据interval的start给interval快速排序
     *
     * @param intervals
     * @param low
     * @param high
     */
    public static void quickSort(Interval[] intervals, int low, int high) {
        if (low >= high) return;
        int pivot = doPartion(intervals, low, high);
        if (low < pivot) quickSort(intervals, low, pivot - 1);
        if (high > pivot) quickSort(intervals, pivot + 1, high);
    }

    /**
     * @param intervals
     * @param low
     * @param high
     * @return
     */
    private static int doPartion(Interval[] intervals, int low, int high) {
        int pivot = (int) (low + Math.random() * (high - low + 1));
        swap(intervals, pivot, high);
        int pre = low - 1;
        for (int i = low; i <= high; i++) {
            if (intervals[i].start <= intervals[high].start) {
                pre++;
                if (i > pre) swap(intervals, pre, i);
            }
        }
        return pre;
    }

    private static void swap(Interval[] intervals, int i, int j) {
        Interval temp = intervals[i];
        intervals[i] = intervals[j];
        intervals[j] = temp;
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
