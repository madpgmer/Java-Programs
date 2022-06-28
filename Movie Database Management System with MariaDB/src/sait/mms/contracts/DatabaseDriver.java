package sait.mms.contracts;
/**
@author: Madhu Madhavan
**/

import java.sql.*;

public interface DatabaseDriver {
	
	void connect() throws SQLException;
	
	void disconnect() throws SQLException;
	
	/**
	 * Performs a retrieval from the database
	 * @param query Query to send to the DB
	 * @return Returns a resultset containing the results
	 * @throws SQLException Thrown if a problem happens
	 */
	ResultSet get(String query) throws SQLException;
	
	/**
	 * Performs an update query (update, delete, insert...)
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	int update(String query) throws SQLException;
	
}
