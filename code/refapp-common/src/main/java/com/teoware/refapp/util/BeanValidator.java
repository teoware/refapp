package com.teoware.refapp.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BeanValidator {

	private List<Field> stringFields;

	public void validate(Object bean, Set<String> regex) throws IllegalArgumentException, IllegalAccessException,
			ValidationException {
		if (bean == null || regex == null || regex.size() == 0) {
			return;
		}

		stringFields = new ArrayList<Field>();
		findStringFields(bean.getClass().getDeclaredFields());

		if (stringFields.size() > 0) {
			List<Pattern> patterns = generatePatterns(regex);
			validateStringFields(bean, patterns);
		}
	}

	private void findStringFields(Field[] fields) {
		for (Field field : fields) {
			if (String.class.isAssignableFrom(field.getType())) {
				stringFields.add(field);
			}
		}
	}

	private void validateStringFields(Object object, List<Pattern> patterns) throws IllegalArgumentException,
			IllegalAccessException, ValidationException {
		for (Field field : stringFields) {
			if (!field.isAccessible()) {
				field.setAccessible(Boolean.TRUE);
			}
			Object value = field.get(object);
			if (value == null) {
				return;
			} else {
				for (Pattern pattern : patterns) {
					Matcher matcher = pattern.matcher(value.toString());
					if (matcher.matches()) {
						throw new ValidationException("Validation error for field \"" + field.getName()
								+ "\" when applied to regex \"" + pattern.pattern() + "\"");
					}
				}
			}
		}
	}

	private List<Pattern> generatePatterns(Set<String> validationRegex) {
		List<Pattern> patterns = new ArrayList<Pattern>();
		for (String regex : validationRegex) {
			System.out.println(regex);
			Pattern pattern = Pattern.compile(regex);
			patterns.add(pattern);
		}
		return patterns;
	}

	public class ValidationException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public ValidationException() {
			super();
		}

		public ValidationException(String message, Throwable cause) {
			super(message, cause);
		}

		public ValidationException(String message) {
			super(message);
		}

		public ValidationException(Throwable cause) {
			super(cause);
		}
	}
}
