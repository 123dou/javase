package mediator;

public class ConcreteMediator extends Mediator {
    private ConcreteColleague1 concreteColleague1;
    private ConcreteColleague2 concreteColleague2;
    
	public void setConcreteConlleague1(ConcreteColleague1 concreteColleague1) {
		this.concreteColleague1 = concreteColleague1;
	}

	public void setConcreteConlleague2(ConcreteColleague2 concreteColleague2) {
		this.concreteColleague2 = concreteColleague2;
	}

	@Override
	public void sendMessage(String message, Colleague colleague) {
	    if(colleague == concreteColleague1)
	    	concreteColleague2.notifC(message);
	    else
	    	concreteColleague1.notifC(message);
	}

}
