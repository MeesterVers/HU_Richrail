package Model;

public class Wagon {
	private String name;
	private int numseats;
	
	public Wagon(String name) {		
		this.name = name;	
		this.numseats = 20;
	}	
	
	public Wagon(String name, int numseats) {
		this.name = name;
		this.numseats = numseats;
	}
	
	public String getName() {
		return this.name;		
	}
	
	public int getnumSeats() {
		return this.numseats; 
	}
}
