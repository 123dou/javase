package adapter.demo;
/**
 * 通过在内部包装一个adaptee对像,把源接口转换成目标接口
 * @author dou
 *
 */
public class Adapter extends Target{
    private Adaptee adaptee = new Adaptee();
    public void request() {
    	adaptee.specificRequest();
    }
}
