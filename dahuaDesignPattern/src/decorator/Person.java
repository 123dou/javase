package decorator;

public class Person {
    private String name;
    public Person(String name) {
    	this.name = name;
    }
    public Person(){
    	
    }
    public void show() {
    	System.out.println(name + "的装扮:");
    }
}
