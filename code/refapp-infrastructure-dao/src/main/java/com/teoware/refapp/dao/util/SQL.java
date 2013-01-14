package com.teoware.refapp.dao.util;

/**
 * Utility for building an SQL statement.
 * 
 */
public class SQL {

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

		public Builder columns(String... columns) {
			sql.append(" (" + join(columns, ", ") + ")");
			return this;
		}

		public Builder values(int length) {
			sql.append(" VALUES (" + count(length) + ")");
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
			sql.append(" SET " + column + " = ?");
			return this;
		}

		public Builder setColumns(String... columns) {
			sql.append(" SET " + join(append(columns, " = ?"), ", "));
			return this;
		}

		public Builder doSelect(String column) {
			sql.append("SELECT " + column);
			return this;
		}

		public Builder doSelect(String... columns) {
			sql.append("SELECT (" + join(columns, ", ") + ")");
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
			sql.append(" " + table);
			return this;
		}

		public Builder table(String table) {
			sql.append(" " + table);
			return this;
		}

		public Builder where() {
			sql.append(" WHERE");
			return this;
		}

		public Builder where(String column) {
			where();
			sql.append(" " + column + " = ?");
			return this;
		}

		public Builder where(String... column) {
			where();
			sql.append(" (" + join(append(column, " = ?"), " AND ") + ")");
			return this;
		}

		public Builder whereIn(String column, int count) {
			where();
			sql.append(" " + column);
			in(count);
			return this;
		}

		public Builder whereLike(String column) {
			where();
			sql.append(" " + column);
			like();
			return this;
		}

		public Builder in(int count) {
			sql.append(" IN (" + count(count) + ")");
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
			sql.append(" " + column + " = ?");
			return this;
		}

		public Builder and(String... column) {
			sql.append(" (" + join(append(column, " = ?"), " AND ") + ")");
			return this;
		}

		public Builder or() {
			sql.append(" OR");
			return this;
		}

		public Builder or(String column) {
			or();
			sql.append(" " + column + " = ?");
			return this;
		}

		public Builder or(String... column) {
			sql.append(" (" + join(append(column, " = ?"), " OR ") + ")");
			return this;
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

		private String count(int length) {
			String values = "?";
			for (int i = 1; i < length; i++) {
				values += ", ?";
			}
			return values;
		}

		public SQL build() {
			return new SQL(sql.toString());
		}
	}
}
