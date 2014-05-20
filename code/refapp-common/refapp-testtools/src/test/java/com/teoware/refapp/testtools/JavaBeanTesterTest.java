package com.teoware.refapp.testtools;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.teoware.refapp.testtools.JavaBeanTester;

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
