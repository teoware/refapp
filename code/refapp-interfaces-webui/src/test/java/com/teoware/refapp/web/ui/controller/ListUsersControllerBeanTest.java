package com.teoware.refapp.web.ui.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.web.consumer.UserServiceConsumer;
import com.teoware.refapp.web.consumer.vo.ListUsersVO;
import com.teoware.refapp.web.ui.controller.ListUsersControllerBean;
import com.teoware.refapp.web.ui.util.Globalization;

public class ListUsersControllerBeanTest {

    @InjectMocks
    ListUsersControllerBean controller;

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
    public void testTitle() {
        assertNotNull(controller.getTitle());
        assertTrue(controller.getTitle().startsWith("RefApp"));
    }

    @Test
    public void testDoLoadUserList() {
        when(consumer.listUsers()).thenReturn(new ListUsersVO(new ArrayList<User>()));

        controller.doLoadUserList();

        verify(consumer).listUsers();

        assertNotNull(controller.getUserList());
        assertEquals(0, controller.getUserList().size());
    }
}
