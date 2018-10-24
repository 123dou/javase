package mediator;

public class ConcreteColleague2 extends Colleague {
    
	public ConcreteColleague2(Mediator mediator) {
		super(mediator);
	}
	
	public void send(String message) {
		mediator.sendMessage(message, this);
	}
	
	public void notifC(String message) {
		System.out.println("同事2得到消息: " + message);
	}

}
