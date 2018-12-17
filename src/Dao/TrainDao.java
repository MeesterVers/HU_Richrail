package Dao;

import java.sql.SQLException;
import java.util.*;

import Model.Train;

public interface TrainDao {
	public List<Train> findAll() throws SQLException;
	public List<Train> findTrain() throws SQLException;
	public Boolean save(Train train) throws SQLException;
	public Boolean update(Train train) throws SQLException;
	public Boolean delete(Train train) throws SQLException;
}
