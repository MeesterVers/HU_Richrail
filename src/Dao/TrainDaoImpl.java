package Dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import Model.Train;
import Model.Wagon;

public class TrainDaoImpl extends BaseDao implements TrainDao{
	private static Connection conn;
	private List<Train> trains = new ArrayList<Train>();
	
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
	
	public Boolean save(String name) throws SQLException {
		conn = BaseDao.getConnection();
		String query = "INSERT INTO trains (id, name) VALUES (TRAINS_SEQ.nextval, ?)";

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);

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
	public Train findTrain(String train) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(String train) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(String train) throws SQLException {
		conn = BaseDao.getConnection();
		String query = "DELETE FROM TRAINS WHERE ID = ?";

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, train.getID());
		
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
