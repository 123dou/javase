package factoryMethod.factory;

import factoryMethod.operation.Operation;
import factoryMethod.operation.OperationMul;

public class FactoryMul implements IFactory{
	public Operation createOperation() {
		return new OperationMul();
	}
}
