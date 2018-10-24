package niukewang.DynamicPlanning;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/d83721575bd4418eae76c916483493de
 * 来源：牛客网
 * <p>
 * 广场上站着一支队伍，她们是来自全国各地的扭秧歌代表队，现在有她们的身高数据，请你帮忙找出身高依次递增的子序列。
 * 例如队伍的身高数据是（1、7、3、5、9、4、8），其中依次递增的子序列有（1、7），（1、3、5、9），（1、3、4、8）等，其中最长的长度为 4。
 * 输入描述:
 * 输入包含多组数据，每组数据第一行包含一个正整数n（1≤n≤1000）。
 * <p>
 * 紧接着第二行包含n个正整数m（1≤n≤10000），代表队伍中每位队员的身高。
 * <p>
 * <p>
 * 输出描述:
 * 对应每一组数据，输出最长递增子序列的长度。
 * 示例 1
 * 输入
 * 7
 * 1 7 3 5 9 4 8
 * 6
 * 1 3 5 2 4 6
 * 输出
 * 4
 * 4
 */
public class LIS {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = in.nextInt();
            }
            int result = solution(n, array);
            System.out.println(result);
        }
    }

    /**
     * 动态规划的解法:假定以array[i]结尾的的数组的最长子序列长度为F(i),那么F(i) = {max{L(j)} + 1, j < i, array[i] < array[j]}
     * 然后遍历所有的F(i),找出最长子序列
     *
     * @param n
     * @param array
     * @return
     */
    public static int solution(int n, int[] array) {
        if (array == null || n <= 0 || array.length != n) {
            return 0;
        }
        int[] result = new int[n]; // result[i]表示以array[i]结尾的数组的最大子序列长度
        result[0] = 1; //初始化第一个数,result[i]中每个数的最小值都不小于1
        int max = 1;
        for (int i = 1; i < n; i++) {
            result[i] = 1; //result[i]的初始值为1
            for (int j = i - 1; j >= 0; j--) { //F(i) = {max{L(i)} + 1, j < i, array[j] < array[i]}
                if (array[i] > array[j])
                    result[i] = Math.max(result[i], result[j] + 1);
            }
            if (max < result[i])
                max = result[i];
        }
        return max;
    }

    /**
     * 时间复杂度为O(nlogn)的算法:
     * 定义 d[k]: 长度为 k 的上升子序列的最末元素，若有多个长度为 k 的上升子序列，则记录最小的那个最末元素。
     * 注意 d 中元素是单调递增的，下面要用到这个性质。
     * 首先 len = 1,d[1] = a[1], 然后对 a[i]: 若 a[i]>d[len]，那么 len++，d[len] = a[i];
     * 否则，我们要从 d[1] 到 d[len-1] 中找到一个 j，满足 d[j-1]<a[i]<d[j],
     * 则根据 D 的定义，我们需要更新长度为 j 的上升子序列的最末元素（使之为最小的）即 d[j] = a[i];
     * 最终答案就是 len
     * 利用 d 的单调性，在查找 j 的时候可以二分查找，从而时间复杂度为 nlogn。
     * <p>
     * 假设存在一个序列 d[1..9] = 2 1 5 3 6 4 8 9 7，可以看出来它的 LIS 长度为 5。
     * 下面一步一步试着找出它。
     * 我们定义一个序列 B，然后令 i = 1 to 9 逐个考察这个序列。
     * 此外，我们用一个变量 Len 来记录现在最长算到多少了
     * <p>
     * <p>
     * 首先，把 d[1] 有序地放到 B 里，令 B[1] = 2，就是说当只有 1 一个数字 2 的时候，长度为 1 的 LIS 的最小末尾是 2。这时 Len=1
     * <p>
     * 然后，把 d[2] 有序地放到 B 里，令 B[1] = 1，就是说长度为 1 的 LIS 的最小末尾是 1，d[1]=2 已经没用了，很容易理解吧。这时 Len=1
     * <p>
     * 接着，d[3] = 5，d[3]>B[1]，所以令 B[1+1]=B[2]=d[3]=5，就是说长度为 2 的 LIS 的最小末尾是 5，很容易理解吧。这时候 B[1..2] = 1, 5，Len＝2
     * <p>
     * 再来，d[4] = 3，它正好加在 1,5 之间，放在 1 的位置显然不合适，因为 1 小于 3，长度为 1 的 LIS 最小末尾应该是 1，这样很容易推知，
     * 长度为 2 的 LIS 最小末尾是 3，于是可以把 5 淘汰掉，这时候 B[1..2] = 1, 3，Len = 2
     * <p>
     * 继续，d[5] = 6，它在 3 后面，因为 B[2] = 3, 而 6 在 3 后面，于是很容易可以推知 B[3] = 6, 这时 B[1..3] = 1, 3, 6，还是很容易理解吧？ Len = 3 了噢。
     * <p>
     * 第 6 个, d[6] = 4，你看它在 3 和 6 之间，于是我们就可以把 6 替换掉，得到 B[3] = 4。B[1..3] = 1, 3, 4， Len 继续等于 3
     * <p>
     * 第 7 个, d[7] = 8，它很大，比 4 大，嗯。于是 B[4] = 8。Len 变成 4 了
     * <p>
     * 第 8 个, d[8] = 9，得到 B[5] = 9，嗯。Len 继续增大，到 5 了。
     * <p>
     * 最后一个, d[9] = 7，它在 B[3] = 4 和 B[4] = 8 之间，所以我们知道，最新的 B[4] =7，B[1..5] = 1, 3, 4, 7, 9，Len = 5。
     * <p>
     * 于是我们知道了 LIS 的长度为 5。
     * <p>
     * 注意！！！这个 1,3,4,7,9 不是 LIS，它只是存储的对应长度 LIS 的最小末尾。有了这个末尾，我们就可以一个一个地插入数据。虽然最后一个 d[9] = 7 更新进去对于这组数据没有什么意义，但是如果后面再出现两个数字 8 和 9，那么就可以把 8 更新到 d[5], 9 更新到 d[6]，得出 LIS 的长度为 6。
     * 然后应该发现一件事情了：在 B 中插入数据是有序的，而且是进行替换而不需要挪动——也就是说，我们可以使用二分查找，将每一个数字的插入时间优化到 O(logN)，于是算法的时间复杂度就降低到了 O(NlogN)！
     *
     * @param n
     * @param array
     * @return
     */
    public static int solution2(int n, int[] array) {
        if (array == null || n <= 0 || array.length != n) {
            return 0;
        }
        int[] series = new int[n]; //
        series[0] = array[0];
        int length = 0;
        for (int i = 1; i < n; i++) {
            if (array[i] > series[length]) {
                series[++length] = array[i];
            } else {
                int index = getReplaceIndex(series, length - 1, array[i]);
                series[index] = array[i];
            }
        }
        return length + 1;
    }

    /**
     * 二分查找第一个不小于目标值的索引
     *
     * @param series
     * @param high
     * @param target
     * @return
     */
    private static int getReplaceIndex(int[] series, int high, int target) {
        int mid = 0;
        int low = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (target <= series[mid]) { //如果当前小于等于中值,则在下半部分查找
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}
