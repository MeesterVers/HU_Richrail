package Dao;

import java.sql.SQLException;
import java.util.*;

import Model.Train;

public interface TrainDao {
	public List<Train> findAll() throws SQLException;
	public Train findTrain(String train) throws SQLException;
	public Boolean save(String train) throws SQLException;
	public Boolean update(String train) throws SQLException;
	public Boolean delete(String train) throws SQLException;
}
