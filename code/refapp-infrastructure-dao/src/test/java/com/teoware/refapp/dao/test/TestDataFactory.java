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
import com.teoware.refapp.model.user.Username;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.util.time.DateTimeUtils;

public final class TestDataFactory {

	public static User createUserJohn() {
		Calendar calendar = Calendar.getInstance();
		User user = BeanFactory.createUserBean();
		user.getUserId().setUserName("john.doe");
		user.getUserId().setStatus(UserStatus.ACTIVE);
		user.getUserId().setCreated(calendar);
		user.getUserId().setModified(calendar);
		user.getUserInfo().setFirstName("John");
		user.getUserInfo().setLastName("Doe");
		user.getUserInfo().setBirthDate(DateTimeUtils.stringToDate("1975-01-01"));
		user.getUserInfo().setGender(Gender.MALE);
		user.getUserInfo().setEmail("john.doe@email.com");
		user.getUserInfo().setPhone("+47 23456789");
		user.getUserAddress().setAddress("Storgata 1");
		user.getUserAddress().setPostalCode("1234");
		user.getUserAddress().setMunicipality("Oslo");
		user.getUserAddress().setRegion("Oslo");
		user.getUserAddress().setCountry("Norway");
		return user;
	}

	public static User createUserJane() {
		Calendar calendar = Calendar.getInstance();
		User user = BeanFactory.createUserBean();
		user.getUserId().setUserName("jane.doe");
		user.getUserId().setStatus(UserStatus.ACTIVE);
		user.getUserId().setCreated(calendar);
		user.getUserId().setModified(calendar);
		user.getUserInfo().setFirstName("Jane");
		user.getUserInfo().setLastName("Doe");
		user.getUserInfo().setBirthDate(DateTimeUtils.stringToDate("1970-12-30"));
		user.getUserInfo().setGender(Gender.FEMALE);
		user.getUserInfo().setEmail("jane.doe@email.com");
		user.getUserInfo().setPhone("+47 98765432");
		user.getUserAddress().setAddress("Lillegata 1");
		user.getUserAddress().setPostalCode("1010");
		user.getUserAddress().setMunicipality("Oslo");
		user.getUserAddress().setRegion("Oslo");
		user.getUserAddress().setCountry("Norway");
		return user;
	}

	public static User createUserJonah() {
		Calendar calendar = Calendar.getInstance();
		User user = BeanFactory.createUserBean();
		user.getUserId().setUserName("jonah.doe");
		user.getUserId().setStatus(UserStatus.ACTIVE);
		user.getUserId().setCreated(calendar);
		user.getUserId().setModified(calendar);
		user.getUserInfo().setFirstName("Jonah");
		user.getUserInfo().setLastName("Doe");
		user.getUserInfo().setBirthDate(DateTimeUtils.stringToDate("1975-01-01"));
		user.getUserInfo().setGender(Gender.MALE);
		user.getUserInfo().setEmail("jonah.doe@email.com");
		user.getUserInfo().setPhone("+47 19283746");
		user.getUserAddress().setAddress("Mellomgata 1");
		user.getUserAddress().setPostalCode("1221");
		user.getUserAddress().setMunicipality("Oslo");
		user.getUserAddress().setRegion("Oslo");
		user.getUserAddress().setCountry("Norway");
		return user;
	}

	public static UserPassword createUserJohnPassword() {
		UserPassword userPassword = new UserPassword();
		userPassword.setPassword("johnsPassword");
		userPassword.setSalt("johnsPasswordSalt");
		return userPassword;
	}

	public static UserPassword createUserJanePassword() {
		UserPassword userPassword = new UserPassword();
		userPassword.setPassword("janesPassword");
		userPassword.setSalt("janesPasswordSalt");
		return userPassword;
	}

	public static UserPassword createUserJonahPassword() {
		UserPassword userPassword = new UserPassword();
		userPassword.setPassword("jonahsPassword");
		userPassword.setSalt("jonahsPasswordSalt");
		return userPassword;
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
		return new SelectUserRequest(createUsername("john.doe"));
	}

	public static SelectUserRequest createSelectUserJaneRequest() {
		return new SelectUserRequest(createUsername("jane.doe"));
	}

	public static SelectUserRequest createSelectUserJonahRequest() {
		return new SelectUserRequest(createUsername("jonah.doe"));
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

	private static Username createUsername(String username) {
		Username bean = new Username();
		bean.setUsername(username);
		return bean;
	}
}
