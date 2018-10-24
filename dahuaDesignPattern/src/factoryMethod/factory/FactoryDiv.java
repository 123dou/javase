package factoryMethod.factory;

import factoryMethod.operation.Operation;
import factoryMethod.operation.OperationDiv;

public class FactoryDiv implements IFactory{
	public Operation createOperation() {
		return new OperationDiv();
	}
}
