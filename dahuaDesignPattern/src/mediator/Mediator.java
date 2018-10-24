package mediator;

public abstract class Mediator {
    protected Colleague colleague;
    abstract void sendMessage(String message, Colleague colleague);
}
