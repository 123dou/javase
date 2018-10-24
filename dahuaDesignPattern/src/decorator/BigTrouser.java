package decorator;

public class BigTrouser extends Finery {
    public BigTrouser(Finery finery) {
    	this.person = finery;
    }
    
    public void show() {
    	if(person != null)
    	    person.show();
    	System.out.println("跨裤");
    }
}
