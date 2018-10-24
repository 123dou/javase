package mediator;

public class Test {
    public static void main(String[] args) {
    	ConcreteMediator mediator = new ConcreteMediator();
    	ConcreteColleague1 c1 = new ConcreteColleague1(mediator);
    	ConcreteColleague2 c2 = new ConcreteColleague2(mediator);
		mediator.setConcreteConlleague1(c1);
		mediator.setConcreteConlleague2(c2);
		c1.send("hello");
		c2.send("你好");
		
		
	}
}
