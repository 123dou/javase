package builder;

import java.util.ArrayList;
import java.util.List;
/**
 * 产品类,由多个部件构成
 * @author dou
 *
 */
public class Product {
    private List<String> product = new ArrayList<>();
    public void add(String part) {
    	product.add(part);
    }
    public void show() {
    	System.out.println("产品创建------");
    	product.forEach(str -> System.out.println(str));
    }
}
