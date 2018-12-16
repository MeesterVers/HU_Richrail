package Model;

import java.util.ArrayList;


public class Train {
	private String name;
	private ArrayList<Wagon> wagons = new ArrayList<Wagon>();
			
	public Train(String name) {	
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
}
