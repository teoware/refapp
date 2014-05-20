package com.teoware.refapp.service.validation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.teoware.refapp.service.validation.group.ValidationGroup;

@Documented
@Retention(RUNTIME)
@Target({PARAMETER, METHOD})
public @interface Validate {

    Class<? extends ValidationGroup> value() default ValidationGroup.class;
}
