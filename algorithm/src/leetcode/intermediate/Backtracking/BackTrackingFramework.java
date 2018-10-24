package leetcode.intermediate.Backtracking;

public class BackTrackingFramework<T> {
    boolean finished = false; /* 是否获得全部解? */

    void backtrack(int a[], int k, T input) { //k表示搜索深度；
        int[] c = null; /*这次搜索的候选MAXCANDIDATES */
        int ncandidates = 0; /* 候选数目 */
        int i; /* counter */
        if (is_a_solution(a, k, input))
            process_solution(a, k, input);
        else {
            k = k + 1;
            construct_candidates(a, k, input, c, ncandidates);
            for (i = 0; i < ncandidates; i++) {
                a[k] = c[i];
                make_move(a, k, input);
                backtrack(a, k, input);
                unmake_move(a, k, input);
                if (finished) return; /* 如果符合终止条件就提前退出 */
            }
        }
    }

    /**
     * 将采取的选择更新到原始数据结构上
     *
     * @param a
     * @param k
     * @param input
     */
    private void unmake_move(int[] a, int k, T input) {

    }

    /**
     * 把这一行为撤销。
     *
     * @param a
     * @param k
     * @param input
     */
    private void make_move(int[] a, int k, T input) {

    }

    /**
     * 根据目前状态，构造这一步可能的选择，存入 c[] 数组，其长度存入 ncandidates
     *
     * @param a
     * @param k
     * @param input
     * @param c
     * @param p4
     * @return
     */
    private Object construct_candidates(int[] a, int k, T input, int[] c, Object p4) {
        return null;
    }

    /**
     * 对于符合条件的解进行处理，通常是输出、计数等
     *
     * @param a
     * @param k
     * @param input
     */
    private void process_solution(int[] a, int k, T input) {

    }

    /**
     * 判断这是不是问题的一个解
     *
     * @param a
     * @param k
     * @param input
     * @return
     */
    private boolean is_a_solution(int[] a, int k, T input) {

        return false;
    }
}
