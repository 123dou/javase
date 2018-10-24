package builder;

public class ConcrecteBuilderB extends Builder {
    private Product product = new Product();
	@Override
	public void addA() {
        product.add("partX");
	}

	@Override
	public void addB() {
        product.add("partY");
	}

	@Override
	public Product getProduct() {
		return product;
	}

}
