package observer;

public class Test {
    public static void main(String[] args) {
    	Secretary secretary = new Secretary();
        IObserver stockObserver = new StockObserver("小明",secretary);
        IObserver NBAObserver = new NBAObserver("小黑",secretary);
        secretary.add(NBAObserver);
        secretary.add(stockObserver);
        secretary.setAction("老板回来了---");
        secretary.notifyObserver();
        
	}
}
