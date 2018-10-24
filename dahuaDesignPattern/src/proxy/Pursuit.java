package proxy;

public class Pursuit implements IGiveGift{
    private SchoolGirl mm;
    public Pursuit(SchoolGirl mm) {
    	this.mm = mm;
    }
	@Override
	public void giveDolls() {
		System.out.println("送你一个洋娃娃~~");
	}

	@Override
	public void giveFlowers() {
		System.out.println("送你一个小红花~~");
	}

	@Override
	public void giveChocolate() {
		System.out.println("送你德芙~~");
	}

}
