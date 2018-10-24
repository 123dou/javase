package simpleFactory;

public class OperationFactory {
    public static Operation createOperate(String operate) throws Exception {
    	Operation operation = null;
    	switch(operate) {
    	case "+":
    		operation = new OperationAdd();
    		break;
    	case "-":
    		operation = new OperationSub();
    		break;
    	case "*":
    		operation = new OperationMul();
    		break;
    	case "/":
    		operation = new OperationDiv();
    		break;
    	default :
    		throw new Exception("请输入+,-,*,/中的一个符号");
    	}
    	return operation;
    }
}
