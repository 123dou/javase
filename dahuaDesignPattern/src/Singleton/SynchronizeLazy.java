package Singleton;
/*双重加锁的懒汉模式,线程安全,避免了延迟加载但还是有可能出现这里要提到Java中的指令重排优化。所谓指令重排优化是指在不改变原语义的情况下，
通过调整指令的执行顺序让程序运行的更快。
JVM中并没有规定编译器优化相关的内容，也就是说JVM可以自由的进行指令重排序的优化。
这个问题的关键就在于由于指令重排优化的存在，导致初始化Singleton和将对象地址赋给instance字段的顺序是不确定的。
在某个线程创建单例对象时，在构造方法被调用之前，就为该对象分配了内存空间并将对象的字段设置为默认值。此时就可以将分配的内存地址赋值给instance字段了，
然而该对象可能还没有初始化。若紧接着另外一个线程来调用newInstance，取到的就是状态不正确的对象，程序就会出错
*/public class SynchronizeLazy {
    //private static SynchronizeLazy scnLazy; 
    private static volatile SynchronizeLazy scnLazy; // 
    
    private SynchronizeLazy() {
    	
    }
    public static SynchronizeLazy newInstance() {
    	if(scnLazy == null) {
    		synchronized(SynchronizeLazy.class) {
    			if(scnLazy == null)
    				scnLazy = new SynchronizeLazy();
    		}
    	}
    	return scnLazy;
    }
}
