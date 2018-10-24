package Singleton;

public class Hungry {
	//随这类的加载而创建对像,耗内存,线程安全
    private static Hungry hungry = new Hungry();
    private Hungry() {
    	
    }
    public static Hungry newInstance() {
    	return hungry;
    }
}
