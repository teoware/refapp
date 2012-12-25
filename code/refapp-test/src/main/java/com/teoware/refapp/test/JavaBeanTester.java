package com.teoware.refapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.xml.datatype.DatatypeConfigurationException;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaBeanTester {

	private static final Logger LOG = LoggerFactory.getLogger(JavaBeanTester.class);

	public static <T> void test(final Class<T> clazz, final String... skipThese) throws IntrospectionException {
		final PropertyDescriptor[] props = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
		next: for (PropertyDescriptor prop : props) {

			for (String skipThis : skipThese) {
				if (skipThis.equals(prop.getName())) {
					continue next;
				}
			}

			findBooleanGetters(clazz, prop);

			final Method getter = prop.getReadMethod();
			final Method setter = prop.getWriteMethod();

			if (getter != null && setter != null) {

				final Class<?> returnType = getter.getReturnType();
				final Class<?>[] params = setter.getParameterTypes();

				if (params.length == 1 && params[0] == returnType) {

					try {

						final Object expectedType = createType(returnType);

						T bean = createBean(clazz);

						setter.invoke(bean, expectedType);

						final Object actualType = getter.invoke(bean);

						assertEquals(String.format("Failed when testing types %s", prop.getName()), expectedType,
								actualType);

					} catch (Exception e) {
						e.printStackTrace();
						fail(String.format("An exception was thrown during bean test %s: %s", prop.getName(),
								e.toString()));
					}
				}
			}
		}
	}

	public static <T> void test(final Class<T> clazz) throws IntrospectionException {
		test(clazz, new String[0]);
	}

	@SuppressWarnings("unchecked")
	private static <T> T createBean(final Class<T> clazz) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException {

		final Constructor<?> constructor = getConstructor(clazz);

		int minParams = constructor.getParameterTypes().length;
		if (minParams == 0) {

			return (T) constructor.newInstance();

		} else {
			return (T) constructor.newInstance(getNullParams(minParams));
		}
	}

	private static Constructor<?> getConstructor(final Class<?> clazz) {
		Constructor<?>[] constructors = clazz.getConstructors();

		int minParams = Integer.MAX_VALUE;
		int index = 0;

		for (int i = 0; i < constructors.length; i++) {
			int params = constructors[i].getParameterTypes().length;
			if (params < minParams) {
				minParams = params;
				index = i;
			}
		}

		return constructors[index];
	}

	private static Object[] getNullParams(int size) {
		Object[] obj = new Object[size];
		return obj;
	}

	private static Object createType(Class<?> clazz) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, SecurityException, InvocationTargetException, DatatypeConfigurationException {

		Object object = createBasicType(clazz);

		if (object != null) {
			return object;
		}

		object = createMockType(clazz);

		if (object != null) {
			return object;
		}

		object = createObjectType(clazz);

		if (object != null) {
			return object;
		}

		fail("Could not create bean object of class " + clazz.getName() + ", please extend the "
				+ JavaBeanTester.class.getName() + " class to prevent this.");
		return null;
	}

	private static Object createBasicType(Class<?> clazz) {
		if (clazz == String.class) {
			return "testvalue";
		} else if (clazz == boolean.class || clazz == Boolean.class) {
			return true;
		} else if (clazz == int.class || clazz == Integer.class) {
			return 1;
		} else if (clazz == long.class || clazz == Long.class) {
			return 1L;
		} else if (clazz == double.class || clazz == Double.class) {
			return 1.0D;
		} else if (clazz == float.class || clazz == Float.class) {
			return 1.0F;
		} else if (clazz == char.class || clazz == Character.class) {
			return 'Y';
		} else if (clazz.isArray()) {
			return Array.newInstance(clazz.getComponentType(), 1);
		} else {
			return null;
		}
	}

	private static Object createMockType(Class<?> clazz) {
		if (!Modifier.isFinal(clazz.getModifiers())) {
			return Mockito.mock(clazz);
		} else {
			return null;
		}
	}

	private static Object createObjectType(Class<?> clazz) throws IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		final Constructor<?>[] constructors = clazz.getConstructors();

		for (Constructor<?> constructor : constructors) {

			if (constructor.getParameterTypes().length == 0) {
				return constructor.newInstance();
			}
		}
		return null;
	}

	public static <T> void findBooleanGetters(Class<T> clazz, PropertyDescriptor descriptor)
			throws IntrospectionException {
		if (isReadMetod(descriptor)) {
			findReadMetod(descriptor, clazz);
		}
	}

	private static boolean isReadMetod(PropertyDescriptor property) {
		return property.getReadMethod() == null && property.getPropertyType() == Boolean.class;
	}

	private static <T> void findReadMetod(PropertyDescriptor descriptor, Class<T> clazz) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(descriptor.getName(), clazz);
			descriptor.setReadMethod(pd.getReadMethod());
		} catch (IntrospectionException e) {
			// Ignore
		}
	}
}
