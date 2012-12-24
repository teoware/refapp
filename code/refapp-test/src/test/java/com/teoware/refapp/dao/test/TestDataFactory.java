package com.teoware.refapp.dao.test;

import java.util.Calendar;

import com.teoware.refapp.dao.dto.DeleteUserRequest;
import com.teoware.refapp.dao.dto.InsertUserRequest;
import com.teoware.refapp.dao.dto.PurgeUsersRequest;
import com.teoware.refapp.dao.dto.SelectUserRequest;
import com.teoware.refapp.dao.dto.UpdateUserRequest;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.UserStatus;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.util.PasswordHandler;
import com.teoware.refapp.util.time.DateTimeUtils;

public final class TestDataFactory {

	public static User createUserJohn() {
		Calendar calendar = Calendar.getInstance();
		User author = BeanFactory.createUserBean();
		author.getUserId().setUserName("john.doe");
		author.getUserId().setStatus(UserStatus.ACTIVE);
		author.getUserId().setCreated(calendar);
		author.getUserId().setModified(calendar);
		author.getUserInfo().setFirstName("John");
		author.getUserInfo().setLastName("Doe");
		author.getUserInfo().setBirthDate(DateTimeUtils.stringToDate("1975-01-01"));
		author.getUserInfo().setGender(Gender.MALE);
		author.getUserInfo().setEmail("john.doe@email.com");
		author.getUserInfo().setPhone("+47 23456789");
		author.getUserAddress().setAddress("Storgata 1");
		author.getUserAddress().setPostalCode("1234");
		author.getUserAddress().setMunicipality("Oslo");
		author.getUserAddress().setRegion("Oslo");
		author.getUserAddress().setCountry("Norway");
		return author;
	}

	public static User createUserJane() {
		Calendar calendar = Calendar.getInstance();
		User author = BeanFactory.createUserBean();
		author.getUserId().setUserName("jane.doe");
		author.getUserId().setStatus(UserStatus.ACTIVE);
		author.getUserId().setCreated(calendar);
		author.getUserId().setModified(calendar);
		author.getUserInfo().setFirstName("Jane");
		author.getUserInfo().setLastName("Doe");
		author.getUserInfo().setBirthDate(DateTimeUtils.stringToDate("1970-12-30"));
		author.getUserInfo().setGender(Gender.FEMALE);
		author.getUserInfo().setEmail("jane.doe@email.com");
		author.getUserInfo().setPhone("+47 98765432");
		author.getUserAddress().setAddress("Lillegata 1");
		author.getUserAddress().setPostalCode("1010");
		author.getUserAddress().setMunicipality("Oslo");
		author.getUserAddress().setRegion("Oslo");
		author.getUserAddress().setCountry("Norway");
		return author;
	}

	public static User createUserJonah() {
		Calendar calendar = Calendar.getInstance();
		User author = BeanFactory.createUserBean();
		author.getUserId().setUserName("jonah.doe");
		author.getUserId().setStatus(UserStatus.ACTIVE);
		author.getUserId().setCreated(calendar);
		author.getUserId().setModified(calendar);
		author.getUserInfo().setFirstName("Jonah");
		author.getUserInfo().setLastName("Doe");
		author.getUserInfo().setBirthDate(DateTimeUtils.stringToDate("1975-01-01"));
		author.getUserInfo().setGender(Gender.MALE);
		author.getUserInfo().setEmail("jonah.doe@email.com");
		author.getUserInfo().setPhone("+47 19283746");
		author.getUserAddress().setAddress("Mellomgata 1");
		author.getUserAddress().setPostalCode("1221");
		author.getUserAddress().setMunicipality("Oslo");
		author.getUserAddress().setRegion("Oslo");
		author.getUserAddress().setCountry("Norway");
		return author;
	}

	public static UserPassword createUserJohnPassword() {
		UserPassword authorPassword = new UserPassword();
		authorPassword.setPassword(PasswordHandler.encryptPassword("johnsPassword"));
		return authorPassword;
	}

	public static UserPassword createUserJanePassword() {
		UserPassword authorPassword = new UserPassword();
		authorPassword.setPassword(PasswordHandler.encryptPassword("janesPassword"));
		return authorPassword;
	}

	public static UserPassword createUserJonahPassword() {
		UserPassword authorPassword = new UserPassword();
		authorPassword.setPassword(PasswordHandler.encryptPassword("jonahsPassword"));
		return authorPassword;
	}

	public static InsertUserRequest createInsertUserJohnRequest() {
		return new InsertUserRequest(createUserJohn(), createUserJohnPassword());
	}

	public static InsertUserRequest createInsertUserJaneRequest() {
		return new InsertUserRequest(createUserJohn(), createUserJanePassword());
	}

	public static InsertUserRequest createInsertUserJonahRequest() {
		return new InsertUserRequest(createUserJohn(), createUserJonahPassword());
	}

	public static UpdateUserRequest createUpdateUserJohnRequest() {
		return new UpdateUserRequest(createUserJohn(), createUserJohnPassword());
	}

	public static UpdateUserRequest createUpdateUserJaneRequest() {
		return new UpdateUserRequest(createUserJohn(), createUserJanePassword());
	}

	public static UpdateUserRequest createUpdateUserJonahRequest() {
		return new UpdateUserRequest(createUserJohn(), createUserJonahPassword());
	}

	public static SelectUserRequest createSelectUserJohnRequest() {
		return new SelectUserRequest("john.doe");
	}

	public static SelectUserRequest createSelectUserJaneRequest() {
		return new SelectUserRequest("jane.doe");
	}

	public static SelectUserRequest createSelectUserJonahRequest() {
		return new SelectUserRequest("jonah.doe");
	}

	public static DeleteUserRequest createDeleteUserJohnRequest() {
		return new DeleteUserRequest("john.doe");
	}

	public static DeleteUserRequest createDeleteUserJaneRequest() {
		return new DeleteUserRequest("jane.doe");
	}

	public static DeleteUserRequest createDeleteUserJonahRequest() {
		return new DeleteUserRequest("jonah.doe");
	}

	public static PurgeUsersRequest createPurgeUsersRequest() {
		return new PurgeUsersRequest();
	}
}
