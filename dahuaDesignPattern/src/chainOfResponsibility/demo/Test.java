package chainOfResponsibility.demo;

public class Test {
    public static void main(String[] args) {
		Handler handler = new ConcreteHandler();
		Handler handler1 = new ConcreteHandler1();
		Handler handler2 = new ConcreteHandler2();
		//这里可以灵活的设置职责链的上家跟下家
		handler1.setSuccessor(handler2);
		handler2.setSuccessor(handler);
		
		int[] request = {1,10,20,30,40};
		for(int i = 0, length = request.length; i < length; i ++) {
			handler1.doRequest(request[i]);
		}
	}
}
