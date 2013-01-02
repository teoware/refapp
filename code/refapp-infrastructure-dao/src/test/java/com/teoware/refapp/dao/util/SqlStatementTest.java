package com.teoware.refapp.dao.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.teoware.refapp.dao.util.SqlStatement;

public class SqlStatementTest {

	@Test
	public void testInsertOneColumnSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doInsert("TABLE").columnValues("COLUMN").build();
		String expectedSql = "INSERT INTO TABLE (COLUMN) VALUES (?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testInsertThreeColumnsSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doInsert("TABLE").columnValues("COLUMN1", "COLUMN2", "COLUMN3")
				.build();
		String expectedSql = "INSERT INTO TABLE (COLUMN1, COLUMN2, COLUMN3) VALUES (?, ?, ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testInsertThreeValuesSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doInsert("TABLE").values(3).build();
		String expectedSql = "INSERT INTO TABLE VALUES (?, ?, ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateOneColumnSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doUpdate("TABLE").setColumn("COLUMN").build();
		String expectedSql = "UPDATE TABLE SET COLUMN = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateThreeColumnSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doUpdate("TABLE").setColumns("COLUMN1", "COLUMN2", "COLUMN3")
				.build();
		String expectedSql = "UPDATE TABLE SET COLUMN1 = ?, COLUMN2 = ?, COLUMN3 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateThreeColumnWhereOneSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doUpdate("TABLE").setColumns("COLUMN1", "COLUMN2", "COLUMN3")
				.where("COLUMN4").build();
		String expectedSql = "UPDATE TABLE SET COLUMN1 = ?, COLUMN2 = ?, COLUMN3 = ? WHERE COLUMN4 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateThreeColumnWhereThreeSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doUpdate("TABLE").setColumns("COLUMN1", "COLUMN2", "COLUMN3")
				.where("COLUMN4", "COLUMN5", "COLUMN6").build();
		String expectedSql = "UPDATE TABLE SET COLUMN1 = ?, COLUMN2 = ?, COLUMN3 = ? "
				+ "WHERE (COLUMN4 = ? AND COLUMN5 = ? AND COLUMN6 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllFromSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doSelect("*").from("TABLE").build();
		String expectedSql = "SELECT * FROM TABLE";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllFromWhereOneSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doSelect("*").from("TABLE").where("COLUMN").build();
		String expectedSql = "SELECT * FROM TABLE WHERE COLUMN = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereTwoSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE")
				.where("COLUMN4", "COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE (COLUMN4 = ? AND COLUMN5 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereAndTwoSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where()
				.and("COLUMN4", "COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE (COLUMN4 = ? AND COLUMN5 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereAndSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE")
				.where("COLUMN4").and("COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE COLUMN4 = ? AND COLUMN5 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereTwoAndSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE")
				.where("COLUMN4").and("COLUMN5").and("COLUMN6").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE "
				+ "WHERE COLUMN4 = ? AND COLUMN5 = ? AND COLUMN6 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereOrTwoSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where()
				.or("COLUMN4", "COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE (COLUMN4 = ? OR COLUMN5 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereOrSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE")
				.where("COLUMN4").or("COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE COLUMN4 = ? OR COLUMN5 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereTwoOrSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE")
				.where("COLUMN4").or("COLUMN5").or("COLUMN6").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE "
				+ "WHERE COLUMN4 = ? OR COLUMN5 = ? OR COLUMN6 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereOrTwoAndAndThreeSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where()
				.or("COLUMN4", "COLUMN5").and().and("COLUMN6", "COLUMN7", "COLUMN8").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE "
				+ "WHERE (COLUMN4 = ? OR COLUMN5 = ?) AND (COLUMN6 = ? AND COLUMN7 = ? AND COLUMN8 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testDeleteSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doDelete("TABLE").build();
		String expectedSql = "DELETE FROM TABLE";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testDeleteWhereSqlStatementBuilder() {
		SqlStatement sql = new SqlStatement.Builder().doDelete("TABLE").where("COLUMN").build();
		String expectedSql = "DELETE FROM TABLE WHERE COLUMN = ?";

		assertEquals(expectedSql, sql.getSql());
	}
}
