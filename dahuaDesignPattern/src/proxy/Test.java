package proxy;

public class Test {
    public static void main(String[] args) {
		SchoolGirl meimei = new SchoolGirl("美女");
		Proxy proxy = new Proxy(meimei);
		proxy.giveChocolate();
		proxy.giveDolls();
		proxy.giveFlowers();
	}
}
