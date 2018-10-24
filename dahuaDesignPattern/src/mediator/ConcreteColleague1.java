package mediator;

public class ConcreteColleague1 extends Colleague {
    
	public ConcreteColleague1(Mediator mediator) {
		super(mediator);
	}
	
	public void send(String message) {
		mediator.sendMessage(message, this);
	}
	
	public void notifC(String message) {
		System.out.println("同事1得到消息: " + message);
	}

}
