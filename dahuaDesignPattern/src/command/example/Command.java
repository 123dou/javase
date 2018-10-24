package command.example;

public abstract class Command {
    protected Barbecuer barbecuer;
    public Command(Barbecuer barcuer) {
    	this.barbecuer = barcuer;
	}
    abstract void execute();
}
