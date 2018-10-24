package builder;
/**
 * 规定产品必须要添加的部件
 * @author dou
 *
 */
public abstract class Builder {
    public abstract void addA();
    public abstract void addB();
    public abstract Product getProduct();
}
