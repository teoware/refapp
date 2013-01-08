package com.teoware.refapp.dao;

import static com.teoware.refapp.dao.UserDaoBean.USERS_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_ADDRESS_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_PASSWORD_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_STATUS_TABLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.CreateUserPasswordInput;
import com.teoware.refapp.dao.dto.CreateUserPasswordOutput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserOutput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.ReadUserPasswordInput;
import com.teoware.refapp.dao.dto.ReadUserPasswordOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserOutput;
import com.teoware.refapp.dao.mock.UserDaoMock;
import com.teoware.refapp.dao.test.TestDataSourceHandler;
import com.teoware.refapp.dao.util.SQL;
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
		connection = TestDataSourceHandler.initializeDatabase();
		userDao = new UserDaoMock(connection);
	}

	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		userDao.closeAll();
	}

	@Before
	public void setUp() throws Exception {
		cleanTables();
	}

	@Test
	public void testInsertAndSelectUserJohn() {
		try {
			User user = TestDataFactory.createUserJohn();
			CreateUserInput createInput = new CreateUserInput(user);
			CreateUserOutput createOutput = userDao.createUser(createInput);

			assertEquals(3, createOutput.getRowsAffected());

			Username bean = new Username("john.doe");
			ReadUserInput readInput = new ReadUserInput(bean);
			ReadUserOutput readOutput = userDao.readUser(readInput);
			List<User> userList = readOutput.getUserList();

			assertNotNull(userList);
			assertEquals(1, userList.size());
			User author = userList.get(0);

			assertCreateUserJohn(author);
		} catch (DaoException e) {
			e.printStackTrace();
			fail("" + e.getMessage());
		}
	}

	@Test
	public void testInsertUpdateAndSelectUserJane() throws DaoException {
		User user = TestDataFactory.createUserJane();
		CreateUserInput createInput = new CreateUserInput(user);
		CreateUserOutput createOutput = userDao.createUser(createInput);

		assertEquals(3, createOutput.getRowsAffected());

		Username bean = new Username("jane.doe");
		ReadUserInput readInput = new ReadUserInput(bean);
		ReadUserOutput readOutput = userDao.readUser(readInput);
		List<User> userList = readOutput.getUserList();

		assertNotNull(userList);
		assertEquals(1, userList.size());

		user = userList.get(0);

		assertCreateUserJane(user);

		user.getUserInfo().setEmail("jane.doe@epost.net");
		user.getUserInfo().setPhone("+47 22334455");
		user.getUserAddress().setAddress("Nygata 2");
		user.getUserAddress().setPostalCode("1122");
		user.getUserStatus().setStatus(Status.ACTIVE);

		UpdateUserInput input = new UpdateUserInput(user);
		UpdateUserOutput output = userDao.updateUser(input);
		assertEquals(3, output.getRowsAffected());

		readOutput = userDao.readUser(readInput);
		userList = readOutput.getUserList();

		assertNotNull(userList);
		assertEquals(1, userList.size());
		user = userList.get(0);

		assertUpdateUserJane(user);
	}

	@Test
	public void testInsertAndSelectPasswordForUserJonah() throws DaoException {
		User user = TestDataFactory.createUserJonah();
		CreateUserInput createInput = new CreateUserInput(user);
		CreateUserOutput createOutput = userDao.createUser(createInput);

		assertEquals(3, createOutput.getRowsAffected());

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
	}

	@Test
	public void testInsertAndSelectAllUsersShouldBeThree() throws DaoException {
		User user = TestDataFactory.createUserJohn();
		CreateUserInput createInput = new CreateUserInput(user);
		CreateUserOutput createOutput = userDao.createUser(createInput);
		int rowsAffected = createOutput.getRowsAffected();

		user = TestDataFactory.createUserJane();
		createInput = new CreateUserInput(user);
		createOutput = userDao.createUser(createInput);
		rowsAffected += createOutput.getRowsAffected();

		user = TestDataFactory.createUserJonah();
		createInput = new CreateUserInput(user);
		createOutput = userDao.createUser(createInput);
		rowsAffected += createOutput.getRowsAffected();

		assertEquals(9, rowsAffected);

		ReadUserOutput readAllOutput = userDao.readAllUsers();
		List<User> userList = readAllOutput.getUserList();

		assertNotNull(userList);
		assertEquals(3, userList.size());
	}

	@Test
	public void testInsertDeleteOneAndSelectAllUsersShouldBeTwo() throws DaoException {
		User user = TestDataFactory.createUserJohn();
		CreateUserInput createInput = new CreateUserInput(user);
		CreateUserOutput createOutput = userDao.createUser(createInput);
		int rowsAffected = createOutput.getRowsAffected();

		user = TestDataFactory.createUserJane();
		createInput = new CreateUserInput(user);
		createOutput = userDao.createUser(createInput);
		rowsAffected += createOutput.getRowsAffected();

		user = TestDataFactory.createUserJonah();
		createInput = new CreateUserInput(user);
		createOutput = userDao.createUser(createInput);
		rowsAffected += createOutput.getRowsAffected();

		assertEquals(9, rowsAffected);

		DeleteUserInput deleteInput = new DeleteUserInput("john.doe");
		DeleteUserOutput deleteOutput = userDao.deleteUser(deleteInput);

		assertEquals(1, deleteOutput.getRowsAffected());

		ReadUserOutput readAllOutput = userDao.readAllUsers();
		List<User> userList = readAllOutput.getUserList();

		assertNotNull(userList);
		assertEquals(2, userList.size());
	}

	private static void cleanTables() throws DaoException {
		if (userDao.rowCount(USERS_TABLE) > 0) {
			userDao.delete(new SQL("DELETE FROM " + USERS_TABLE));
		}

		if (userDao.rowCount(USER_STATUS_TABLE) > 0) {
			userDao.delete(new SQL("DELETE FROM " + USER_STATUS_TABLE));
		}

		if (userDao.rowCount(USER_ADDRESS_TABLE) > 0) {
			userDao.delete(new SQL("DELETE FROM " + USER_ADDRESS_TABLE));
		}

		if (userDao.rowCount(USER_PASSWORD_TABLE) > 0) {
			userDao.delete(new SQL("DELETE FROM " + USER_PASSWORD_TABLE));
		}
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
