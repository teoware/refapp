package com.teoware.refapp.web.consumer.vo;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.teoware.refapp.testtools.JavaBeanTester;

public class ConsumerVOTest {

    @Test
    public void testFindUserRequestVO() throws IntrospectionException {
        JavaBeanTester.test(FindUserRequestVO.class);
    }

    @Test
    public void testRegisterUserRequestVO() throws IntrospectionException {
        JavaBeanTester.test(RegisterUserRequestVO.class);
    }

    @Test
    public void testRegisterUserResponseVO() throws IntrospectionException {
        JavaBeanTester.test(RegisterUserResponseVO.class);
    }

    @Test
    public void testUserListVO() throws IntrospectionException {
        JavaBeanTester.test(ListUsersVO.class);
    }

    @Test
    public void testUserVO() throws IntrospectionException {
        JavaBeanTester.test(FindUserResponseVO.class);
    }
}
