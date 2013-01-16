package com.teoware.refapp.test.util;

import org.joda.time.DateTime;

import com.teoware.refapp.dao.dto.CreateUserAddressInput;
import com.teoware.refapp.dao.dto.CreateUserInfoInput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.Id;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.Username;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.service.dto.RegisterUserRequest;

public final class TestDataFactory {

	public static User createUserJohn() {
		DateTime dateTime = new DateTime();
		User bean = BeanFactory.createUserBean();
		bean.getUsername().setUsername("john.doe");
		bean.getUserStatus().setStatus(Status.ACTIVE);
		bean.getUserStatus().setCreated(dateTime);
		bean.getUserStatus().setModified(dateTime);
		bean.getUserInfo().setFirstName("John");
		bean.getUserInfo().setLastName("Doe");
		bean.getUserInfo().setBirthDate(new DateTime().withYear(1975).withMonthOfYear(1).withDayOfMonth(1));
		bean.getUserInfo().setGender(Gender.MALE);
		bean.getUserInfo().setEmail("john.doe@email.com");
		bean.getUserInfo().setPhone("+47 23456789");
		bean.getUserAddress().setAddress("Storgata 1");
		bean.getUserAddress().setPostalCode("1234");
		bean.getUserAddress().setMunicipality("Oslo");
		bean.getUserAddress().setRegion("Oslo");
		bean.getUserAddress().setCountry("Norway");
		return bean;
	}

	public static User createUserJane() {
		DateTime dateTime = new DateTime();
		User bean = BeanFactory.createUserBean();
		bean.getUsername().setUsername("jane.doe");
		bean.getUserStatus().setStatus(Status.ACTIVE);
		bean.getUserStatus().setCreated(dateTime);
		bean.getUserStatus().setModified(dateTime);
		bean.getUserInfo().setFirstName("Jane");
		bean.getUserInfo().setLastName("Doe");
		bean.getUserInfo().setBirthDate(new DateTime().withYear(1970).withMonthOfYear(12).withDayOfMonth(30));
		bean.getUserInfo().setGender(Gender.FEMALE);
		bean.getUserInfo().setEmail("jane.doe@email.com");
		bean.getUserInfo().setPhone("+47 98765432");
		bean.getUserAddress().setAddress("Lillegata 1");
		bean.getUserAddress().setPostalCode("1010");
		bean.getUserAddress().setMunicipality("Oslo");
		bean.getUserAddress().setRegion("Oslo");
		bean.getUserAddress().setCountry("Norway");
		return bean;
	}

	public static User createUserJonah() {
		DateTime dateTime = new DateTime();
		User bean = BeanFactory.createUserBean();
		bean.getUsername().setUsername("jonah.doe");
		bean.getUserStatus().setStatus(Status.ACTIVE);
		bean.getUserStatus().setCreated(dateTime);
		bean.getUserStatus().setModified(dateTime);
		bean.getUserInfo().setFirstName("Jonah");
		bean.getUserInfo().setLastName("Doe");
		bean.getUserInfo().setBirthDate(new DateTime().withYear(1975).withMonthOfYear(1).withDayOfMonth(1));
		bean.getUserInfo().setGender(Gender.MALE);
		bean.getUserInfo().setEmail("jonah.doe@email.com");
		bean.getUserInfo().setPhone("+47 19283746");
		bean.getUserAddress().setAddress("Mellomgata 1");
		bean.getUserAddress().setPostalCode("1221");
		bean.getUserAddress().setMunicipality("Oslo");
		bean.getUserAddress().setRegion("Oslo");
		bean.getUserAddress().setCountry("Norway");
		return bean;
	}

	public static UserPassword createUserPasswordJohn() {
		UserPassword bean = new UserPassword();
		bean.setPassword("johnsPassword");
		bean.setSalt("johnsPasswordSalt");
		return bean;
	}

	public static UserPassword createUserPasswordJane() {
		UserPassword bean = new UserPassword();
		bean.setPassword("janesPassword");
		bean.setSalt("janesPasswordSalt");
		return bean;
	}

	public static UserPassword createUserPasswordJonah() {
		UserPassword bean = new UserPassword();
		bean.setPassword("jonahsPassword");
		bean.setSalt("jonahsPasswordSalt");
		return bean;
	}

