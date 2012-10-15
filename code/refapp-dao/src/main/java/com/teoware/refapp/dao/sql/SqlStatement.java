package com.teoware.refapp.dao.sql;

public class SqlStatement {

	private String sql;

	public SqlStatement(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public String build() {
	    return sql;
	}

	public class Builder {

	    private StringBuffer sql;

	    public Builder() {
	        sql = new StringBuffer();
	    }

	    public Builder doInsert(String tableName) {
	        sql.append("INSERT INTO " + tableName);
	        return this;
	    }

	    public Builder withColumns(String... columns) {
	        sql.append(" (" + join(columns, ", ") + ")");
	        return this;
	    }

	    private Builder withValues(int length) {
	        String values = "?";
	        for (int i = 1; i < length; i++) {
                values += ", ?";
            }
	        sql.append(" VALUES (" + values + ")");
	        return this;
	    }

	    public Builder doUpdate(String tableName) {
	        sql.append("UPDATE " + tableName);
	        return this;
	    }

	    public Builder setColumnsTo(String... columns) {
	        sql.append(" SET (" + join(append(columns, " = ?"), ", ") + ")");
	        return this;
	    }

	    public Builder doSelect(String... columns) {
	        sql.append("SELECT (" + join(columns, ", ") + ")");
	        return this;
	    }

	    public Builder from(String table) {
	        sql.append(" FROM " + table);
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

	    public Builder and(String column) {
	        sql.append(" AND " + column + " = ?");
	        return this;
	    }

	    public Builder and(String... column) {
	        sql.append(" AND (" + join(append(column, " = ?"), " AND ") + ")");
	        return this;
	    }

	    public Builder or(String column) {
	        sql.append(" OR " + column + " = ?");
	        return this;
	    }

	    public Builder or(String... column) {
	        sql.append(" OR (" + join(append(column, " = ?"), " OR ") + ")");
	        return this;
	    }

        private String[] append(String[] strings, String append) {
            for (int i = 0; i < strings.length; i++) {
                strings[i] = strings[1] + append;
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
	}
}
