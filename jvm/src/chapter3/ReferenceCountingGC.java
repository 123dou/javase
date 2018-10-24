package chapter3;

public class ReferenceCountingGC {
    public Object instance = null;
    //以下两个成员属性只是占点空间,以便能在GC日志中看清楚是否被回收过
    private static final int _1M = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1M];

    public static void main(String[] args) {
        ReferenceCountingGC obj1 = new ReferenceCountingGC();
        ReferenceCountingGC obj2 = new ReferenceCountingGC();
        obj1.instance = obj2;
        obj2.instance = obj1;
        System.gc();
    }
}
