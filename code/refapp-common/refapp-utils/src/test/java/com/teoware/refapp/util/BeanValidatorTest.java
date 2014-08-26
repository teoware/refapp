package com.teoware.refapp.util;

import java.util.HashSet;
import java.util.Set;

import com.teoware.refapp.validation.BeanValidator;
import org.junit.Test;

import com.teoware.refapp.validation.BeanValidator.ValidationException;

public class BeanValidatorTest {

    @Test
    public void testNoError() throws IllegalArgumentException, IllegalAccessException {
        Bean bean = new Bean("heyhey", 0L, 1);
        Set<String> regex = new HashSet<String>();
        regex.add("^whatever.*");

        new BeanValidator().validate(bean, regex);
    }

    @Test(expected = ValidationException.class)
    public void testWithError() throws IllegalArgumentException, IllegalAccessException {
        Bean bean = new Bean("heyhey", 0L, 1);
        Set<String> regex = new HashSet<String>();
        regex.add("^hey.*");

        new BeanValidator().validate(bean, regex);
    }

    @Test
    public void test() {
        int a = 9;
        a(a);
    }

    public void a(Object o) {
        System.out.println(o.getClass().equals(Integer.class));
    }
}
