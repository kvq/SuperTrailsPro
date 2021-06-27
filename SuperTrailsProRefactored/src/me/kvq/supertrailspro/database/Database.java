package me.kvq.supertrailspro.database;

import java.sql.SQLException;

public interface Database {

	public void execute(DBAction action) throws SQLException;
	
}
