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

public class JavaBeanTester {

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

						Object type = createType(returnType);

						T bean = clazz.newInstance();

						setter.invoke(bean, type);

						final Object expectedType = type;
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

	private static Object createType(Class<?> clazz) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, SecurityException, InvocationTargetException, DatatypeConfigurationException {

		// Specific rules for common classes
		if (clazz == String.class) {
			return "testvalue";

		} else if (clazz.isArray()) {
			return Array.newInstance(clazz.getComponentType(), 1);

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

		}

		// Add more types here

		else {

			final Object mockObjekt = createMockType(clazz);

			if (mockObjekt != null) {
				return mockObjekt;
			}

			final Constructor<?>[] ctrs = clazz.getConstructors();

			for (Constructor<?> ctr : ctrs) {

				if (ctr.getParameterTypes().length == 0) {
					return ctr.newInstance();
				}
			}
			fail("Could not create bean object of class " + clazz.getName() + ", please extend the "
					+ JavaBeanTester.class.getName() + " class to prevent this.");
			return null;
		}
	}

	private static Object createMockType(Class<?> clazz) {
		if (!Modifier.isFinal(clazz.getModifiers())) {
			return Mockito.mock(clazz);
		} else {
			System.out.println("Booboo");
			return null;
		}
	}

	public static <T> void findBooleanGetters(Class<T> clazz, PropertyDescriptor descriptor)
			throws IntrospectionException {
		if (erReadMetode(descriptor)) {
			finnReadMetoden(descriptor, clazz);
		}
	}

	private static boolean erReadMetode(PropertyDescriptor property) {
		return property.getReadMethod() == null && property.getPropertyType() == Boolean.class;
	}

	private static <T> void finnReadMetoden(PropertyDescriptor descriptor, Class<T> clazz) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(descriptor.getName(), clazz);
			descriptor.setReadMethod(pd.getReadMethod());
		} catch (IntrospectionException e) {
			// Ignore
		}
	}
}
