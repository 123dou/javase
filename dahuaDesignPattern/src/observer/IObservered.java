package observer;
/**
 * 被观察者的接口
 * @author dou
 *
 */
public interface IObservered {
    void add(IObserver observer);
    void remove(IObserver observer);
    void notifyObserver();
	String getAction();
}
