package com.teoware.refapp.service.validation;

import com.teoware.refapp.service.validation.group.ValidationGroup;

public class DummyValidationClass {

	public void nonValidatedMethod() {
	}

	@Validate
	public void validatedMethodWithoutParams() {
	}

	@Validate
	public void validatedMethodWithParamsButNoValidationGroup(String param) {
	}
	
	@Validate(DummyValidationGroup.class)
	public void validatedMethodWithParamsAndValidationGroup(String param) {
	}
	
	public interface DummyValidationGroup extends ValidationGroup {
	}
}
