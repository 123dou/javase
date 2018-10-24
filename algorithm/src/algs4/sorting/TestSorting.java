package algs4.sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class TestSorting {
    private static int[] array = new int[100000];

    static {
        Random random = new Random(10000);
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(10000000);
    }

    //测试冒泡法
    @Test
    public void testBubbleSorting() {
        double start = (double) System.currentTimeMillis();
        Bubble.bubbleSorting(array);
        double end = (double) System.currentTimeMillis();
        double duration = end - start;
        System.out.println("冒泡所用毫秒时: " + duration);
        System.out.println(SortingUtil.isSorted(array));
        System.out.println(Arrays.toString(array));

    }

    //测试选择法
    @Test
    public void testSelectionSortiong() {
        double start = (double) System.currentTimeMillis();
        SelectionSorting.selectionSorting(array);
        double end = (double) System.currentTimeMillis();
        double duration = end - start;
        System.out.println("选择法所用毫秒时: " + duration);
        System.out.println(SortingUtil.isSorted(array));
        //System.out.println(Arrays.toString(array));

    }

    //测试插入法
    @Test
    public void testInsertionSortiong() {
        double start = (double) System.currentTimeMillis();
        InsertionSorting.insertionSorting(array);
        double end = (double) System.currentTimeMillis();
        double duration = end - start;
        System.out.println("插入法所用毫秒时: " + duration);
        System.out.println(SortingUtil.isSorted(array));
        //System.out.println(Arrays.toString(array));

    }

    //测试希尔法
    @Test
    public void testShellSorting() {
        double start = (double) System.currentTimeMillis();
        ShellSorting.shellSorting(array);
        double end = (double) System.currentTimeMillis();
        double duration = end - start;
        System.out.println("希尔法所用毫秒时: " + duration);
        System.out.println(SortingUtil.isSorted(array));
        //System.out.println(Arrays.toString(array));

    }

    //测试归并法
    @Test
    public void testMergeSorting() {
        double start = (double) System.currentTimeMillis();
        array = MergeSorting.mergeSorting(array);
        double end = (double) System.currentTimeMillis();
        double duration = end - start;
        System.out.println("归并法所用毫秒时: " + duration);
        System.out.println(SortingUtil.isSorted(array));
        //System.out.println(Arrays.toString(array));
    }

    //测试快速法
    @Test
    public void testQuickSorting() {
        double start = (double) System.currentTimeMillis();
        QuickSorting.quickSorting(array, 0, array.length - 1);
        double end = (double) System.currentTimeMillis();
        double duration = end - start;
        System.out.println("快速法所用毫秒时: " + duration);
        System.out.println(SortingUtil.isSorted(array));
        //System.out.println(Arrays.toString(array));

    }

    //计数法
    @Test
    public void testCounteSorting() {
        double start = (double) System.currentTimeMillis();
        array = CounteSorting.counteSorting(array);
        double end = (double) System.currentTimeMillis();
        double duration = end - start;
        System.out.println("计数法所用毫秒时: " + duration);
        System.out.println(SortingUtil.isSorted(array));
        //System.out.println(Arrays.toString(array));

    }

    //桶计数法
    @Test
    public void testBucketSorting() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) list.add(array[i]);
        double start = (double) System.currentTimeMillis();
        list = BucketSorting.bucketSorting(list, 100);
        double end = (double) System.currentTimeMillis();
        double duration = end - start;
        for (int i = 0; i < list.size(); i++) array[i] = list.get(i);
        System.out.println(SortingUtil.isSorted(array));
        System.out.println("桶计数法所用毫秒时: " + duration);
        //System.out.println(Arrays.toString(array));
    }

    //测试堆排序法
    @Test
    public void testHeadSorting() {
        double start = (double) System.currentTimeMillis();
        HeapSorting.headSorting(array);
        double end = (double) System.currentTimeMillis();
        double duration = end - start;
        System.out.println("堆排序所用有毫秒时: " + duration);
        System.out.println(SortingUtil.isSorted(array));
        //System.out.println(Arrays.toString(array));
    }

    //测试基数法
    @Test
    public void testSorting() {
        double start = (double) System.currentTimeMillis();
        LSDSort.lsdSort(array);
        double end = (double) System.currentTimeMillis();
        double duration = end - start;
        System.out.println("基排序所用有毫秒时: " + duration);
        System.out.println(SortingUtil.isSorted(array));
        //System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {

    }
}
