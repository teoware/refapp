package com.teoware.refapp.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.UserDao;
import com.teoware.refapp.dao.dto.DeleteUserRequest;
import com.teoware.refapp.dao.dto.DeleteUserResponse;
import com.teoware.refapp.dao.dto.InsertUserRequest;
import com.teoware.refapp.dao.dto.InsertUserResponse;
import com.teoware.refapp.dao.dto.SelectUserPasswordRequest;
import com.teoware.refapp.dao.dto.SelectUserPasswordResponse;
import com.teoware.refapp.dao.dto.SelectUserRequest;
import com.teoware.refapp.dao.dto.SelectUserResponse;
import com.teoware.refapp.dao.dto.UpdateUserRequest;
import com.teoware.refapp.dao.dto.UpdateUserResponse;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.UserStatus;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.Username;

public abstract class UserDaoTestHelper {

	public static int insertUser(UserDao userDao, User user, UserPassword userPassword) throws DaoException {
		InsertUserRequest request = new InsertUserRequest(user, userPassword);
		InsertUserResponse response = userDao.insertUser(request);
		return response.getRowsAffected();
	}

	public static int updateUser(UserDao userDao, User user, UserPassword userPassword) throws DaoException {
		UpdateUserRequest request = new UpdateUserRequest(user, userPassword);
		UpdateUserResponse response = userDao.updateUser(request);
		return response.getRowsAffected();
	}

	public static List<User> selectUser(UserDao userDao, String username) throws DaoException {
		Username bean = new Username();
		bean.setUsername(username);
		SelectUserRequest request = new SelectUserRequest(bean);
		SelectUserResponse response = userDao.selectUser(request);
		return response.getUserList();
	}

	public static List<User> selectUserJohn(UserDao userDao) throws DaoException {
		return selectUser(userDao, "john.doe");
	}

	public static List<User> selectUserJane(UserDao userDao) throws DaoException {
		return selectUser(userDao, "jane.doe");
	}

	public static List<User> selectUserJonah(UserDao userDao) throws DaoException {
		return selectUser(userDao, "jonah.doe");
	}

	public static List<UserPassword> selectUserPassword(UserDao userDao, String userName) throws DaoException {
		SelectUserPasswordRequest request = new SelectUserPasswordRequest(userName);
		SelectUserPasswordResponse response = userDao.selectUserPassword(request);
		return response.getUserPasswordList();
	}

	public static List<UserPassword> selectUserPasswordJohn(UserDao userDao) throws DaoException {
		return selectUserPassword(userDao, "john.doe");
	}

	public static List<UserPassword> selectUserPasswordJane(UserDao userDao) throws DaoException {
		return selectUserPassword(userDao, "jane.doe");
	}

	public static List<UserPassword> selectUserPasswordJonah(UserDao userDao) throws DaoException {
		return selectUserPassword(userDao, "jonah.doe");
	}

	public static List<User> selectAllUsers(UserDao userDao) throws DaoException {
		SelectUserResponse response = userDao.selectAllUsers();
		return response.getUserList();
	}

	public static int deleteUser(UserDao userDao, String userName) throws DaoException {
		DeleteUserRequest request = new DeleteUserRequest(userName);
		DeleteUserResponse response = userDao.deleteUser(request);
		return response.getRowsAffected();
	}

	public static int deleteUserJohn(UserDao userDao) throws DaoException {
		return deleteUser(userDao, "john.doe");
	}

	public static int deleteUserJane(UserDao userDao) throws DaoException {
		return deleteUser(userDao, "jane.doe");
	}

	public static int deleteUserJonah(UserDao userDao) throws DaoException {
		return deleteUser(userDao, "jonah.doe");
	}

	public static int insertUserJohn(UserDao userDao) throws DaoException {
		User user = TestDataFactory.createUserJohn();
		UserPassword userPassword = TestDataFactory.createUserJohnPassword();
		return insertUser(userDao, user, userPassword);
	}

	public static int insertUserJane(UserDao userDao) throws DaoException {
		User user = TestDataFactory.createUserJane();
		UserPassword userPassword = TestDataFactory.createUserJanePassword();
		return insertUser(userDao, user, userPassword);
	}

	public static int insertUserJonah(UserDao userDao) throws DaoException {
		User user = TestDataFactory.createUserJonah();
		UserPassword userPassword = TestDataFactory.createUserJonahPassword();
		return insertUser(userDao, user, userPassword);
	}

	public static void assertInsertUserJohn(User user) {
		assertNotNull(user.getUserId());
		assertEquals("john.doe", user.getUserId().getUserName());
		assertEquals(UserStatus.PENDING, user.getUserId().getStatus());
		assertTrue(user.getUserId().getCreated().before(user.getUserId().getModified())
				|| user.getUserId().getCreated().equals(user.getUserId().getModified()));

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

	public static void assertInsertUserJane(User user) {
		assertNotNull(user.getUserId());
		assertTrue("jane.doe".equals(user.getUserId().getUserName()));
		assertTrue(UserStatus.PENDING.equals(user.getUserId().getStatus()));
		assertTrue(user.getUserId().getCreated().before(user.getUserId().getModified())
				|| user.getUserId().getCreated().equals(user.getUserId().getModified()));

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
		assertNotNull(user.getUserId());
		assertEquals("jane.doe", user.getUserId().getUserName());
		assertEquals(UserStatus.ACTIVE, user.getUserId().getStatus());
		assertTrue(user.getUserId().getCreated().before(user.getUserId().getModified())
				|| user.getUserId().getCreated().equals(user.getUserId().getModified()));

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
