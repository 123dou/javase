package leetcode.primary.sortAndSearch;

public class BinarySearch {
    public static void main(String[] args) {
        boolean[] bools = {false, !true, !true, !true, true, true};
        int i = firstTrue(bools);
        System.out.println(i);
    }

    public static int firstTrue(boolean[] bools) {
        int low = 0;
        int hi = bools.length - 1;
        int mid;
        while (low < hi) {
            mid = low + (hi - low) / 2;
            if (bools[mid]) hi = mid;
            else low = mid + 1;
        }
        return hi;
    }
}
