package chainOfResponsibility.demo;

public abstract class Handler {  //抽像处理器
	//每个处理器都保存有下一级处理器的引用,当不满足本处理器的要求时,就会将处理传到下一级
    protected Handler handler;
    public void setSuccessor(Handler handler) {
    	this.handler = handler;
    }
    //需要处理的请求
    abstract void  doRequest(int request);
}
