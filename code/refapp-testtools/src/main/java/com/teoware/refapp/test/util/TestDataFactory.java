package com.teoware.refapp.test.util;

import org.joda.time.DateTime;

import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.Username;
import com.teoware.refapp.model.util.BeanFactory;

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

	public static UserPassword createUserJohnPassword() {
		UserPassword bean = new UserPassword();
		bean.setPassword("johnsPassword");
		bean.setSalt("johnsPasswordSalt");
		return bean;
	}

	public static UserPassword createUserJanePassword() {
		UserPassword bean = new UserPassword();
		bean.setPassword("janesPassword");
		bean.setSalt("janesPasswordSalt");
		return bean;
	}

	public static UserPassword createUserJonahPassword() {
		UserPassword bean = new UserPassword();
		bean.setPassword("jonahsPassword");
		bean.setSalt("jonahsPasswordSalt");
		return bean;
	}

	public static CreateUserInput createCreateUserJohnInput() {
		return new CreateUserInput(createUserJohn(), createUserJohnPassword());
	}

	public static CreateUserInput createCreateUserJaneInput() {
		return new CreateUserInput(createUserJohn(), createUserJanePassword());
	}

	public static CreateUserInput createCreateUserJonahInput() {
		return new CreateUserInput(createUserJohn(), createUserJonahPassword());
	}

	public static UpdateUserInput createUpdateUserJohnInput() {
		return new UpdateUserInput(createUserJohn(), createUserJohnPassword());
	}

	public static UpdateUserInput createUpdateUserJaneInput() {
		return new UpdateUserInput(createUserJohn(), createUserJanePassword());
	}

	public static UpdateUserInput createUpdateUserJonahInput() {
		return new UpdateUserInput(createUserJohn(), createUserJonahPassword());
	}

	public static ReadUserInput createReadUserJohnInput() {
		return new ReadUserInput(createUsername("john.doe"));
	}

	public static ReadUserInput createReadUserJaneInput() {
		return new ReadUserInput(createUsername("jane.doe"));
	}

	public static ReadUserInput createReadUserJonahInput() {
		return new ReadUserInput(createUsername("jonah.doe"));
	}

	public static DeleteUserInput createDeleteUserJohnInput() {
		return new DeleteUserInput("john.doe");
	}

	public static DeleteUserInput createDeleteUserJaneInput() {
		return new DeleteUserInput("jane.doe");
	}

	public static DeleteUserInput createDeleteUserJonahInput() {
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