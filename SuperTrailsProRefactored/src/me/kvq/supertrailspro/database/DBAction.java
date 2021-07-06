package me.kvq.supertrailspro.database;

import java.sql.ResultSet;
import java.util.function.Consumer;

import me.kvq.supertrailspro.database.STDB.DatabaseWorker;
import me.kvq.supertrailspro.utils.STUtils;
import pro.husk.SQLConsumer;

public class DBAction {
	
	private String query;
	private SQLConsumer<ResultSet> func;
	private int retries = 0;
	private DBRequestType requestType;
	
	public DBAction(String query) {
		this.query = query; requestType = DBRequestType.UPDATE;
	}
	
	public DBAction(String query, SQLConsumer<ResultSet> func) {
		this.query = query; this.func = func; requestType = DBRequestType.QUERY;
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
	
	public boolean retry(DatabaseWorker worker) {
		this.retries++;
		if (retries >= STUtils.DATABASE_RETRIES_LIMIT) return false;
		worker.addQueue(this);
		return true;
	}

	public DBRequestType getType() {
		return requestType;
	}
	
}

