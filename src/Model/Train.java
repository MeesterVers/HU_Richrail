package Model;
import java.util.ArrayList;
import java.util.List;

public class Train {
	private int ID;
	private String name;
	private List<Wagon> wagons;

	public Train(String name) {	
		this.name = name;			
	}
	
	public Train(int ID, String name) {
		this.ID = ID;
		this.name = name;
    	this.wagons = new ArrayList<Wagon>();
	}

	public int getID() {
		return ID;
	}	
	
	public String getName() {
		return this.name;			
	}
	
	// wagons methods
	public void setWagons(List<Wagon> wagons) {
		this.wagons = wagons;	
	}

	public List<Wagon> getWagons() {
		return this.wagons; 		
	}
	
	public String toString() {
		if(getWagons().isEmpty()) {
			return "Train name: " + name + " No wagons \n";
		}else {
			return "Train name: " + name + " Wagons: " + getWagons() + "\n";
		}
		
	}
	
	
}
