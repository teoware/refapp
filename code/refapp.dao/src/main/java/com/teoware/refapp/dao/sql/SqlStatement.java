package com.teoware.refapp.dao.sql;

public class SqlStatement {

	private String statement;

	public SqlStatement(String statement) {
		this.statement = statement;
	}

	public void appendStatement(String append) {
		statement += append;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
}
