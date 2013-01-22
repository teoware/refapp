package com.teoware.refapp.dao.itest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.dto.CreateUserAddressInput;
import com.teoware.refapp.dao.dto.CreateUserAddressOutput;
import com.teoware.refapp.dao.dto.CreateUserDetailsInput;
import com.teoware.refapp.dao.dto.CreateUserDetailsOutput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.CreateUserPasswordInput;
import com.teoware.refapp.dao.dto.CreateUserPasswordOutput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserOutput;
import com.teoware.refapp.dao.dto.Id;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.ReadUserPasswordInput;
import com.teoware.refapp.dao.dto.ReadUserPasswordOutput;
import com.teoware.refapp.dao.dto.UpdateUserAddressInput;
import com.teoware.refapp.dao.dto.UpdateUserAddressOutput;
import com.teoware.refapp.dao.dto.UpdateUserDetailsInput;
import com.teoware.refapp.dao.dto.UpdateUserDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserStatusInput;
import com.teoware.refapp.dao.dto.UpdateUserStatusOutput;
import com.teoware.refapp.dao.itest.mock.UserDaoMock;
import com.teoware.refapp.dao.itest.util.TestDataSourceHandler;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.Username;
import com.teoware.refapp.test.util.TestDataFactory;

@Category(com.teoware.refapp.test.IntegrationTestGroup.class)
public class UserDaoIT {

