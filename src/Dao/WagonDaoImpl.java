package Dao;

import java.sql.*;
import java.util.*;

import Model.Wagon;

public class WagonDaoImpl extends BaseDao implements WagonDao{
	private static Connection conn;
	private List<Wagon> wagons = new ArrayList<Wagon>();

	@Override
	public List<Wagon> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Wagon> findTrain() throws SQLException {
		// TODO Auto-generated method stub
		return null;
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

}
