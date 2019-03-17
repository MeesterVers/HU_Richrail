package Contollers;

import java.sql.SQLException;

import Dao.TrainDao;
import Dao.TrainDaoImpl;
import Dao.WagonDao;
import Dao.WagonDaoImpl;
import Model.Train;
import Model.Wagon;

public class Controller {

	public String getAllWagons() throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();
		
		if (!wagonService.findAll().isEmpty()) {
			return "All wagons \n" + wagonService.findAll();
		}else {
			return "No wagons";
		}
	}
	
	
	/**
	 * @param name : String
	 * @return String for the response output
	 * @throws SQLException 
	 * 
	 * @Description Creates a new wagon and adds the new wagon to 
	 * an arrayList in the class railroad
	 */
	public String createWagon(String wagonName) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();

		if(!wagonExists(wagonName)) {
			if(wagonService.save(wagonName, 20)){
				return "Wagon " + wagonName + " created";
			}else{
				return "Wagon " + wagonName + " not created";
			}
			
		} else { 
			return "Wagon " + wagonName + " already exists"; 
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
	public String createWagon(String wagonName, int numseats) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();
		
		if(!wagonExists(wagonName)) {
			if(wagonService.save(wagonName, numseats)){
				return "Wagon " + wagonName + " created with " + numseats + " seats";
			}else{
				return "Wagon " + wagonName + " was not created with";
			}
			
		} else { 
			return "Wagon " + wagonName + " already exists" ;
		}	
	}
	
	public String addWagonToTrain(String wagonName, String trainName) throws SQLException {
		TrainDao trainService = new TrainDaoImpl();
		WagonDao wagonService = new WagonDaoImpl();
		
		if(wagonExists(wagonName)){
			if(trainExists(trainName)){
				if(wagonService.addWagonToTrain(trainService.findTrainByName(trainName).getID(), wagonName)){
					return "Wagon " + wagonName + " added to train " + trainName;
				}else{
					return "Wagon " + wagonName + " was not added to train " + trainName;
				}
				
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
	public String deleteWagon(String wagonName) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();
		
		if(wagonExists(wagonName)) {
			if(wagonService.delete(wagonName)){
				return "Wagon " + wagonName + " deleted";
			}else{
				return "Wagon " + wagonName + " was not deleted";
			}
			
		} else { 
			return "Wagon " + wagonName + " does not exists"; 
		}
	}

	public String removeWagon(String wagonName, String trainName) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();

		if(wagonExists(wagonName)) {
			if(trainExists(trainName)){
				if(wagonService.removeWagon(wagonName)) {
					return "Wagon " + wagonName + " removed from train " + trainName;
				}else {
					return "Wagon " + wagonName + " was not removed from train " + trainName;
				}
				
			} else { 
				return "Wagon " + wagonName + " does not exists"; 
			}
		} else {
			return "Train " + trainName + " does not exists"; 
		}
	}

	public String wagonNumSeats(String wagonName) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();

		for (Wagon wagon : wagonService.findAll()) {
			if (wagon.getName().equals(wagonName)) {
				return Integer.toString(wagon.getnumSeats());
			}
		}
		return "Wagon " + wagonName + " does not exist";
	}
	

	public boolean wagonExists(String wagonName) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();
		
		for(Wagon wagon : wagonService.findAll()) {
			if(wagon.getName().equals(wagonName)) { 
				return true; 
			}	
		}
		return false;
	}
	
//train------------------------------------------------------------------------------------------------ 
	/**
	 * @param name : String
	 * @return String for the response output
	 * @throws SQLException 
	 * 
	 * @Description Creates a new train and adds the new train to 
	 * an arrayList in the class railroad
	 */
	public String createTrain(String trainName) throws SQLException {
		TrainDao trainService = new TrainDaoImpl();

		if(!trainExists(trainName)) {
			if(trainService.save(trainName)){
				return "Train " + trainName + " created";
			}else{
				return "Train " + trainName + " was not created";
			}
			
		} else { 
			return "Train " + trainName + " already exists"; 
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
		TrainDao trainService = new TrainDaoImpl();
		
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
	public String deleteTrain(String trainName) throws SQLException {
		TrainDao trainService = new TrainDaoImpl();

		if(trainExists(trainName)) {
			if(trainService.delete(trainName)){
				return "Train " + trainName + " deleted";
			}else{
				return "Train " + trainName + " was not deleted";
			}
			
		} else { 
			return "Train " + trainName + " does not exist"; 
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
	public String trainNumSeats(String trainName) throws SQLException {
		TrainDao trainService = new TrainDaoImpl();
		int numseats = 0;
			
		if(trainExists(trainName)) {
			Train train = trainService.findTrainByName(trainName);
			for(Wagon wagon : train.getWagons()){
				numseats = wagon.getnumSeats() + numseats;
			}
				return Integer.toString(numseats);
			} else {
			return "Train " + trainName + " does not exist";
		}
	}

	public boolean trainExists(String trainName) throws SQLException {
		TrainDao trainService = new TrainDaoImpl();
		
		for(Train train : trainService.findAll()) {
			if(train.getName().equals(trainName)){
				return true;
			}
		}
		return false;
	}
	
}