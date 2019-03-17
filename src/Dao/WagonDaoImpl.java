package Dao;

import java.sql.*;
import java.util.*;

import Model.Train;
import Model.Wagon;

public class WagonDaoImpl implements WagonDao{
	private static Connection conn;
	private List<Wagon> wagons = new ArrayList<Wagon>();


	public List<Wagon> findAll() throws SQLException {
		List<Wagon> wagons = new ArrayList<Wagon>();
		conn = BaseDao.getConnection();
		
		String query = "SELECT * FROM wagons";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		while (result.next()) {
			int ID = result.getInt("id");
			String name = result.getString("name");
			int numseats = result.getInt("seats");
			int train_id = result.getInt("train_id");

			Wagon wagon = new Wagon(ID, name, numseats, train_id);
			wagons.add(wagon);
		}

		conn.close();
		result.close();
		return wagons;
	}


	public List<Wagon> findWagonByTrainID(int trainID) throws SQLException {
		List<Wagon> wagons = new ArrayList<Wagon>();
		conn = BaseDao.getConnection();
		
		String query = "SELECT * FROM wagons WHERE train_id = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, trainID);
		ResultSet result = statement.executeQuery();
		
		while (result.next()) {
			int ID = result.getInt("id");
			String name = result.getString("name");
			int numseats = result.getInt("seats");
			int train_id = result.getInt("train_id");

			Wagon wagon = new Wagon(ID, name, numseats, train_id);
			wagons.add(wagon);
		}

		conn.close();
		result.close();
		return wagons;
	}

	public Boolean save(String wagonName, int numseats) throws SQLException {
		conn = BaseDao.getConnection();
		String query = "INSERT INTO wagons (id, name, seats) VALUES (WAGONS_SEQ.nextval, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, wagonName);
		statement.setInt(2, numseats);

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("SUCCESS");
			conn.close();
			return true;
		}else {
			System.out.println("NO SUCCESS");
			conn.close();
			return false;
		}
	}

	public Boolean update(String wagonName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public Boolean delete(String wagonName) throws SQLException {
		conn = BaseDao.getConnection();
		String query = "DELETE FROM WAGONS WHERE NAME = ?";

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, wagonName);
		
		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			conn.close();
			return true;
		} else {
			conn.close();
			return false;
		}
	}
	
	public Boolean addWagonToTrain(int trainID, String wagonName) throws SQLException{
		conn = BaseDao.getConnection();
		String UpdateQuery = "UPDATE wagons set train_id = ? WHERE name = ?";
		
		PreparedStatement UpdateStatement = conn.prepareStatement(UpdateQuery);
		UpdateStatement.setInt(1, trainID);
		UpdateStatement.setString(2, wagonName);

		int rowsUpdated = UpdateStatement.executeUpdate();
		if (rowsUpdated > 0) {
			conn.close();
			return true;
		} else {
			conn.close();
			return false;
		}
	}

	public Boolean removeWagon(String wagonName) throws SQLException{
		conn = BaseDao.getConnection();
		String UpdateQuery = "UPDATE wagons set train_id = ? WHERE name = ?";
		
		PreparedStatement UpdateStatement = conn.prepareStatement(UpdateQuery);
		UpdateStatement.setInt(1, 0);
		UpdateStatement.setString(2, wagonName);

		int rowsUpdated = UpdateStatement.executeUpdate();
		if (rowsUpdated > 0) {
			conn.close();
			return true;
		} else {
			conn.close();
			return false;
		}
	}

}
