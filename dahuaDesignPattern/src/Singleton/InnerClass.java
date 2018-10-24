package Singleton;
/**
 * 通过内部类来创建对像,线程安全,延迟小
 * @author dou
 *
 */
public class InnerClass {
    private InnerClass() {
    	
    }
    public static InnerClass newInstance() {
    	return newInnerClass.innerCalss;
    }
    private static class newInnerClass {
    	public static InnerClass innerCalss = new InnerClass(); 
    }
}
