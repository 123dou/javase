package command.example;

public class BakeMuttonCommand extends Command {

	public BakeMuttonCommand(Barbecuer barcuer) {
		super(barcuer);
	}

	@Override
	void execute() {
		barbecuer.BakeMutton();
	}

	@Override
	public String toString() {
		return "烤羊肉串";
	}
	
	

}
