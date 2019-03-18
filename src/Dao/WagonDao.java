package Dao;

import java.sql.SQLException;
import java.util.List;

import Model.Wagon;

public interface WagonDao {
	public List<Wagon> findAll() throws SQLException;

	public List<Wagon> findWagonByTrainID(int trainID) throws SQLException;

	public Boolean save(String wagonName, int numseats) throws SQLException;

	public Boolean delete(String wagonName) throws SQLException;

	public Boolean addWagonToTrain(int trainID, String wagonName) throws SQLException;

	public Boolean removeWagon(String wagonName) throws SQLException;
}
