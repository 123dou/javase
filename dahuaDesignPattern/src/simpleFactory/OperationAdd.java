package simpleFactory;

public class OperationAdd extends Operation {

	@Override
	public double getResult() {
		return number1 + number2;
	}

}
