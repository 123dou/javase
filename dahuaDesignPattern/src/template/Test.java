package template;
/**
 * 模板方法模式是通过把不变的行为搬移到超类,去除子类中的重置代码来体现它的优势
 * 它提供了一个很好的代码复用平台
 * @author dou
 *
 */
public class Test {
    public static void main(String[] args) {
		TestPaper stuA = new TestPaperA();
		stuA.question1();
		stuA.question2();
		stuA.question3();
		System.out.println("---------------------");
		TestPaper stuB = new TestPaperB();
		stuB.question1();
		stuB.question2();
		stuB.question3();
	}
}
