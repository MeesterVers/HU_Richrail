package Model;

public class Wagon {
	private int ID;
	private String name;
	private int numseats;
	private int train_id;
	
	public Wagon(String name) {		
		this.name = name;	
		this.numseats = 20;
	}	
	
	public Wagon(String name, int numseats) {
		this.name = name;
		this.numseats = numseats;
	}
	
	public Wagon(int ID, String name, int numseats) {
		this.ID = ID;
		this.name = name;
		this.numseats = numseats;
	}
	
	public Wagon(int ID, String name, int numseats, int train_id) {
		this.ID = ID;
		this.name = name;
		this.numseats = numseats;
		this.train_id = train_id;
	}
	
	public int getID() {
		return ID;
	}

	public String getName() {
		return this.name;		
	}
	
	public int getnumSeats() {
		return this.numseats; 
	}
	

	public int getTrain_id() {
		return train_id;
	}

	public void setTrain_id(int train_id) {
		this.train_id = train_id;
	}

	public String toString() {
		return "Wagon name = " + name + ", number of seats = " + numseats + "\n";
	}
	
}
