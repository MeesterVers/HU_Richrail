package Dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import Model.Train;

public class TrainDaoImpl extends BaseDao implements TrainDao{
	private static Connection conn;
	private List<Train> trains = new ArrayList<Train>();
	
	public Boolean save(Train train) throws SQLException {
		conn = BaseDao.getConnection();
		String query = "INSERT INTO trains (id, name) VALUES (TRAINS_SEQ.nextval, ?)";

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, train.getName());

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
	public List<Train> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Train> findTrain() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Train train) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Train train) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
