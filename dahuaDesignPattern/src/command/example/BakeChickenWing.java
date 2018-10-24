package command.example;

public class BakeChickenWing extends Command {

	public BakeChickenWing(Barbecuer barcuer) {
		super(barcuer);
	}

	@Override
	void execute() {
		barbecuer.BakeChickenWing();
	}

	@Override
	public String toString() {
		return "烤鸡翅";
	}
	

}
