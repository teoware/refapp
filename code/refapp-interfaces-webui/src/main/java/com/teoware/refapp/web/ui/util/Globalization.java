package com.teoware.refapp.web.ui.util;

import java.util.HashMap;
import java.util.Map;

public class Globalization {

	public static final String spacer = " - ";

	private static Map<String, String> dict;

	static {
		dict = new HashMap<String, String>();
		dict.put("app.title", "RefApp");

		dict.put("page.users.title", "Users");
		dict.put("page.register_user.title", "Register user");
		dict.put("page.find_user.title", "Find user");
		dict.put("page.list_users.title", "List users");
		dict.put("page.change_user.title", "Change user");

		dict.put("page.notes.title", "Notes");

		dict.put("page.tasks.title", "Tasks");

		dict.put("page.common.footer", "By teoware");
	}

	public static String dict(String key) {
		return dict.get(key);
	}
}
