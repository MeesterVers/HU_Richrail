package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Train;

public class TrainDaoImpl implements TrainDao {
	private static Connection conn;

	public List<Train> findAll() throws SQLException {
		List<Train> trains = new ArrayList<Train>();
		WagonDaoImpl wagonDaoImpl = new WagonDaoImpl();
		conn = BaseDao.getConnection();

		String query = "SELECT * FROM trains ORDER BY ID DESC";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);

		while (result.next()) {
			int ID = result.getInt("id");
			String name = result.getString("name");

			Train train = new Train(ID, name);
			trains.add(train);
			train.setWagons(wagonDaoImpl.findWagonByTrainID(ID));
		}

		conn.close();
		result.close();
		return trains;
	}

	public Boolean save(String trainName) throws SQLException {
		conn = BaseDao.getConnection();
		String query = "INSERT INTO trains (id, name) VALUES (TRAINS_SEQ.nextval, ?)";

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, trainName);

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("SUCCESS");
			conn.close();
			return true;
		} else {
			System.out.println("NO SUCCESS");
			conn.close();
			return false;
		}
	}

	public Train findTrainByName(String trainName) throws SQLException {
		conn = BaseDao.getConnection();
		WagonDao wagonDaoImpl = new WagonDaoImpl();
		Train foundTrain = null;

		String query = "SELECT * FROM TRAINS WHERE name = ?";

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, trainName);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			int ID = result.getInt("id");
			String name = result.getString("name");

			foundTrain = new Train(ID, name);
			foundTrain.setWagons(wagonDaoImpl.findWagonByTrainID(ID));
		}

		conn.close();
		result.close();
		return foundTrain;
	}

	public Boolean delete(String trainName) throws SQLException {
		conn = BaseDao.getConnection();
		String query = "DELETE FROM TRAINS WHERE NAME = ?";

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, trainName);

		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("train deleted");
			conn.close();
			return true;
		} else {
			System.out.println("train not deleted");
			conn.close();
			return false;
		}
	}

}
