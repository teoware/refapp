package com.teoware.refapp.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BeanValidator {

	private List<Field> stringFields;

	public void validate(Object bean, Set<String> regex) throws IllegalArgumentException, IllegalAccessException {
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
			Class<?> type = field.getType();
			if (String.class.isAssignableFrom(type)) {
				stringFields.add(field);
			} else if (!isPrimitive(type) && !Collection.class.isAssignableFrom(type)) {

			}
		}
	}

	private boolean isPrimitive(Class<?> type) {
		return type.isPrimitive() || Boolean.class.equals(type) || Byte.class.equals(type) || Character.class.equals(type)
				|| Short.class.equals(type) || Integer.class.equals(type) || Long.class.equals(type)
				|| Float.class.equals(type) || Double.class.equals(type);
	}

	private void validateStringFields(Object object, List<Pattern> patterns) throws IllegalArgumentException,
			IllegalAccessException {
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
