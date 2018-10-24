package factoryMethod;

import factoryMethod.factory.FactoryAdd;
import factoryMethod.factory.IFactory;
import factoryMethod.operation.Operation;

public class Test {
	public static void main(String[] args) {
		try {
			IFactory factory = new FactoryAdd();
			Operation operation = factory.createOperation();
			operation.setNumber1(23);
			operation.setNumber2(34);
			System.out.println(operation.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
