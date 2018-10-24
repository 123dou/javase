package observer;

public class StockObserver implements IObserver {
    private String name;
    private IObservered secretary;
    
	public StockObserver() {
	}

	public StockObserver(String name, IObservered secretary) {
		super();
		this.name = name;
		this.secretary = secretary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IObservered getSecretary() {
		return secretary;
	}

	public void setSecretary(Secretary secretary) {
		this.secretary = secretary;
	}

	@Override
	public void update() {
        System.out.println(secretary.getAction() + name + "关闭股票行情,继续工作");
	}

}
