package com.teoware.refapp.dao.rowmapper.util;

import java.util.Calendar;
import java.util.Date;

import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.enums.Gender;

public class MapperHelper {

	public static Gender mapGender(String gender) {
		return Gender.valueOf(gender);
	}

	public static AuthorStatus mapAuthorStatus(String status) {
		return AuthorStatus.valueOf(status);
	}

	public static Date mapDate(java.sql.Date date) {
		return new Date(date.getTime());
	}

	public static Calendar mapCalendar(java.sql.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mapDate(date));
		return calendar;
	}

	public static Calendar mapCalendar(java.sql.Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timestamp);
		return calendar;
	}
}