	private static Connection connection;
	private static UserDaoMock userDao;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		connection = TestDataSourceHandler.createDataSourceConnection();
		userDao = new UserDaoMock(connection);
	}

	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		userDao.closeAll();
	}

	@After
	public void tearDown() {
		try {
			userDao.rollBack();
		} catch (Throwable t) {
			t.printStackTrace();
			fail(t.getMessage());
		}
	}

	@Test
	public void testInsertAndSelectUserJohn() {
		try {
			CreateUserInput createUserInput = TestDataFactory.createCreateUserInputJohn();
			CreateUserOutput createUserOutput = userDao.createUser(createUserInput);
			Id userId = createUserOutput.getUserId();

			assertEquals(1, createUserOutput.getRowsAffected());

			CreateUserDetailsInput createUserInfoInput = TestDataFactory.createCreateUserDetailsInputJohn(userId);
			CreateUserDetailsOutput createUserInfoOutput = userDao.createUserDetails(createUserInfoInput);

			assertEquals(1, createUserInfoOutput.getRowsAffected());

			CreateUserAddressInput createUserAddressInput = TestDataFactory.createCreateUserAddressInputJohn(userId);
			CreateUserAddressOutput createUserAddressOutput = userDao.createUserAddress(createUserAddressInput);

			assertEquals(1, createUserAddressOutput.getRowsAffected());

			Username bean = new Username("john.doe");
			ReadUserInput readInput = new ReadUserInput(bean);
			ReadUserOutput readOutput = userDao.readUser(readInput);
			List<User> userList = readOutput.getUserList();

			assertNotNull(userList);
			assertEquals(1, userList.size());
			User author = userList.get(0);

			assertCreateUserJohn(author);
		} catch (Throwable t) {
			t.printStackTrace();
			fail(t.getMessage());
		}
	}

	@Test
	public void testInsertUpdateAndSelectUserJane() {
		try {
			CreateUserInput createUserInput = TestDataFactory.createCreateUserInputJane();
			CreateUserOutput createUserOutput = userDao.createUser(createUserInput);
			Id userId = createUserOutput.getUserId();

			assertEquals(1, createUserOutput.getRowsAffected());

			CreateUserDetailsInput createUserInfoInput = TestDataFactory.createCreateUserDetailsInputJane(userId);
			CreateUserDetailsOutput createUserInfoOutput = userDao.createUserDetails(createUserInfoInput);

			assertEquals(1, createUserInfoOutput.getRowsAffected());

			CreateUserAddressInput createUserAddressInput = TestDataFactory.createCreateUserAddressInputJane(userId);
			CreateUserAddressOutput createUserAddressOutput = userDao.createUserAddress(createUserAddressInput);

			assertEquals(1, createUserAddressOutput.getRowsAffected());

			Username bean = new Username("jane.doe");
			ReadUserInput readInput = new ReadUserInput(bean);
			ReadUserOutput readOutput = userDao.readUser(readInput);
			List<User> userList = readOutput.getUserList();

			assertNotNull(userList);
			assertEquals(1, userList.size());

			User user = userList.get(0);

			assertCreateUserJane(user);

			user.getUserDetails().setEmail("jane.doe@epost.net");
			user.getUserDetails().setPhone("+47 22334455");
			user.getUserAddress().setAddress("Nygata 2");
			user.getUserAddress().setPostalCode("1122");
			user.getUserStatus().setStatus(Status.ACTIVE);

			userId = userDao.readUserId(user.getUsername().getUsername());

			UpdateUserInput updateUserInput = new UpdateUserInput(userId, user.getUsername());
			UpdateUserOutput updateUserOutput = userDao.updateUser(updateUserInput);

			assertEquals(1, updateUserOutput.getRowsAffected());

			UpdateUserDetailsInput updateUserInfoInput = new UpdateUserDetailsInput(userId, user.getUserDetails());
			UpdateUserDetailsOutput updateUserInfoOutput = userDao.updateUserDetails(updateUserInfoInput);

			assertEquals(1, updateUserInfoOutput.getRowsAffected());

			UpdateUserAddressInput updateUserAddressInput = new UpdateUserAddressInput(userId, user.getUserAddress());
			UpdateUserAddressOutput updateUserAddressOutput = userDao.updateUserAddress(updateUserAddressInput);

			assertEquals(1, updateUserAddressOutput.getRowsAffected());

			UpdateUserStatusInput updateUserStatusInput = new UpdateUserStatusInput(userId, user.getUserStatus());
			UpdateUserStatusOutput updateUserStatusOutput = userDao.updateUserStatus(updateUserStatusInput);

			assertEquals(1, updateUserStatusOutput.getRowsAffected());

			readOutput = userDao.readUser(readInput);
			userList = readOutput.getUserList();

			assertNotNull(userList);
			assertEquals(1, userList.size());
			user = userList.get(0);

			assertUpdateUserJane(user);
		} catch (Throwable t) {
			t.printStackTrace();
			fail(t.getMessage());
		}
	}

	@Test
	public void testInsertAndSelectPasswordForUserJonah() throws DaoException {
		try {
			User user = TestDataFactory.createUserJonah();
			CreateUserInput createInput = new CreateUserInput(user.getUsername());
			CreateUserOutput createOutput = userDao.createUser(createInput);

			assertEquals(1, createOutput.getRowsAffected());

			UserPassword userPwd = new UserPassword("jonahsPassword", "jonahsPasswordSalt");
			CreateUserPasswordInput createPwdInput = new CreateUserPasswordInput(createOutput.getUserId(), userPwd);
			CreateUserPasswordOutput createPwdOutput = userDao.createUserPassword(createPwdInput);

			assertEquals(1, createPwdOutput.getRowsAffected());

			ReadUserPasswordInput readInput = new ReadUserPasswordInput("jonah.doe");
			ReadUserPasswordOutput readOutput = userDao.readUserPassword(readInput);
			List<UserPassword> userPasswordList = readOutput.getUserPasswordList();

			assertNotNull(userPasswordList);
			assertEquals(1, userPasswordList.size());
			UserPassword authorPassword = userPasswordList.get(0);

			assertNotNull(authorPassword.getPassword());
			assertEquals("jonahsPassword", authorPassword.getPassword());
			assertEquals("jonahsPasswordSalt", authorPassword.getSalt());
		} catch (Throwable t) {
			t.printStackTrace();
			fail(t.getMessage());
		}
	}

	@Test
	public void testInsertAndSelectAllUsersShouldBeThree() throws DaoException {
		try {
			int rowsAffected = 0;

			CreateUserInput createUserInput = TestDataFactory.createCreateUserInputJohn();
			CreateUserOutput createUserOutput = userDao.createUser(createUserInput);
			Id userId = createUserOutput.getUserId();
			rowsAffected += createUserOutput.getRowsAffected();

			CreateUserDetailsInput createUserInfoInput = TestDataFactory.createCreateUserDetailsInputJohn(userId);
			rowsAffected += userDao.createUserDetails(createUserInfoInput).getRowsAffected();

			CreateUserAddressInput createUserAddressInput = TestDataFactory.createCreateUserAddressInputJohn(userId);
			CreateUserAddressOutput createUserAddressOutput = userDao.createUserAddress(createUserAddressInput);

			rowsAffected += createUserAddressOutput.getRowsAffected();

			createUserInput = TestDataFactory.createCreateUserInputJane();
			createUserOutput = userDao.createUser(createUserInput);
			userId = createUserOutput.getUserId();

			rowsAffected += createUserOutput.getRowsAffected();

			createUserInfoInput = TestDataFactory.createCreateUserDetailsInputJane(userId);
			rowsAffected += userDao.createUserDetails(createUserInfoInput).getRowsAffected();

			createUserAddressInput = TestDataFactory.createCreateUserAddressInputJane(userId);
			createUserAddressOutput = userDao.createUserAddress(createUserAddressInput);

			rowsAffected += createUserAddressOutput.getRowsAffected();

			createUserInput = TestDataFactory.createCreateUserInputJonah();
			createUserOutput = userDao.createUser(createUserInput);
			userId = createUserOutput.getUserId();

			rowsAffected += createUserOutput.getRowsAffected();

			createUserInfoInput = TestDataFactory.createCreateUserDetailsInputJonah(userId);
			rowsAffected += userDao.createUserDetails(createUserInfoInput).getRowsAffected();

			createUserAddressInput = TestDataFactory.createCreateUserAddressInputJonah(userId);
			createUserAddressOutput = userDao.createUserAddress(createUserAddressInput);

			rowsAffected += createUserAddressOutput.getRowsAffected();

			assertEquals(9, rowsAffected);

			ReadUserOutput readAllOutput = userDao.readAllUsers();
			List<User> userList = readAllOutput.getUserList();

			assertNotNull(userList);
			assertEquals(3, userList.size());
		} catch (Throwable t) {
			t.printStackTrace();
			fail(t.getMessage());
		}
	}

	@Test
	public void testInsertDeleteOneAndSelectAllUsersShouldBeTwo() throws DaoException {
		try {
			int rowsAffected = 0;

			CreateUserInput createUserInput = TestDataFactory.createCreateUserInputJohn();
			CreateUserOutput createUserOutput = userDao.createUser(createUserInput);
			Id userId = createUserOutput.getUserId();
			rowsAffected += createUserOutput.getRowsAffected();

			CreateUserDetailsInput createUserInfoInput = TestDataFactory.createCreateUserDetailsInputJohn(userId);
			rowsAffected += userDao.createUserDetails(createUserInfoInput).getRowsAffected();

			CreateUserAddressInput createUserAddressInput = TestDataFactory.createCreateUserAddressInputJohn(userId);
			CreateUserAddressOutput createUserAddressOutput = userDao.createUserAddress(createUserAddressInput);

			rowsAffected += createUserAddressOutput.getRowsAffected();

			createUserInput = TestDataFactory.createCreateUserInputJane();
			createUserOutput = userDao.createUser(createUserInput);
			userId = createUserOutput.getUserId();

			rowsAffected += createUserOutput.getRowsAffected();

			createUserInfoInput = TestDataFactory.createCreateUserDetailsInputJane(userId);
			rowsAffected += userDao.createUserDetails(createUserInfoInput).getRowsAffected();

			createUserAddressInput = TestDataFactory.createCreateUserAddressInputJane(userId);
			createUserAddressOutput = userDao.createUserAddress(createUserAddressInput);

			rowsAffected += createUserAddressOutput.getRowsAffected();

			createUserInput = TestDataFactory.createCreateUserInputJonah();
			createUserOutput = userDao.createUser(createUserInput);
			userId = createUserOutput.getUserId();

			rowsAffected += createUserOutput.getRowsAffected();

			createUserInfoInput = TestDataFactory.createCreateUserDetailsInputJonah(userId);
			rowsAffected += userDao.createUserDetails(createUserInfoInput).getRowsAffected();

			createUserAddressInput = TestDataFactory.createCreateUserAddressInputJonah(userId);
			createUserAddressOutput = userDao.createUserAddress(createUserAddressInput);

			rowsAffected += createUserAddressOutput.getRowsAffected();

			assertEquals(9, rowsAffected);

			userId = userDao.readUserId("john.doe");

			DeleteUserInput deleteInput = new DeleteUserInput(userId);
			DeleteUserOutput deleteOutput = userDao.deleteUser(deleteInput);

			assertEquals(1, deleteOutput.getRowsAffected());

			ReadUserOutput readAllOutput = userDao.readAllUsers();
			List<User> userList = readAllOutput.getUserList();

			assertNotNull(userList);
			assertEquals(3, userList.size());
		} catch (Throwable t) {
			t.printStackTrace();
			fail(t.getMessage());
		}
	}

	public static void assertCreateUserJohn(User user) {
		assertNotNull(user.getUsername());
		assertEquals("john.doe", user.getUsername().getUsername());
		assertEquals(Status.PENDING, user.getUserStatus().getStatus());
		assertTrue(user.getUserStatus().getCreated().isBefore(user.getUserStatus().getModified())
				|| user.getUserStatus().getCreated().equals(user.getUserStatus().getModified()));

		assertNotNull(user.getUserDetails());
		assertEquals("John", user.getUserDetails().getFirstName());
		assertEquals("Doe", user.getUserDetails().getLastName());
		assertNotNull(user.getUserDetails().getBirthDate());
		assertEquals(Gender.MALE, user.getUserDetails().getGender());
		assertEquals("john.doe@email.com", user.getUserDetails().getEmail());
		assertEquals("+47 23456789", user.getUserDetails().getPhone());

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

		assertNotNull(user.getUserDetails());
		assertEquals("Jane", user.getUserDetails().getFirstName());
		assertEquals("Doe", user.getUserDetails().getLastName());
		assertNotNull(user.getUserDetails().getBirthDate());
		assertEquals(Gender.FEMALE, user.getUserDetails().getGender());
		assertEquals("jane.doe@email.com", user.getUserDetails().getEmail());
		assertEquals("+47 98765432", user.getUserDetails().getPhone());

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

		assertNotNull(user.getUserDetails());
		assertEquals("Jane", user.getUserDetails().getFirstName());
		assertEquals("Doe", user.getUserDetails().getLastName());
		assertNotNull(user.getUserDetails().getBirthDate());
		assertEquals(Gender.FEMALE, user.getUserDetails().getGender());
		assertEquals("jane.doe@epost.net", user.getUserDetails().getEmail());
		assertEquals("+47 22334455", user.getUserDetails().getPhone());

		assertNotNull(user.getUserAddress());
		assertEquals("Nygata 2", user.getUserAddress().getAddress());
		assertEquals("1122", user.getUserAddress().getPostalCode());
		assertEquals("Oslo", user.getUserAddress().getMunicipality());
		assertEquals("Oslo", user.getUserAddress().getRegion());
		assertEquals("Norway", user.getUserAddress().getCountry());
	}
}
