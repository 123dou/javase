package simpleFactory;
/**
 * 定义(加减乘除)运算的抽象类
 * @author dou
 *
 */
/**
 * @author dou
 *
 */
public abstract class Operation {
    protected double number1;
    protected double  number2;
	public double getNumber1() {
		return number1;
	}

	public void setNumber1(double number1) {
		this.number1 = number1;
	}

	public double getNumber2() {
		return number2;
	}

	public void setNumber2(double number2) {
		this.number2 = number2;
	}

	public abstract double getResult() throws Exception;
}
