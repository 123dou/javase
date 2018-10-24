package swordToOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class MedianOfStream {

    public static void main(String[] args) {
        MedianOfStream t = new MedianOfStream();
        t.Insert(5);
        System.out.println(t.GetMedian());
        t.Insert(2);
        System.out.println(t.GetMedian());
        t.Insert(3);
        System.out.println(t.GetMedian());
        t.Insert(4);
        System.out.println(t.GetMedian());
        t.Insert(1);
        System.out.println(t.GetMedian());
        t.Insert(6);
        System.out.println(t.GetMedian());
        t.Insert(7);
        System.out.println(t.GetMedian());
        t.Insert(0);
        System.out.println(t.GetMedian());
        t.Insert(8);
        System.out.println(t.GetMedian());
    }

    private List<Integer> list = new ArrayList<>();

    public void Insert(Integer num) {
        int index = find(list, num);
        list.add(index, num);
    }

    public Double GetMedian() {
        if (list.size() == 1) return Double.valueOf(list.get(0));
        int mid = (list.size() - 1) / 2;
        if (((list.size() - 1) & 1) == 0) return Double.valueOf(list.get(mid));
        double l = list.get(mid);
        double r = list.get(mid + 1);
        return Double.valueOf((l + r) / 2);
    }

    private int find(List<Integer> list, int target) {
        int low = 0, hi = list.size() - 1, mid;
        while (low < hi) {
            mid = (low + hi) >>> 1;
            if (target <= list.get(mid)) hi = mid;
            else low = mid + 1;
        }
        return low;
    }
}
