package com.teoware.refapp.dao.sql;

public class SqlStatement {

	private String statement;

	public SqlStatement(String statement) {
		this.statement = statement;
	}

	public SqlStatement append(String append) {
		if (this.statement == null) {
			this.statement = "";
		}
		this.statement += append;
		return this;
	}

	public String build() {
		return statement;
	}
}
