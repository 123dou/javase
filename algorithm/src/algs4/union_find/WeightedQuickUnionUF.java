package algs4.union_find;

public class WeightedQuickUnionUF extends Quick_union {
    private int[] sz;

    public WeightedQuickUnionUF(int N) {
        super(N);
        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 1;
    }

    /**
     * union时:总是将小的树链接到大的树
     *
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j; //将i变量链接到j变量
            sz[j] += sz[i];
        } else {
            id[j] = i; //将j变量链接到i变量
            sz[i] += sz[j];
        }
        count--;
    }
}
