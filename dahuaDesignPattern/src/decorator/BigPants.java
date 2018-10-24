package decorator;

public class BigPants extends Finery {
    public BigPants(Finery finery) {
    	this.person = finery;
    }
    
    public void show() {
    	if(person != null)
    	    person.show();
    	System.out.println("大裤子");
    }
}
