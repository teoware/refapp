package com.teoware.refapp.service.testtools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;

public class TestHelper {

	public static RegisterAuthorRequest populateRegisterAuthorRequest() {
		return populateRegisterAuthorRequest(createRegisterAuthorRequestBean());
	}

	public static RegisterAuthorRequest populateRegisterAuthorRequest(RegisterAuthorRequest request) {
		if (request.getBody() != null) {
			if (request.getBody().getAuthorId() != null) {
				request.getBody().getAuthorId().setUserName("johndoe");
			}
			if (request.getBody().getAuthorInfo() != null) {
				request.getBody().getAuthorInfo().setFirstName("John");
				request.getBody().getAuthorInfo().setLastName("Doe");
				request.getBody().getAuthorInfo().setBirthDate(createDate("01-01-1970"));
				request.getBody().getAuthorInfo().setGender(Gender.MALE);
				request.getBody().getAuthorInfo().setEmail("john.doe@mail.com");
				request.getBody().getAuthorInfo().setPhone("+123456789");
			}
			if (request.getBody().getAuthorAddress() != null) {
				request.getBody().getAuthorAddress().setAddress("Mystreet 1");
				request.getBody().getAuthorAddress().setPostalCode("1234");
				request.getBody().getAuthorAddress().setMunicipality("Mytown");
				request.getBody().getAuthorAddress().setRegion("Mycounty");
				request.getBody().getAuthorAddress().setCountry("Mycountry");
			}
		}
		return request;
	}

	public static RegisterAuthorRequest createRegisterAuthorRequestBean() {
		Author author = BeanFactory.createAuthorBean();
		AuthorPassword authorPassword = BeanFactory.createAuthorPasswordBean();
		return new RegisterAuthorRequest(author, authorPassword);
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
