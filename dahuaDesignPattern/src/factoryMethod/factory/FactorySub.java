package factoryMethod.factory;

import factoryMethod.operation.Operation;
import factoryMethod.operation.OperationSub;

public class FactorySub implements IFactory{
	public Operation createOperation() {
		return new OperationSub();
	}
}
