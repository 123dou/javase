package chainOfResponsibility.demo;

public class ConcreteHandler1 extends Handler {
    //处理请求小于十的
	@Override
	public void doRequest(int request) {
        if(request < 10)
        	System.out.println("1号处理器处理: " + request);
        else {
        	if(handler != null) 
        		handler.doRequest(request);
        }
	}

}
