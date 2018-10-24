package algs4.sorting;

public class SortingUtil {

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static boolean isNullOrOneElement(int[] array) {
        if (array == null)
            return true;
        else if (array.length == 1)
            return true;
        return false;
    }

    public static boolean isSorted(int[] array) {
        if (array == null)
            return false;
        if (array.length == 1)
            return true;
        for (int i = 0; i < array.length - 1; i++)
            if (array[i] > array[i + 1]) return false;
        return true;
    }
}
