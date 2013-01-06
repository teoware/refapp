package com.teoware.refapp.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.UserDao;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserOutput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.ReadUserPasswordInput;
import com.teoware.refapp.dao.dto.ReadUserPasswordOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserOutput;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.Username;
import com.teoware.refapp.test.util.TestDataFactory;

public abstract class UserDaoTestHelper {

	public static int createUser(UserDao userDao, User user, UserPassword userPassword) throws DaoException {
		CreateUserInput input = new CreateUserInput(user, userPassword);
		CreateUserOutput output = userDao.createUser(input);
		return output.getRowsAffected();
	}

	public static int updateUser(UserDao userDao, User user, UserPassword userPassword) throws DaoException {
		UpdateUserInput input = new UpdateUserInput(user, userPassword);
		UpdateUserOutput output = userDao.updateUser(input);
		return output.getRowsAffected();
	}

	public static List<User> readUser(UserDao userDao, String username) throws DaoException {
		Username bean = new Username();
		bean.setUsername(username);
		ReadUserInput input = new ReadUserInput(bean);
		ReadUserOutput output = userDao.readUser(input);
		return output.getUserList();
	}

	public static List<UserPassword> readUserPassword(UserDao userDao, String userName) throws DaoException {
		ReadUserPasswordInput input = new ReadUserPasswordInput(userName);
		ReadUserPasswordOutput output = userDao.readUserPassword(input);
		return output.getUserPasswordList();
	}

	public static List<User> readAllUsers(UserDao userDao) throws DaoException {
		ReadUserOutput output = userDao.readAllUsers();
		return output.getUserList();
	}

	public static int deleteUser(UserDao userDao, String userName) throws DaoException {
		DeleteUserInput input = new DeleteUserInput(userName);
		DeleteUserOutput output = userDao.deleteUser(input);
		return output.getRowsAffected();
	}

	public static int createUserJohn(UserDao userDao) throws DaoException {
		User user = TestDataFactory.createUserJohn();
		UserPassword userPassword = TestDataFactory.createUserJohnPassword();
		return createUser(userDao, user, userPassword);
	}

	public static int createUserJane(UserDao userDao) throws DaoException {
		User user = TestDataFactory.createUserJane();
		UserPassword userPassword = TestDataFactory.createUserJanePassword();
		return createUser(userDao, user, userPassword);
	}

	public static int createUserJonah(UserDao userDao) throws DaoException {
		User user = TestDataFactory.createUserJonah();
		UserPassword userPassword = TestDataFactory.createUserJonahPassword();
		return createUser(userDao, user, userPassword);
	}

	public static void assertCreateUserJohn(User user) {
		assertNotNull(user.getUsername());
		assertEquals("john.doe", user.getUsername().getUsername());
		assertEquals(Status.PENDING, user.getUserStatus().getStatus());
		assertTrue(user.getUserStatus().getCreated().isBefore(user.getUserStatus().getModified())
				|| user.getUserStatus().getCreated().equals(user.getUserStatus().getModified()));

		assertNotNull(user.getUserInfo());
		assertEquals("John", user.getUserInfo().getFirstName());
		assertEquals("Doe", user.getUserInfo().getLastName());
		assertNotNull(user.getUserInfo().getBirthDate());
		assertEquals(Gender.MALE, user.getUserInfo().getGender());
		assertEquals("john.doe@email.com", user.getUserInfo().getEmail());
		assertEquals("+47 23456789", user.getUserInfo().getPhone());

		assertNotNull(user.getUserAddress());
		assertEquals("Storgata 1", user.getUserAddress().getAddress());
		assertEquals("1234", user.getUserAddress().getPostalCode());
		assertEquals("Oslo", user.getUserAddress().getMunicipality());
		assertEquals("Oslo", user.getUserAddress().getRegion());
		assertEquals("Norway", user.getUserAddress().getCountry());
	}

	public static void assertCreateUserJane(User user) {
		assertNotNull(user.getUsername());
		assertTrue("jane.doe".equals(user.getUsername().getUsername()));
		assertTrue(Status.PENDING.equals(user.getUserStatus().getStatus()));
		assertTrue(user.getUserStatus().getCreated().isBefore(user.getUserStatus().getModified())
				|| user.getUserStatus().getCreated().equals(user.getUserStatus().getModified()));

		assertNotNull(user.getUserInfo());
		assertEquals("Jane", user.getUserInfo().getFirstName());
		assertEquals("Doe", user.getUserInfo().getLastName());
		assertNotNull(user.getUserInfo().getBirthDate());
		assertEquals(Gender.FEMALE, user.getUserInfo().getGender());
		assertEquals("jane.doe@email.com", user.getUserInfo().getEmail());
		assertEquals("+47 98765432", user.getUserInfo().getPhone());

		assertNotNull(user.getUserAddress());
		assertEquals("Lillegata 1", user.getUserAddress().getAddress());
		assertEquals("1010", user.getUserAddress().getPostalCode());
		assertEquals("Oslo", user.getUserAddress().getMunicipality());
		assertEquals("Oslo", user.getUserAddress().getRegion());
		assertEquals("Norway", user.getUserAddress().getCountry());
	}

	public static void assertUpdateUserJane(User user) {
		assertNotNull(user.getUsername());
		assertEquals("jane.doe", user.getUsername().getUsername());
		assertEquals(Status.ACTIVE, user.getUserStatus().getStatus());
		assertTrue(user.getUserStatus().getCreated().isBefore(user.getUserStatus().getModified())
				|| user.getUserStatus().getCreated().equals(user.getUserStatus().getModified()));

		assertNotNull(user.getUserInfo());
		assertEquals("Jane", user.getUserInfo().getFirstName());
		assertEquals("Doe", user.getUserInfo().getLastName());
		assertNotNull(user.getUserInfo().getBirthDate());
		assertEquals(Gender.FEMALE, user.getUserInfo().getGender());
		assertEquals("jane.doe@epost.net", user.getUserInfo().getEmail());
		assertEquals("+47 22334455", user.getUserInfo().getPhone());

		assertNotNull(user.getUserAddress());
		assertEquals("Nygata 2", user.getUserAddress().getAddress());
		assertEquals("1122", user.getUserAddress().getPostalCode());
		assertEquals("Oslo", user.getUserAddress().getMunicipality());
		assertEquals("Oslo", user.getUserAddress().getRegion());
		assertEquals("Norway", user.getUserAddress().getCountry());
	}
}
