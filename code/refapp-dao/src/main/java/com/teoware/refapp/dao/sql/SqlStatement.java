package com.teoware.refapp.dao.sql;

public class SqlStatement {

	private String sql;

	public SqlStatement(String sql) {
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
			String values = "?";
			for (int i = 1; i < length; i++) {
				values += ", ?";
			}
			sql.append(" VALUES (" + values + ")");
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

		public Builder doDelete(String tableName) {
			sql.append("DELETE FROM " + tableName);
			return this;
		}

		public Builder from() {
			sql.append(" FROM");
			return this;
		}

		public Builder from(String table) {
			sql.append(" FROM " + table);
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
			sql.append(" WHERE " + column + " = ?");
			return this;
		}

		public Builder where(String... column) {
			sql.append(" WHERE (" + join(append(column, " = ?"), " AND ") + ")");
			return this;
		}

		public Builder and() {
			sql.append(" AND");
			return this;
		}

		public Builder and(String column) {
			sql.append(" AND " + column + " = ?");
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
			sql.append(" OR " + column + " = ?");
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

		public SqlStatement build() {
			return new SqlStatement(sql.toString());
		}
	}
}
