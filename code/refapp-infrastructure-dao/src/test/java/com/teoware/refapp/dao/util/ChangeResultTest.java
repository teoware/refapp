package com.teoware.refapp.dao.util;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.teoware.refapp.testtools.JavaBeanTester;

public class ChangeResultTest {

	@Test
	public void testBean() throws IntrospectionException {
		JavaBeanTester.test(ChangeResult.class);
	}
}
