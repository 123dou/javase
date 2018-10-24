package builder;
/**
 * 控制产品的建造过程
 * @author dou
 *
 */
public class Director {
    public void createProduct(Builder builder) {
    	builder.addA();
    	builder.addB();
    }
}
