package observer;

import java.util.ArrayList;
import java.util.List;

public class Secretary implements IObservered{
	//同事列表，把关察对像添加到这里
    private List<IObserver> observers = new ArrayList<>();
    private String action;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	//添加观察对像
	@Override
	public void add(IObserver observer) {
		observers.add(observer);
	}
	//移除观察对像
	@Override
	public void remove(IObserver observer) {
		observers.remove(observer);
	}
	//根据状态逐个通知观察对像
	@Override
	public void notifyObserver() {
		observers.forEach(o -> o.update());
	}
	
}
