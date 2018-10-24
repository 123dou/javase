package decorator;
/**
 * 装饰者模式是为已有的功能动态的添加更多功能的一种方式,那到底什么时候用它呢?
 * 当系统需要新的功能的时候是向旧的类中添加新的代码,这些新加的代码通常装饰了原有类的的核心职责或主要行为,如果直接在主类中添加新的方法和新的火逻辑,从而增加了主类的
 * 组类的复杂度,而这些新加入的东西仅仅是为了满足一些只在特殊情况下的特殊行为的需要,而装饰者模式却提供了一个非常好的解决方案,它把每个要装饰的功能放在单独的类中,并
 * 让这个类包装他所要包装的对像,因此,当需要执行特殊行为时,客户端就可以在运行时根据需要有选择地,按顺序地使用装饰功能包装对像啦.
 * @author dou
 *
 */
public class Test {
    public static void main(String[] args) {
		Person person = new Person("小明");
		Finery finery = new Finery();
		Sneakers sneakers = new Sneakers();
		Tshirts tshirts = new Tshirts();
		LeatherShoes leatherShoes = new LeatherShoes();
		
		finery.decorator(person);
		sneakers.decorator(finery);
		tshirts.decorator(sneakers);
		leatherShoes.decorator(tshirts);
		
		leatherShoes.show();
	}
}
