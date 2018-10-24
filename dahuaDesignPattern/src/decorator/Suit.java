package decorator;

public class Suit extends Finery {
    public Suit(Finery finery) {
    	this.person = finery;
    }
    
    public void show() {
    	if(person != null)
    	    person.show();
    	System.out.println("西装");
    }
}