	public static RegisterUserRequest createRegisterUserRequestJohn() {
		return new RegisterUserRequest(createUserJohn(), createUserPasswordJohn());
	}

	public static RegisterUserRequest createRegisterUserRequestJane() {
		return new RegisterUserRequest(createUserJane(), createUserPasswordJane());
	}

	public static RegisterUserRequest createRegisterUserRequestJonah() {
		return new RegisterUserRequest(createUserJonah(), createUserPasswordJonah());
	}

	public static CreateUserInput createCreateUserInputJohn() {
		return new CreateUserInput(createUserJohn().getUsername());
	}

	public static CreateUserInfoInput createCreateUserInfoInputJohn() {
		return createCreateUserInfoInputJohn(new Id());
	}

	public static CreateUserInfoInput createCreateUserInfoInputJohn(Id userId) {
		return new CreateUserInfoInput(userId, createUserJohn().getUserInfo());
	}

	public static CreateUserAddressInput createCreateUserAddressInputJohn() {
		return createCreateUserAddressInputJohn(new Id());
	}

	public static CreateUserAddressInput createCreateUserAddressInputJohn(Id userId) {
		return new CreateUserAddressInput(userId, createUserJohn().getUserAddress());
	}

	public static CreateUserInput createCreateUserInputJane() {
		return new CreateUserInput(createUserJane().getUsername());
	}

	public static CreateUserInfoInput createCreateUserInfoInputJane() {
		return createCreateUserInfoInputJane(new Id());
	}

	public static CreateUserInfoInput createCreateUserInfoInputJane(Id userId) {
		return new CreateUserInfoInput(userId, createUserJane().getUserInfo());
	}

	public static CreateUserAddressInput createCreateUserAddressInputJane() {
		return createCreateUserAddressInputJane(new Id());
	}

	public static CreateUserAddressInput createCreateUserAddressInputJane(Id userId) {
		return new CreateUserAddressInput(userId, createUserJane().getUserAddress());
	}

	public static CreateUserInput createCreateUserInputJonah() {
		return new CreateUserInput(createUserJonah().getUsername());
	}

	public static CreateUserInfoInput createCreateUserInfoInputJonah() {
		return createCreateUserInfoInputJonah(new Id());
	}

	public static CreateUserInfoInput createCreateUserInfoInputJonah(Id userId) {
		return new CreateUserInfoInput(userId, createUserJonah().getUserInfo());
	}

	public static CreateUserAddressInput createCreateUserAddressInputJonah() {
		return createCreateUserAddressInputJonah(new Id());
	}

	public static CreateUserAddressInput createCreateUserAddressInputJonah(Id userId) {
		return new CreateUserAddressInput(userId, createUserJonah().getUserAddress());
	}

	public static UpdateUserInput createUpdateUserInputJohn() {
		return new UpdateUserInput(createUserJohn());
	}

	public static UpdateUserInput createUpdateUserInputJane() {
		return new UpdateUserInput(createUserJohn());
	}

	public static UpdateUserInput createUpdateUserInputJonah() {
		return new UpdateUserInput(createUserJohn());
	}

	public static ReadUserInput createReadUserInputJohn() {
		return new ReadUserInput(createUsername("john.doe"));
	}

	public static ReadUserInput createReadUserInputJane() {
		return new ReadUserInput(createUsername("jane.doe"));
	}

	public static ReadUserInput createReadUserInputJonah() {
		return new ReadUserInput(createUsername("jonah.doe"));
	}

	public static DeleteUserInput createDeleteUserInputJohn() {
		return new DeleteUserInput("john.doe");
	}

	public static DeleteUserInput createDeleteUserInputJane() {
		return new DeleteUserInput("jane.doe");
	}

	public static DeleteUserInput createDeleteUserInputJonah() {
		return new DeleteUserInput("jonah.doe");
	}

	public static PurgeUsersInput createPurgeUsersInput() {
		return new PurgeUsersInput();
	}

	private static Username createUsername(String username) {
		Username bean = new Username();
		bean.setUsername(username);
		return bean;
	}
}
