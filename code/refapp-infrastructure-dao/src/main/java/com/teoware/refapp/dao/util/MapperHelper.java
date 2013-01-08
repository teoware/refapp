package com.teoware.refapp.dao.util;

import org.joda.time.DateTime;

import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.util.time.DateTimeConverter;

public class MapperHelper {

	public static Gender mapGender(String gender) {
		return Gender.valueOf(gender);
	}

	public static Status mapStatus(String status) {
		return Status.valueOf(status);
	}

	public static DateTime mapDate(java.sql.Date date) {
		return DateTimeConverter.fromSqlDate(date);
	}

	public static DateTime mapTime(java.sql.Time time) {
		return DateTimeConverter.fromSqlTime(time);
	}

	public static DateTime mapTimestamp(java.sql.Timestamp timestamp) {
		return DateTimeConverter.fromSqlTimestamp(timestamp);
	}
}
