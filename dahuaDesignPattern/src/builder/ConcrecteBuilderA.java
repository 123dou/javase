package builder;
/**
 * 产品添加的具体部件
 * @author dou
 *
 */
public class ConcrecteBuilderA extends Builder {
    private Product product = new Product();
	@Override
	public void addA() {
        product.add("partA");
	}

	@Override
	public void addB() {
        product.add("partB");
	}

	@Override
	public Product getProduct() {
		return product;
	}

}
