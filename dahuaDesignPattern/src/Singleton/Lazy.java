package Singleton;

public class Lazy {
    private static Lazy lazy;
    private Lazy() {
    	
    }
    //省内存,但是线程不安全
    public static Lazy newInstance() {
    	if(lazy == null) 
    		lazy = new Lazy();
    	return lazy;
    }
}
