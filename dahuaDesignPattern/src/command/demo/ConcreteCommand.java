package command.demo;

public class ConcreteCommand extends Command{
    public ConcreteCommand(Receiver receiver) {
    	super(receiver);
	}
	@Override
	void execute() {
		receiver.action();
	}

}
