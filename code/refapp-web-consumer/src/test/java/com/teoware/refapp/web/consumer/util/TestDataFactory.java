package com.teoware.refapp.web.consumer.util;

import java.text.ParseException;
import java.util.Calendar;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.util.DateUtils;
import com.teoware.refapp.util.PasswordHandler;

public class TestDataFactory {

	public static Author createAuthorJohn() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Author author = BeanFactory.createAuthorBean();
		author.getAuthorId().setUserName("john.doe");
		author.getAuthorId().setStatus(AuthorStatus.ACTIVE);
		author.getAuthorId().setCreated(calendar);
		author.getAuthorId().setModified(calendar);
		author.getAuthorInfo().setFirstName("John");
		author.getAuthorInfo().setLastName("Doe");
		author.getAuthorInfo().setBirthDate(DateUtils.stringToDate("1975-01-01"));
		author.getAuthorInfo().setGender(Gender.MALE);
		author.getAuthorInfo().setEmail("john.doe@email.com");
		author.getAuthorInfo().setPhone("+47 23456789");
		author.getAuthorAddress().setAddress("Storgata 1");
		author.getAuthorAddress().setPostalCode("1234");
		author.getAuthorAddress().setMunicipality("Oslo");
		author.getAuthorAddress().setRegion("Oslo");
		author.getAuthorAddress().setCountry("Norway");
		return author;
	}

	public static Author createAuthorJane() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Author author = BeanFactory.createAuthorBean();
		author.getAuthorId().setUserName("jane.doe");
		author.getAuthorId().setStatus(AuthorStatus.ACTIVE);
		author.getAuthorId().setCreated(calendar);
		author.getAuthorId().setModified(calendar);
		author.getAuthorInfo().setFirstName("Jane");
		author.getAuthorInfo().setLastName("Doe");
		author.getAuthorInfo().setBirthDate(DateUtils.stringToDate("1970-12-30"));
		author.getAuthorInfo().setGender(Gender.FEMALE);
		author.getAuthorInfo().setEmail("jane.doe@email.com");
		author.getAuthorInfo().setPhone("+47 98765432");
		author.getAuthorAddress().setAddress("Lillegata 1");
		author.getAuthorAddress().setPostalCode("1010");
		author.getAuthorAddress().setMunicipality("Oslo");
		author.getAuthorAddress().setRegion("Oslo");
		author.getAuthorAddress().setCountry("Norway");
		return author;
	}

	public static Author createAuthorJonah() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Author author = BeanFactory.createAuthorBean();
		author.getAuthorId().setUserName("jonah.doe");
		author.getAuthorId().setStatus(AuthorStatus.ACTIVE);
		author.getAuthorId().setCreated(calendar);
		author.getAuthorId().setModified(calendar);
		author.getAuthorInfo().setFirstName("Jonah");
		author.getAuthorInfo().setLastName("Doe");
		author.getAuthorInfo().setBirthDate(DateUtils.stringToDate("1975-01-01"));
		author.getAuthorInfo().setGender(Gender.MALE);
		author.getAuthorInfo().setEmail("jonah.doe@email.com");
		author.getAuthorInfo().setPhone("+47 19283746");
		author.getAuthorAddress().setAddress("Mellomgata 1");
		author.getAuthorAddress().setPostalCode("1221");
		author.getAuthorAddress().setMunicipality("Oslo");
		author.getAuthorAddress().setRegion("Oslo");
		author.getAuthorAddress().setCountry("Norway");
		return author;
	}

	public static AuthorPassword createAuthorJohnPassword() {
		AuthorPassword authorPassword = new AuthorPassword();
		authorPassword.setPassword(PasswordHandler.encryptPassword("johnsPassword"));
		return authorPassword;
	}

	public static AuthorPassword createAuthorJanePassword() {
		AuthorPassword authorPassword = new AuthorPassword();
		authorPassword.setPassword(PasswordHandler.encryptPassword("janesPassword"));
		return authorPassword;
	}

	public static AuthorPassword createAuthorJonahPassword() {
		AuthorPassword authorPassword = new AuthorPassword();
		authorPassword.setPassword(PasswordHandler.encryptPassword("jonahsPassword"));
		return authorPassword;
	}
}
