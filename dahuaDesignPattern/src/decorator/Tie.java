package decorator;

public class Tie extends Finery {
    public Tie(Finery finery) {
    	this.person = finery;
    }
    
    public void show() {
    	if(person != null)
    	    person.show();
    	System.out.println("西装");
    }
}
