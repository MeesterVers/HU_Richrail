package Model;

import java.util.ArrayList;


public class Train {
	private int ID;
	private String name;
	
	private ArrayList<Wagon> wagons = new ArrayList<Wagon>();
	public Train(String name) {	
		this.name = name;			
	}
	
	public Train(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	public void addWagon(Wagon wagon) {
		this.wagons.add(wagon);		
	}

	public void clearWagons() {
		wagons.clear(); 	 		
	}	
	
	public String getName() {
		return this.name;			
	}
	
	

	public ArrayList<Wagon> getWagons() {
		return this.wagons; 		
	}

	public boolean hasName() {
		return (this.name != "");	
	}
	
	public int getNumSeats() {
		int numseatsTotal = 0;
		
		for(Wagon w : wagons) {
			numseatsTotal += w.getnumSeats();
		}
		return numseatsTotal;		
	}
	
	

	public int getID() {
		return ID;
	}

	@Override
	public String toString() {
		return "Train name: " + name;
	}
	
	
}
