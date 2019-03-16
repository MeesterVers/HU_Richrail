package Contollers;

import java.sql.SQLException;

import Dao.TrainDaoImpl;
import Dao.WagonDaoImpl;

import Model.Railroad;
import Model.Train;
import Model.Wagon;

public class Controller {
	private Railroad railroad;
	
	
	/**
	 * @param railroad : Railroad
	 * 
	 * @Description Constructor
	 */
	public Controller(Railroad railroad) {
		this.railroad = railroad;
	}

	
	
	/**
	 * @param name : String
	 * @return String for the response output
	 * @throws SQLException 
	 * 
	 * @Description Creates a new wagon and adds the new wagon to 
	 * an arrayList in the class railroad
	 */
	public String createWagon(String name) throws SQLException {
		Wagon newWagon = new Wagon(name);

		if(!wagonExists(newWagon)) {
			railroad.addWagon(newWagon);
			return "Wagon " + name + " created";
		} else { 
			return "Wagon " + name + " already exists"; 
		}		
	}
	
	
	
	/**
	 * @param name : String, numseats : int
	 * @return String for the response output
	 * @throws SQLException 
	 * 
	 * @Description Creates a new wagon with numseats
	 * and adds the new wagon to an arrayList in the 
	 * class railroad
	 */
	public String createWagon(String name, int numseats) throws SQLException {
		Wagon newWagon = new Wagon(name, numseats);

		if(!wagonExists(newWagon)) {
			railroad.addWagon(newWagon);
			return "Wagon " + name + " created with " + numseats + " seats";
			
		} else { 
			return "Wagon " + name + " already exists" ;
		}	
	}
	
	public String addWagonToTrain(String wagonName, String trainName) throws SQLException {
		Wagon newWagon = new Wagon(wagonName);
		Train newTrain = new Train(trainName);

		if(wagonExists(newWagon)) {
			if(trainExists(newTrain)){
				railroad.addWagonTotrain(trainName, wagonName);
//				newTrain.addWagon(newWagon);
				return "Wagon " + wagonName + " added to train " + trainName;
			} else {
				return "Train " + trainName + " doesn't exists" ;
			}
			
		} else {
			return "Wagon " + wagonName + " doesn't exists" ;
		}
	}
	
	/**
	 * @param name : String
	 * @return String for the response output
	 * @throws SQLException 
	 * 
	 * @Description Deletes the wagon with the given
	 * name, if exists
	 */
	public String deleteWagon(String name) throws SQLException {
		if(wagonExists(new Wagon(name))) {
			railroad.deleteWagon(name);
			return "Wagon " + name + " deleted";
		} else { 
			return "Wagon " + name + " does not exists"; 
		}
	}
	
	public String removeWagon(String wagonName, String trainName) throws SQLException {
		Wagon newWagon = new Wagon(wagonName);
		Train newTrain = new Train(trainName);

		if(wagonExists(newWagon)) {
			if(trainExists(newTrain)){
				railroad.removeWagon(newWagon, newTrain);
				return "Wagon " + newWagon + " removed from train " + newTrain;
			} else { 
				return "Wagon " + newWagon + " does not exists"; 
			}
		} else {
			return "Train " + newTrain + " does not exists"; 
		}
	}
	
	
	
	
	/**	
	 * @param w : Wagon w
	 * @return boolean if wagon exists
	 * @throws SQLException 
	 * 
	 * @Description Checks if the given wagon exists in the 
	 * array from the class railroad
	 */
	public boolean wagonExists(Wagon w) throws SQLException {
		for(Wagon wagon : railroad.getWagons()) {
			if(wagon.getName().equals(w.getName())) { 
				return true; 
			} else {
				return false;
			}	
		}
		return false;
	}
	
	
	
	/**
	 * @param name : String
	 * @return String for the response output
	 * @throws SQLException 
	 * 
	 * @Description Returns the number of seats 
	 * from the wagon with the given name
	 */
	public String wagonNumSeats(String name) throws SQLException {
		for (Wagon wagon : railroad.getWagons()) {
			if (wagon.getName().equals(name)) {
				return Integer.toString(wagon.getnumSeats());
			}
		}
		return "Wagon " + name + " does not exist";
	}
	
	
	
	/**
	 * @param name : String
	 * @return String for the response output
	 * @throws SQLException 
	 * 
	 * @Description Creates a new train and adds the new train to 
	 * an arrayList in the class railroad
	 */
	public String createTrain(String name) throws SQLException {
		TrainDaoImpl trainService = new TrainDaoImpl();

		if(!trainExists(name)) {
			trainService.save(name);
			return "Train " + name + " created";
		} else { 
			return "Train " + name + " already exists"; 
		}	
	}
	
	// public boolean createTrainGui(String name) throws SQLException {
	// 	Train newTrain = new Train(name);
	// 	if(!trainExists(newTrain)) {
	// 		railroad.setSelectedTrain(newTrain);
	// 		railroad.addTrain(newTrain);
			
	// 		return true;
			
	// 	} else { 
	// 		return false; 
	// 	}	
	// }
	
	public String getAllTrains() throws SQLException {
		TrainDaoImpl trainService = new TrainDaoImpl();
		
		if (!trainService.findAll().isEmpty()) {
			return "All trains \n" + trainService.findAll();
		}else {
			return "No trains";
		}
	}
	
	
	
	/**
	 * @param name : String
	 * @return String for the response output
	 * @throws SQLException 
	 * 
	 * @Description Deletes the train with the given
	 * name, if exists
	 */
	public String deleteTrain(String name) throws SQLException {
	TrainDaoImpl trainService = new TrainDaoImpl();

		if(trainExists(name)) {
			trainService.deleteTrain(name);
			return "Train " + name + " deleted";
		} else { 
			return "Train " + name + " does not exist"; 
		}		
	}
	

	
	/**
	 * @param name : String
	 * @return String for the response output
	 * @throws SQLException 
	 * 
	 * @Description Returns the number of seats 
	 * from the train with the given name
	 */
	public String trainNumSeats(String name) throws SQLException {
		for (Train train : railroad.getTrains()) {
			if (train.getName().equals(name)) {
				return Integer.toString(train.getNumSeats());
			}
		}
		return "Train " + name + " does not exist";
	}
	
	
	
	/**	
	 * @param name : Train
	 * @return boolean if train exists
	 * @throws SQLException 
	 * 
	 * @Description Checks if the given train exists in the DB
	*/
	
	public boolean trainExists(String name) throws SQLException {
		TrainDaoImpl trainService = new TrainDaoImpl();
		
		for(Train train : trainService.findAll()) {
			if(train.getName().equals(name)){
				return true;
			}else {
				return false;
			}	
		}
		return false;
	}
	
}