package com.teoware.refapp.web.ui.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.beans.IntrospectionException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.testtools.JavaBeanTester;
import com.teoware.refapp.web.consumer.UserServiceConsumer;
import com.teoware.refapp.web.consumer.vo.FindUserResponseVO;
import com.teoware.refapp.web.consumer.vo.FindUserRequestVO;
import com.teoware.refapp.web.ui.util.Globalization;

public class FindUserControllerBeanTest {

    @InjectMocks
    FindUserControllerBean controller;

    @Mock
    protected Globalization globalization;

    @Mock
    private UserServiceConsumer consumer;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(globalization.dict(anyString())).thenReturn("RefApp");
    }

    @Test
    public void testGettersAndSetters() throws IntrospectionException {
        JavaBeanTester.test(FindUserControllerBean.class);
    }

    @Test
    public void testTitle() {
        assertNotNull(controller.getTitle());
        assertTrue(controller.getTitle().startsWith("RefApp"));
    }

    @Test
    public void testOnClickRegisterButtonCreatesUser() {
        Mockito.when(consumer.findUser(any(FindUserRequestVO.class))).thenReturn(new FindUserResponseVO(new User()));

        controller.onClickFindButton();

        verify(consumer).findUser(any(FindUserRequestVO.class));
    }
}
