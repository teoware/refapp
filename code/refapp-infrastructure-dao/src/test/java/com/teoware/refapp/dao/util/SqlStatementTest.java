package com.teoware.refapp.dao.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.teoware.refapp.dao.util.SQL;

public class SqlStatementTest {

	@Test
	public void testInsertOneColumnSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doInsert("TABLE").columnValues("COLUMN").build();
		String expectedSql = "INSERT INTO TABLE (COLUMN) VALUES (?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testInsertThreeColumnsSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doInsert("TABLE").columnValues("COLUMN1", "COLUMN2", "COLUMN3")
				.build();
		String expectedSql = "INSERT INTO TABLE (COLUMN1, COLUMN2, COLUMN3) VALUES (?, ?, ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testInsertThreeValuesSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doInsert("TABLE").values(3).build();
		String expectedSql = "INSERT INTO TABLE VALUES (?, ?, ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateOneColumnSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doUpdate("TABLE").setColumn("COLUMN").build();
		String expectedSql = "UPDATE TABLE SET COLUMN = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateThreeColumnSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doUpdate("TABLE").setColumns("COLUMN1", "COLUMN2", "COLUMN3")
				.build();
		String expectedSql = "UPDATE TABLE SET COLUMN1 = ?, COLUMN2 = ?, COLUMN3 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateThreeColumnWhereOneSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doUpdate("TABLE").setColumns("COLUMN1", "COLUMN2", "COLUMN3")
				.where("COLUMN4").build();
		String expectedSql = "UPDATE TABLE SET COLUMN1 = ?, COLUMN2 = ?, COLUMN3 = ? WHERE COLUMN4 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateThreeColumnWhereThreeSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doUpdate("TABLE").setColumns("COLUMN1", "COLUMN2", "COLUMN3")
				.where("COLUMN4", "COLUMN5", "COLUMN6").build();
		String expectedSql = "UPDATE TABLE SET COLUMN1 = ?, COLUMN2 = ?, COLUMN3 = ? "
				+ "WHERE (COLUMN4 = ? AND COLUMN5 = ? AND COLUMN6 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllFromSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doSelect("*").from("TABLE").build();
		String expectedSql = "SELECT * FROM TABLE";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllFromWhereOneSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doSelect("*").from("TABLE").where("COLUMN").build();
		String expectedSql = "SELECT * FROM TABLE WHERE COLUMN = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereTwoSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE")
				.where("COLUMN4", "COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE (COLUMN4 = ? AND COLUMN5 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereAndTwoSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where()
				.and("COLUMN4", "COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE (COLUMN4 = ? AND COLUMN5 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereAndSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE")
				.where("COLUMN4").and("COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE COLUMN4 = ? AND COLUMN5 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereTwoAndSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE")
				.where("COLUMN4").and("COLUMN5").and("COLUMN6").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE "
				+ "WHERE COLUMN4 = ? AND COLUMN5 = ? AND COLUMN6 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereOrTwoSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where()
				.or("COLUMN4", "COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE (COLUMN4 = ? OR COLUMN5 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereOrSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE")
				.where("COLUMN4").or("COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE COLUMN4 = ? OR COLUMN5 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereTwoOrSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE")
				.where("COLUMN4").or("COLUMN5").or("COLUMN6").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE "
				+ "WHERE COLUMN4 = ? OR COLUMN5 = ? OR COLUMN6 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereOrTwoAndAndThreeSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where()
				.or("COLUMN4", "COLUMN5").and().and("COLUMN6", "COLUMN7", "COLUMN8").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE "
				+ "WHERE (COLUMN4 = ? OR COLUMN5 = ?) AND (COLUMN6 = ? AND COLUMN7 = ? AND COLUMN8 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testDeleteSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doDelete("TABLE").build();
		String expectedSql = "DELETE FROM TABLE";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testDeleteWhereSqlStatementBuilder() {
		SQL sql = new SQL.Builder().doDelete("TABLE").where("COLUMN").build();
		String expectedSql = "DELETE FROM TABLE WHERE COLUMN = ?";

		assertEquals(expectedSql, sql.getSql());
	}
}
