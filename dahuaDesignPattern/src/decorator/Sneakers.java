package decorator;

public class Sneakers extends Finery {
    public Sneakers(Finery finery) {
    	this.person = finery;
    }
    
    public Sneakers() {
	}

	public void show() {
		if(person != null)
    	    person.show();
    	System.out.println("破球鞋");
    }
}
