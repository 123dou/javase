package chainOfResponsibility.demo;

public class ConcreteHandler extends Handler {
    //处理请求小于十的
	@Override
	public void doRequest(int request) {
        if(request < 50)
        	System.out.println("总处理器处理: "  + request);
        else {
        	if(handler != null) 
        		handler.doRequest(request);
        }
	}

}
