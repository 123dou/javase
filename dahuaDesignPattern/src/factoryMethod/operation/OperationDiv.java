package factoryMethod.operation;

public class OperationDiv extends Operation {

	@Override
	public double getResult() throws Exception {
		if(number2 == 0) {
			throw new Exception("除数不为0");
		}
		return number1 / number2;
	}

}
