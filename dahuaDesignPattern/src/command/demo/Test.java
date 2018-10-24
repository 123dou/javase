package command.demo;

public class Test {
    public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Command concreteCommand = new ConcreteCommand(receiver);
		Invoker invoker = new Invoker();
		invoker.setCommand(concreteCommand);
		invoker.executeCommand();
	}
}
