package observer;
/**
 * 具体的观察者
 * @author dou
 *
 */
public class NBAObserver implements IObserver {
    private String name;
    private IObservered secretary; //被观的对像
    
	public NBAObserver() {
	}

	public NBAObserver(String name, IObservered secretary) {
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
        System.out.println(secretary.getAction() + name + "关闭NBA直播,继续工作");
	}

}
