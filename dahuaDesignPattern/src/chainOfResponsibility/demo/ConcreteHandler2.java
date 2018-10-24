package chainOfResponsibility.demo;

public class ConcreteHandler2 extends Handler {
    //处理请求小于十的
	@Override
	public void doRequest(int request) {
        if(request < 20)
        	System.out.println("2号处理器处理 " + request);
        else {
        	if(handler != null) 
        		handler.doRequest(request);
        }
	}

}
