package simpleFactory;

public class Test {
	public static void main(String[] args) {
		try {
			Operation operation = OperationFactory.createOperate("+");
			operation.setNumber1(23);
			operation.setNumber2(34);
			System.out.println(operation.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
