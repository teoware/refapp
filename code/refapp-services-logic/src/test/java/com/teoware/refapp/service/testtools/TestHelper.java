package com.teoware.refapp.service.testtools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.service.dto.RegisterUserRequest;

public class TestHelper {

	public static RegisterUserRequest populateRegisterUserRequest() {
		return populateRegisterUserRequest(createRegisterUserRequestBean());
	}

	public static RegisterUserRequest populateRegisterUserRequest(RegisterUserRequest request) {
		if (request.getBody() != null) {
			if (request.getBody().getUserId() != null) {
				request.getBody().getUserId().setUserName("johndoe");
			}
			if (request.getBody().getUserInfo() != null) {
				request.getBody().getUserInfo().setFirstName("John");
				request.getBody().getUserInfo().setLastName("Doe");
				request.getBody().getUserInfo().setBirthDate(createDate("01-01-1970"));
				request.getBody().getUserInfo().setGender(Gender.MALE);
				request.getBody().getUserInfo().setEmail("john.doe@mail.com");
				request.getBody().getUserInfo().setPhone("+123456789");
			}
			if (request.getBody().getUserAddress() != null) {
				request.getBody().getUserAddress().setAddress("Mystreet 1");
				request.getBody().getUserAddress().setPostalCode("1234");
				request.getBody().getUserAddress().setMunicipality("Mytown");
				request.getBody().getUserAddress().setRegion("Mycounty");
				request.getBody().getUserAddress().setCountry("Mycountry");
			}
		}
		return request;
	}

	public static RegisterUserRequest createRegisterUserRequestBean() {
		User user = BeanFactory.createUserBean();
		UserPassword userPassword = BeanFactory.createUserPasswordBean();
		return new RegisterUserRequest(user, userPassword);
	}

	public static Date createDate(String dateString) {
		return createDate(dateString, "dd-MM-yyyy");
	}

	public static Date createDate(String dateString, String dateFormat) {
		try {
			return new SimpleDateFormat(dateFormat).parse(dateString);
		} catch (ParseException e) {
			return new Date();
		}
	}
}
