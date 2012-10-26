package com.teoware.refapp.test;

import java.beans.IntrospectionException;

import org.junit.Test;

public class JavaBeanTesterTest {

	@Test
	public void testUsingTestBean() throws IntrospectionException {
		JavaBeanTester.test(TestBean.class);
	}

	@Test
	public void testUsingTestBeanWithSkipParams() throws IntrospectionException {
		JavaBeanTester.test(TestBean.class, "str", "int", "obj");
	}
}