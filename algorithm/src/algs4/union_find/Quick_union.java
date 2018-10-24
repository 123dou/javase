package algs4.union_find;

public class Quick_union extends UF {
    public Quick_union(int N) {
        super(N);
    }

    /**
     * id[p] 的值指向他的父结点
     * 根结点的值指向他自己
     *
     * @param p
     * @return
     */
    @Override
    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    /**
     * 合并:只需要将一个根结点的父结点设为另一个根结点
     *
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (qRoot != pRoot) {
            id[pRoot] = id[qRoot];
            count--;
        }
    }
}
