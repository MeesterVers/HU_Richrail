package Dao;

import java.sql.SQLException;
import java.util.*;

import Model.Wagon;

public interface WagonDao {
	public List<Wagon> findAll() throws SQLException;
	public List<Wagon> findTrain() throws SQLException;
	public Wagon save(Wagon wagon) throws SQLException;
	public Wagon update(Wagon wagon) throws SQLException;
	public Wagon delete(Wagon wagon) throws SQLException;
}
