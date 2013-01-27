package com.teoware.refapp.dao.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SQLTest {

	@Test
	public void testAppend() {
		SQL sql = new SQL("whatever");
		sql.append(" you say");

		assertEquals("whatever you say", sql.getSql());
	}

	@Test
	public void testInsertOneColumnSqlBuilder() {
		SQL sql = new SQL.Builder().doInsert("TABLE").columnValues("COLUMN").build();
		String expectedSql = "INSERT INTO TABLE (COLUMN) VALUES (?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testInsertThreeColumnsSqlBuilder() {
		SQL sql = new SQL.Builder().doInsert("TABLE").columnValues("COLUMN1", "COLUMN2", "COLUMN3").build();
		String expectedSql = "INSERT INTO TABLE (COLUMN1, COLUMN2, COLUMN3) VALUES (?, ?, ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testInsertThreeValuesSqlBuilder() {
		SQL sql = new SQL.Builder().doInsert("TABLE").values(3).build();
		String expectedSql = "INSERT INTO TABLE VALUES (?, ?, ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateOneColumnSqlBuilder() {
		SQL sql = new SQL.Builder().doUpdate("TABLE").setColumn("COLUMN").build();
		String expectedSql = "UPDATE TABLE SET COLUMN = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateThreeColumnSqlBuilder() {
		SQL sql = new SQL.Builder().doUpdate("TABLE").setColumns("COLUMN1", "COLUMN2", "COLUMN3").build();
		String expectedSql = "UPDATE TABLE SET COLUMN1 = ?, COLUMN2 = ?, COLUMN3 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateThreeColumnWhereOneSqlBuilder() {
		SQL sql = new SQL.Builder().doUpdate("TABLE").setColumns("COLUMN1", "COLUMN2", "COLUMN3").where("COLUMN4")
				.build();
		String expectedSql = "UPDATE TABLE SET COLUMN1 = ?, COLUMN2 = ?, COLUMN3 = ? WHERE COLUMN4 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testUpdateThreeColumnWhereThreeSqlBuilder() {
		SQL sql = new SQL.Builder().doUpdate("TABLE").setColumns("COLUMN1", "COLUMN2", "COLUMN3")
				.where("COLUMN4", "COLUMN5", "COLUMN6").build();
		String expectedSql = "UPDATE TABLE SET COLUMN1 = ?, COLUMN2 = ?, COLUMN3 = ? "
				+ "WHERE (COLUMN4 = ? AND COLUMN5 = ? AND COLUMN6 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllFromSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("*").from("TABLE").build();
		String expectedSql = "SELECT * FROM TABLE";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllFromTableSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("*").from().table("TABLE").build();
		String expectedSql = "SELECT * FROM TABLE";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllFromWhereOneSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("*").from("TABLE").where("COLUMN").build();
		String expectedSql = "SELECT * FROM TABLE WHERE COLUMN = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereTwoSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where("COLUMN4", "COLUMN5")
				.build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE (COLUMN4 = ? AND COLUMN5 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereAndTwoSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where()
				.and("COLUMN4", "COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE (COLUMN4 = ? AND COLUMN5 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereAndSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where("COLUMN4")
				.and("COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE COLUMN4 = ? AND COLUMN5 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereTwoAndSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where("COLUMN4")
				.and("COLUMN5").and("COLUMN6").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE "
				+ "WHERE COLUMN4 = ? AND COLUMN5 = ? AND COLUMN6 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereOrTwoSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where()
				.or("COLUMN4", "COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE (COLUMN4 = ? OR COLUMN5 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereOrSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where("COLUMN4")
				.or("COLUMN5").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE WHERE COLUMN4 = ? OR COLUMN5 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereTwoOrSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where("COLUMN4")
				.or("COLUMN5").or("COLUMN6").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE "
				+ "WHERE COLUMN4 = ? OR COLUMN5 = ? OR COLUMN6 = ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectThreeColumnsFromWhereOrTwoAndAndThreeSqlBuilder() {
		SQL sql = new SQL.Builder().doSelect("COLUMN1", "COLUMN2", "COLUMN3").from("TABLE").where()
				.or("COLUMN4", "COLUMN5").and().and("COLUMN6", "COLUMN7", "COLUMN8").build();
		String expectedSql = "SELECT (COLUMN1, COLUMN2, COLUMN3) FROM TABLE "
				+ "WHERE (COLUMN4 = ? OR COLUMN5 = ?) AND (COLUMN6 = ? AND COLUMN7 = ? AND COLUMN8 = ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllWhereInSqlBuilder() {
		SQL sql = new SQL.Builder().doSelectAll().from("TABLE").whereIn("COLUMN", 2).build();
		String expectedSql = "SELECT * FROM TABLE WHERE COLUMN IN (?, ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllWhereLikeSqlBuilder() {
		SQL sql = new SQL.Builder().doSelectAll().from("TABLE").whereLike("COLUMN").build();
		String expectedSql = "SELECT * FROM TABLE WHERE COLUMN LIKE ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllWhereColumnInSqlBuilder() {
		SQL sql = new SQL.Builder().doSelectAll().from("TABLE").where().column("COLUMN").in(2).build();
		String expectedSql = "SELECT * FROM TABLE WHERE COLUMN IN (?, ?)";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testSelectAllWhereColumnLikeSqlBuilder() {
		SQL sql = new SQL.Builder().doSelectAll().from("TABLE").where().column("COLUMN").like().build();
		String expectedSql = "SELECT * FROM TABLE WHERE COLUMN LIKE ?";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testDeleteSqlBuilder() {
		SQL sql = new SQL.Builder().doDelete("TABLE").build();
		String expectedSql = "DELETE FROM TABLE";

		assertEquals(expectedSql, sql.getSql());
	}

	@Test
	public void testDeleteWhereSqlBuilder() {
		SQL sql = new SQL.Builder().doDelete("TABLE").where("COLUMN").build();
		String expectedSql = "DELETE FROM TABLE WHERE COLUMN = ?";

		assertEquals(expectedSql, sql.getSql());
	}
}
