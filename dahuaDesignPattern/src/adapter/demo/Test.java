package adapter.demo;

public class Test {
    public static void main(String[] args) {
    	//通过适配器客户端调用的Target.request(),但实际上调用的是Adaptee.specificRequest();
		Target target = new Adapter();
		target.request();
	}
}
