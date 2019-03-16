package Dao;

import java.sql.SQLException;
import java.util.*;

import Model.Wagon;

public interface WagonDao {
	public List<Wagon> findAll() throws SQLException;
	public List<Wagon> findWagonByTrainID(int trainID) throws SQLException;
	public Boolean save(Wagon wagon) throws SQLException;
	public Boolean update(Wagon wagon) throws SQLException;
	public Boolean delete(Wagon wagon) throws SQLException;
}
