package factoryMethod.factory;

import factoryMethod.operation.Operation;
import factoryMethod.operation.OperationAdd;

public class FactoryAdd implements IFactory{
	public Operation createOperation() {
		return new OperationAdd();
	}
}
