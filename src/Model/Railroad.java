package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import Dao.TrainDaoImpl;
import Dao.WagonDaoImpl;

public class Railroad {
	private List<Train> trains;
	private List<Wagon> wagons;
	private Train selectedTrain = null;	
	
	public Railroad() {
		trains = new ArrayList<Train>();
		wagons = new ArrayList<Wagon>();
	}

	public List<Train> getTrains() throws SQLException {
		TrainDaoImpl trainService = new TrainDaoImpl();
		trains = trainService.findAll();
		return trainService.findAll();
	}
	
	public void addTrain(Train train) throws SQLException {
		TrainDaoImpl trainService = new TrainDaoImpl();
		
		if(trainService.save(train)) {
			trains.add(train);
		}
	}
	
	public void deleteTrain(String name) throws SQLException {
		TrainDaoImpl trainService = new TrainDaoImpl();
	
		for(Train t : trainService.findAll()) {
			
			if(t.getName().equals(name)) {
				if(trainService.delete(t)) {
					trains.remove(t);
				}
			}			
		}	
	}
	
	public List<Wagon> getWagons() {
		return wagons;
	}
	
	public void addWagon(Wagon wagon) throws SQLException {
		WagonDaoImpl wagonService = new WagonDaoImpl();
		
		if(wagonService.save(wagon)) {
			wagons.add(wagon);
		}
	}
	
	
	public void removeWagon(Wagon wagonName, Train trainName) {
		for(Train t : trains) {
			if(t.getName().equals(t)) {
				for(Wagon wagon : t.getWagons()) {
					if(wagon.getName().equals(wagonName)) {
						t.getWagons().remove(wagon);
					}
				}
			}
		}
	}
	
	public void deleteWagon(String name) {
		for(int i = 0; i < wagons.size(); i++) {
			Wagon wagon = wagons.get(i);
			if(wagon.getName().equals(name)) { 
				wagons.remove(i);
			}		
		}
	}
	
	public Train getSelectedTrain() {
		return selectedTrain;
	}

	public void setSelectedTrain(Train selectedTrain) {
		this.selectedTrain = selectedTrain;
	};
	
}
