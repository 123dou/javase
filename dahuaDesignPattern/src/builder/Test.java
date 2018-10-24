package builder;

public class Test {
    public static void main(String[] args) {
		Director director = new Director();
		Builder createproductA = new ConcrecteBuilderA();
		Builder createproductB = new ConcrecteBuilderB();
		director.createProduct(createproductA);
		Product productA = createproductA.getProduct();
		productA.show();
		director.createProduct(createproductB);
		Product productB = createproductB.getProduct();
		productB.show();
	}
}
