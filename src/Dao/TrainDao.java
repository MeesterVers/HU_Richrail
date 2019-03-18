package Dao;

import java.sql.SQLException;
import java.util.List;

import Model.Train;

public interface TrainDao {
	public List<Train> findAll() throws SQLException;

	public Train findTrainByName(String trainName) throws SQLException;

	public Boolean save(String trainName) throws SQLException;

	public Boolean delete(String trainName) throws SQLException;
}
