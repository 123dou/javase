package decorator;

public class LeatherShoes extends Finery {
    public LeatherShoes(Finery finery) {
    	this.person = finery;
    }
    
    public LeatherShoes() {
	}

	public void show() {
		if(person != null)
    	    person.show();
    	System.out.println("皮鞋");
    }
}
