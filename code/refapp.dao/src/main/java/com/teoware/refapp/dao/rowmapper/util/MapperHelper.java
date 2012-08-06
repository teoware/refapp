package com.teoware.refapp.dao.rowmapper.util;

import com.teoware.refapp.model.enums.Gender;

public class MapperHelper {

	public static Gender mapGender(String gender) {
		return Gender.valueOf(gender);
	}
}
