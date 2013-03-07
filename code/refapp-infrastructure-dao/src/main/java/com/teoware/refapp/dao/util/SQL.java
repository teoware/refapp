package com.teoware.refapp.dao.util;

/**
 * Utility for building SQL statements.
 * 
 * @author thomas@teoware.com
 */
public class SQL {
	
private static final String COMMA = ", ";
	private static final String PARAM_EQUALS = " = ?";

	private String sql;

	public SQL(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public void append(String append) {
		sql += append;
	}

	public static class Builder {

		private StringBuilder sql;

		public Builder() {
			sql = new StringBuilder();
		}

		public Builder doInsert(String tableName) {
			sql.append("INSERT INTO " + tableName);
			return this;
		}

		public Builder column(String column) {
			space();
			sql.append(column);
			return this;
		}

		public Builder columns(String... columns) {
			left();
			sql.append(join(columns, COMMA));
			right();
			return this;
		}

		public Builder values(int length) {
			sql.append(" VALUES (" + params(length));
			right();
			return this;
		}

		public Builder columnValues(String... columns) {
			columns(columns);
			values(columns.length);
			return this;
		}

		public Builder doUpdate(String tableName) {
			sql.append("UPDATE " + tableName);
			return this;
		}

		public Builder setColumn(String column) {
			sql.append(" SET " + column);
			param();
			return this;
		}

		public Builder setColumns(String... columns) {
			sql.append(" SET " + join(append(columns, PARAM_EQUALS), COMMA));
			return this;
		}

		public Builder doSelect(String column) {
			sql.append("SELECT " + column);
			return this;
		}

		public Builder doSelect(String... columns) {
			sql.append("SELECT (" + join(columns, COMMA));
			right();
			return this;
		}

		public Builder doSelectAll() {
			sql.append("SELECT *");
			return this;
		}

		public Builder doDelete(String tableName) {
			sql.append("DELETE FROM " + tableName);
			return this;
		}

		public Builder from() {
			sql.append(" FROM");
			return this;
		}

		public Builder from(String table) {
			from();
			space();
			sql.append(table);
			return this;
		}

		public Builder table(String table) {
			space();
			sql.append(table);
			return this;
		}

		public Builder where() {
			sql.append(" WHERE");
			return this;
		}

		public Builder where(String column) {
			where();
			space();
			sql.append(column);
			param();
			return this;
		}

		public Builder where(String... column) {
			where();
			left();
			sql.append(join(append(column, PARAM_EQUALS), " AND "));
			right();
			return this;
		}

		public Builder whereIn(String column, int count) {
			where();
			space();
			sql.append(column);
			in(count);
			return this;
		}

		public Builder whereLike(String column) {
			where();
			space();
			sql.append(column);
			like();
			return this;
		}

		public Builder in(int count) {
			sql.append(" IN (" + params(count));
			right();
			return this;
		}

		public Builder like() {
			sql.append(" LIKE ?");
			return this;
		}

		public Builder and() {
			sql.append(" AND");
			return this;
		}

		public Builder and(String column) {
			and();
			space();
			sql.append(column);
			param();
			return this;
		}

		public Builder and(String... column) {
			left();
			sql.append(join(append(column, PARAM_EQUALS), " AND "));
			right();
			return this;
		}

		public Builder or() {
			sql.append(" OR");
			return this;
		}

		public Builder or(String column) {
			or();
			space();
			sql.append(column);
			param();
			return this;
		}

		public Builder or(String... column) {
			left();
			sql.append(join(append(column, PARAM_EQUALS), " OR "));
			right();
			return this;
		}
		
		private void space() {
			sql.append(" ");
		}
		
		private void param() {
			sql.append(PARAM_EQUALS);
		}
		
		private void left() {
			sql.append(" (");
		}
		
		private void right() {
			sql.append(")");
		}

		private String[] append(String[] strings, String append) {
			for (int i = 0; i < strings.length; i++) {
				strings[i] = strings[i] + append;
			}
			return strings;
		}

		private String join(String[] strings, String join) {
			int i = 0;
			String string = strings[i++];
			for (; i < strings.length; i++) {
				string += join + strings[i];
			}
			return string;
		}

		private String params(int length) {
			String params = "?";
			for (int i = 1; i < length; i++) {
				params += ", ?";
			}
			return params;
		}

		public SQL build() {
			return new SQL(sql.toString());
		}
	}
}
