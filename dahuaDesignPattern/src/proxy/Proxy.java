package proxy;
/**
 * 代理
 * @author dou
 *
 */
public class Proxy implements IGiveGift{
    private Pursuit pursuit; //代理的对像
    public Proxy(SchoolGirl mm) { //通过构造器创建代理对像
    	this.pursuit = new Pursuit(mm);
    }
	@Override
	public void giveDolls() { //没个代理方法的实际实现都是用实际的对像来实现
		pursuit.giveDolls();
	}

	@Override
	public void giveFlowers() {
		pursuit.giveFlowers();
	}

	@Override
	public void giveChocolate() {
		pursuit.giveChocolate();
	}

}
