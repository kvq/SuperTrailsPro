package me.kvq.supertrailspro.database;

import java.sql.ResultSet;
import java.util.function.Consumer;

import pro.husk.SQLConsumer;

public class DBAction {
	
	private String query;
	private SQLConsumer<ResultSet> func;
	private int retries = 0;
	
	public DBAction(String query, SQLConsumer<ResultSet> func) {
		this.query = query; this.func = func;
	}

	public String getQuery() {
		return query;
	}

	public SQLConsumer<ResultSet> getFunc() {
		return func;
	}
	
	public int getRetries() {
		return this.retries;
	}
	
	public DBAction retry() {
		this.retries++;
		return this;
	}

}
