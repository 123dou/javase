package decorator;

public class Finery extends Person {
	
    protected Person person;
    public Finery() {
    	
    }
	public Finery(Person person) {
    	this.person = person;
	}
	public void decorator(Person person) {
		this.person = person;
	}

	public void show() {
    	person.show();
    }
}
