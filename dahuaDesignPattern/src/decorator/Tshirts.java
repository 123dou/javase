package decorator;

public class Tshirts extends Finery {
    public Tshirts(Finery finery) {
    	this.person = finery;
    }
    
    public Tshirts() {
	}

	public void show() {
		if(person != null)
    	    person.show();
    	System.out.println("大T恤");
    }
}
