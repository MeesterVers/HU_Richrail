package Contollers;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ControllerInterface {
	public String executeCommand(String command) throws SQLException;

	public int getIndexFunction(String command);

	public ArrayList<String> getParameters(String command, int index);
}
