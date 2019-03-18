package Contollers;

import java.sql.SQLException;

import Dao.TrainDao;
import Dao.TrainDaoImpl;
import Dao.WagonDao;
import Dao.WagonDaoImpl;
import Model.Train;
import Model.Wagon;

public class Controller {

	/**
	 * @return List for the response output
	 * @throws SQLException
	 * 
	 * @Description Get all wagons from the database
	 */
	public String getAllWagons() throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();

		if (!wagonService.findAll().isEmpty()) {
			return "All wagons \n" + wagonService.findAll();
		} else {
			return "No wagons";
		}
	}

	/**
	 * @param wagonName : String
	 * @return String for the response output
	 * @throws SQLException
	 * 
	 * @Description Creates a new wagon with 20 seats
	 */
	public String createWagon(String wagonName) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();

		if (!wagonExists(wagonName)) {
			if (wagonService.save(wagonName, 20)) {
				return "Wagon " + wagonName + " created";
			} else {
				return "Wagon " + wagonName + " not created";
			}

		} else {
			return "Wagon " + wagonName + " already exists";
		}
	}

	/**
	 * @param wagonName : String, numseats : int
	 * @return String for the response output
	 * @throws SQLException
	 * 
	 * @Description Creates a new wagon with numseats
	 */
	public String createWagon(String wagonName, int numseats) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();

		if (!wagonExists(wagonName)) {
			if (wagonService.save(wagonName, numseats)) {
				return "Wagon " + wagonName + " created with " + numseats + " seats";
			} else {
				return "Wagon " + wagonName + " was not created with";
			}

		} else {
			return "Wagon " + wagonName + " already exists";
		}
	}

	/**
	 * @param wagonName : String, numseats : int
	 * @return String for the response output
	 * @throws SQLException
	 * 
	 * @Description Creates a new wagon with numseats
	 */
	public String addWagonToTrain(String wagonName, String trainName) throws SQLException {
		TrainDao trainService = new TrainDaoImpl();
		WagonDao wagonService = new WagonDaoImpl();

		if (wagonExists(wagonName)) {
			if (trainExists(trainName)) {
				if (wagonService.addWagonToTrain(trainService.findTrainByName(trainName).getID(), wagonName)) {
					return "Wagon " + wagonName + " added to train " + trainName;
				} else {
					return "Wagon " + wagonName + " was not added to train " + trainName;
				}

			} else {
				return "Train " + trainName + " doesn't exists";
			}

		} else {
			return "Wagon " + wagonName + " doesn't exists";
		}
	}

	/**
	 * @param wagonName : String
	 * @return boolean if excuted successfully
	 * @throws SQLException
	 * 
	 * @Description Deletes the wagon with the given name, if exists
	 */
	public String deleteWagon(String wagonName) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();

		if (wagonExists(wagonName)) {
			if (wagonService.delete(wagonName)) {
				return "Wagon " + wagonName + " deleted";
			} else {
				return "Wagon " + wagonName + " was not deleted";
			}

		} else {
			return "Wagon " + wagonName + " does not exists";
		}
	}

	/**
	 * @param wagonName : String, trainName : String
	 * @return String
	 * @throws SQLException
	 * 
	 * @Description removes the wagon with the given name from train if train and
	 *              wadons exists
	 */
	public String removeWagon(String wagonName, String trainName) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();

		if (wagonExists(wagonName)) {
			if (trainExists(trainName)) {
				if (wagonService.removeWagon(wagonName)) {
					return "Wagon " + wagonName + " removed from train " + trainName;
				} else {
					return "Wagon " + wagonName + " was not removed from train " + trainName;
				}

			} else {
				return "Wagon " + wagonName + " does not exists";
			}
		} else {
			return "Train " + trainName + " does not exists";
		}
	}

	/**
	 * @param wagonName : String
	 * @return String
	 * @throws SQLException
	 * 
	 * @Description gives the amount seats in a wagon if exisit
	 */
	public String wagonNumSeats(String wagonName) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();

		if (wagonExists(wagonName)) {
			for (Wagon wagon : wagonService.findAll()) {
				if (wagon.getName().equals(wagonName)) {
					return Integer.toString(wagon.getnumSeats());
				}
			}
		}
		return "Wagon " + wagonName + " does not exist";
	}

	/**
	 * @param wagonName : String
	 * @return boolean
	 * @throws SQLException
	 * 
	 * @Description checks if wagons exists
	 */
	public boolean wagonExists(String wagonName) throws SQLException {
		WagonDao wagonService = new WagonDaoImpl();

		for (Wagon wagon : wagonService.findAll()) {
			if (wagon.getName().equals(wagonName)) {
				return true;
			}
		}
		return false;
	}

	// train------------------------------------------------------------------------------------------------
	/**
	 * @param trainName : String
	 * @return String for the response output
	 * @throws SQLException
	 * 
	 * @Description Creates a new train
	 */
	public String createTrain(String trainName) throws SQLException {
		TrainDao trainService = new TrainDaoImpl();

		if (!trainExists(trainName)) {
			if (trainService.save(trainName)) {
				return "Train " + trainName + " created";
			} else {
				return "Train " + trainName + " was not created";
			}

		} else {
			return "Train " + trainName + " already exists";
		}
	}

	/**
	 * @return List with trains for the response output
	 * @throws SQLException
	 * 
	 * @Description Get all trains from the database
	 */
	public String getAllTrains() throws SQLException {
		TrainDao trainService = new TrainDaoImpl();

		if (!trainService.findAll().isEmpty()) {
			return "All trains \n" + trainService.findAll();
		} else {
			return "No trains";
		}
	}

	/**
	 * @param trainName : String
	 * @return String for the response output
	 * @throws SQLException
	 * 
	 * @Description Deletes the train with the given name, if exists
	 */
	public String deleteTrain(String trainName) throws SQLException {
		TrainDao trainService = new TrainDaoImpl();

		if (trainExists(trainName)) {
			if (trainService.delete(trainName)) {
				return "Train " + trainName + " deleted";
			} else {
				return "Train " + trainName + " was not deleted";
			}

		} else {
			return "Train " + trainName + " does not exist";
		}
	}

	/**
	 * @param trainName : String
	 * @return String for the response output
	 * @throws SQLException
	 * 
	 * @Description gives the amount of seats in train
	 */
	public String trainNumSeats(String trainName) throws SQLException {
		TrainDao trainService = new TrainDaoImpl();
		int numseats = 0;

		if (trainExists(trainName)) {
			Train train = trainService.findTrainByName(trainName);
			for (Wagon wagon : train.getWagons()) {
				numseats = wagon.getnumSeats() + numseats;
			}
			return Integer.toString(numseats);
		} else {
			return "Train " + trainName + " does not exist";
		}
	}

	/**
	 * @param trainName : String
	 * @return boolean
	 * @throws SQLException
	 * 
	 * @Description checks if trains exists in database
	 */
	public boolean trainExists(String trainName) throws SQLException {
		TrainDao trainService = new TrainDaoImpl();

		for (Train train : trainService.findAll()) {
			if (train.getName().equals(trainName)) {
				return true;
			}
		}
		return false;
	}

}