package Singleton;

public enum Singleon {
    INSTANCE;
	Singleon() {
		System.out.println("诞生的时候做点什么");
	}
	public void doSomething() {
		System.out.println("do something");
	}
}
