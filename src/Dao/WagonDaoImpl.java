package Dao;

import java.sql.*;
import java.util.*;

import Model.Train;
import Model.Wagon;

public class WagonDaoImpl extends BaseDao implements WagonDao{
	private static Connection conn;
	private List<Wagon> wagons = new ArrayList<Wagon>();

	@Override
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

	@Override
	public List<Wagon> findWagonByTrainID(int trainID) throws SQLException {
		List<Wagon> wagons = new ArrayList<Wagon>();
		conn = BaseDao.getConnection();
		
		String query = "SELECT * FROM wagons WHERE train_id = " + trainID;
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

	@Override
	public Boolean save(Wagon wagon) throws SQLException {
		conn = BaseDao.getConnection();
		String query = "INSERT INTO wagons (id, name, seats) VALUES (WAGONS_SEQ.nextval, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, wagon.getName());
		statement.setInt(2, wagon.getnumSeats());

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

	@Override
	public Boolean update(Wagon wagon) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Wagon wagon) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Boolean addWagonToTrain( Train train, Wagon wagon) throws SQLException{
		conn = BaseDao.getConnection();
		String UpdateQuery = "UPDATE wagons set train_id = ? WHERE id = ?";
		
		PreparedStatement UpdateStatement = conn.prepareStatement(UpdateQuery);
		UpdateStatement.setInt(1, train.getID());
		UpdateStatement.setInt(2, wagon.getID());

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
