package command.example;

public class Test {
    public static void main(String[] args) {
    	//开店前的准备
		Barbecuer barbecuer = new Barbecuer();
		Command bakeMuttonC = new BakeMuttonCommand(barbecuer);
		Command bakeChickenWingC = new BakeChickenWing(barbecuer);
		Waiter waiter = new Waiter();
		//开始点菜
		waiter.setOrder(bakeMuttonC);
		waiter.setOrder(bakeChickenWingC);
		waiter.setOrder(bakeChickenWingC);
		waiter.CancelOrder(bakeChickenWingC);
		waiter.executeCommand();
		
	}
}
