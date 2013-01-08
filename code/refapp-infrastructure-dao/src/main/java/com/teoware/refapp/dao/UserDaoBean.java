package com.teoware.refapp.dao;

import static com.teoware.refapp.dao.metadata.Schema.REFAPP_SCHEMA_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.ADDRESS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.BIRTHDATE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.COUNTRY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.EMAIL_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.FIRSTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.GENDER_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.ID_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.LASTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.MUNICIPALITY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.PASSWORD_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.PHONE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.POSTALCODE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.REGION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.SALT_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USERNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USERS_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USERS_VIEW_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USER_ADDRESS_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USER_ID_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USER_INFO_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USER_PASSWORD_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USER_STATUS_TABLE_NAME;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.CreateUserPasswordInput;
import com.teoware.refapp.dao.dto.CreateUserPasswordOutput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserOutput;
import com.teoware.refapp.dao.dto.Id;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.PurgeUsersOutput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.ReadUserPasswordInput;
import com.teoware.refapp.dao.dto.ReadUserPasswordOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordInput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordOutput;
import com.teoware.refapp.dao.metadata.JNDI;
import com.teoware.refapp.dao.rowmapper.UserIdRowMapper;
import com.teoware.refapp.dao.rowmapper.UserPasswordRowMapper;
import com.teoware.refapp.dao.rowmapper.UserRowMapper;
import com.teoware.refapp.dao.util.ChangeResult;
import com.teoware.refapp.dao.util.DaoHelper;
import com.teoware.refapp.dao.util.SQL;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.util.time.DateTimeConverter;
import com.teoware.refapp.util.time.DateTimeUtils;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserDaoBean extends Dao implements UserDao {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(UserDaoBean.class);

	private static final String DAO_NAME = "User DAO";

	public static final String USERS_VIEW = REFAPP_SCHEMA_NAME + "." + USERS_VIEW_NAME;
	public static final String USERS_TABLE = REFAPP_SCHEMA_NAME + "." + USERS_TABLE_NAME;
	public static final String USER_STATUS_TABLE = REFAPP_SCHEMA_NAME + "." + USER_STATUS_TABLE_NAME;
	public static final String USER_INFO_TABLE = REFAPP_SCHEMA_NAME + "." + USER_INFO_TABLE_NAME;
	public static final String USER_ADDRESS_TABLE = REFAPP_SCHEMA_NAME + "." + USER_ADDRESS_TABLE_NAME;
	public static final String USER_PASSWORD_TABLE = REFAPP_SCHEMA_NAME + "." + USER_PASSWORD_TABLE_NAME;

	@Resource(mappedName = JNDI.REFAPP_DATASOURCE)
	private DataSource dataSource;

	private UserIdRowMapper userIdRowMapper = new UserIdRowMapper();
	private UserRowMapper userRowMapper = new UserRowMapper();
	private UserPasswordRowMapper userPasswordRowMapper = new UserPasswordRowMapper();

	@PostConstruct
	private void initialize() {
		super.initialize(dataSource);
	}

	@Override
	public CreateUserOutput createUser(CreateUserInput input) throws DaoException {
		LOG.info(DAO_NAME + ": Insert user operation invoked.");

		super.doPersistConnection();

		int rowsAffected = 0;
		Id userId = null;
		ChangeResult changeResult;

		if (input.getUser().getUsername() != null && input.getUser().getUsername().getUsername() != null) {
			changeResult = localCreateUser(input);
			rowsAffected += changeResult.getRowsAffected();
			userId = changeResult.getAutoGeneratedKey();

			changeResult = localCreateUserInfo(input, userId);
			rowsAffected += changeResult.getRowsAffected();

			changeResult = localCreateUserAddress(input, userId);
			rowsAffected += changeResult.getRowsAffected();
		}

		super.doCloseConnection();

		return new CreateUserOutput(userId, rowsAffected);
	}

	private ChangeResult localCreateUser(CreateUserInput input) throws DaoException {
		SQL sql = new SQL.Builder().doInsert(USERS_TABLE).columnValues(USERNAME_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(input.getUser().getUsername().getUsername());
		return super.create(sql, parameters);
	}

	private ChangeResult localCreateUserInfo(CreateUserInput input, Id userId) throws DaoException {
		SQL sql = new SQL.Builder()
				.doInsert(USER_INFO_TABLE)
				.columnValues(USER_ID_COLUMN_NAME, FIRSTNAME_COLUMN_NAME, LASTNAME_COLUMN_NAME, BIRTHDATE_COLUMN_NAME,
						GENDER_COLUMN_NAME, EMAIL_COLUMN_NAME, PHONE_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(userId.getId(), input.getUser().getUserInfo().getFirstName(),
				input.getUser().getUserInfo().getLastName(),
				DateTimeConverter.toSqlDate(input.getUser().getUserInfo().getBirthDate()), input.getUser()
						.getUserInfo().getGender().toString(), input.getUser().getUserInfo().getEmail(), input
						.getUser().getUserInfo().getPhone());
		return super.create(sql, parameters);
	}

	private ChangeResult localCreateUserAddress(CreateUserInput input, Id userId) throws DaoException {
		if (input.getUser().getUserAddress() != null) {
			SQL sql = new SQL.Builder()
					.doInsert(USER_ADDRESS_TABLE)
					.columnValues(USER_ID_COLUMN_NAME, ADDRESS_COLUMN_NAME, POSTALCODE_COLUMN_NAME,
							MUNICIPALITY_COLUMN_NAME, REGION_COLUMN_NAME, COUNTRY_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(userId.getId(),
					input.getUser().getUserAddress().getAddress(), input.getUser().getUserAddress().getPostalCode(),
					input.getUser().getUserAddress().getMunicipality(), input.getUser().getUserAddress().getRegion(),
					input.getUser().getUserAddress().getCountry());
			return super.create(sql, parameters);
		} else {
			return new ChangeResult(0);
		}
	}

	@Override
	public CreateUserPasswordOutput createUserPassword(CreateUserPasswordInput input) throws DaoException {
		SQL sql = new SQL.Builder().doInsert(USER_PASSWORD_TABLE)
				.columnValues(USER_ID_COLUMN_NAME, PASSWORD_COLUMN_NAME, SALT_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(input.getUserId().getId(), input.getUserPassword().getPassword(),
				input.getUserPassword().getSalt());
		ChangeResult changeResult = super.create(sql, parameters);
		return new CreateUserPasswordOutput(changeResult.getRowsAffected());
	}

	@Override
	public ReadUserOutput readUser(ReadUserInput input) throws DaoException {
		LOG.info(DAO_NAME + ": Select user operation invoked.");

		SQL sql = new SQL.Builder().doSelect("*").from(USERS_VIEW).where(USERNAME_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(input.getUsername().getUsername());
		List<User> UserList = super.read(sql, userRowMapper, parameters);
		return new ReadUserOutput(UserList);
	}

	@Override
	public ReadUserOutput readAllUsers() throws DaoException {
		LOG.info(DAO_NAME + ": Select all users operation invoked.");

		super.doPersistConnection();

		SQL sql = new SQL.Builder().doSelectAll().from(USERS_VIEW).build();
		List<User> UserList = super.read(sql, userRowMapper);
		return new ReadUserOutput(UserList);
	}

	@Override
	public ReadUserPasswordOutput readUserPassword(ReadUserPasswordInput input) throws DaoException {
		LOG.info(DAO_NAME + ": Select user password operation invoked.");

		Id userId = localReadUserId(input.getUserName());

		SQL sql = new SQL.Builder().doSelect("*").from(USER_PASSWORD_TABLE).where(USER_ID_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(userId.getId());
		List<UserPassword> UserPasswordList = super.read(sql, userPasswordRowMapper, parameters);
		return new ReadUserPasswordOutput(UserPasswordList);
	}

	private Id localReadUserId(String username) throws DaoException {
		SQL sql = new SQL.Builder().doSelect(ID_COLUMN_NAME).from(USERS_TABLE).where(USERNAME_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(username);
		return super.read(sql, userIdRowMapper, parameters).get(0);
	}

	@Override
	public UpdateUserOutput updateUser(UpdateUserInput input) throws DaoException {
		LOG.info(DAO_NAME + ": Update user operation invoked.");

		super.doPersistConnection();

		int rowsAffected = 0;
		ChangeResult changeResult;

		if (input.getUser().getUsername() != null && input.getUser().getUsername().getUsername() != null) {
			Id userId = localReadUserId(input.getUser().getUsername().getUsername());

			changeResult = localUpdateUserInfo(input, userId);
			rowsAffected += changeResult.getRowsAffected();

			changeResult = localUpdateUserStatus(input, userId);
			rowsAffected += changeResult.getRowsAffected();

			changeResult = localUpdateUserAddress(input, userId);
			rowsAffected += changeResult.getRowsAffected();
		}

		super.doCloseConnection();

		return new UpdateUserOutput(rowsAffected);
	}

	private ChangeResult localUpdateUserInfo(UpdateUserInput input, Id userId) throws DaoException {
		if (input.getUser().getUserInfo() != null) {
			SQL sql = new SQL.Builder()
					.doUpdate(USER_INFO_TABLE)
					.setColumns(FIRSTNAME_COLUMN_NAME, LASTNAME_COLUMN_NAME, BIRTHDATE_COLUMN_NAME, GENDER_COLUMN_NAME,
							EMAIL_COLUMN_NAME, PHONE_COLUMN_NAME).where(USER_ID_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(input.getUser().getUserInfo().getFirstName(), input.getUser()
					.getUserInfo().getLastName(),
					DateTimeConverter.toSqlDate(input.getUser().getUserInfo().getBirthDate()), input.getUser()
							.getUserInfo().getGender().toString(), input.getUser().getUserInfo().getEmail(), input
							.getUser().getUserInfo().getPhone(), userId.getId());
			return super.update(sql, parameters);
		} else {
			return new ChangeResult(0);
		}
	}

	private ChangeResult localUpdateUserStatus(UpdateUserInput input, Id userId) throws DaoException {
		if (input.getUser().getUserStatus().getStatus() != null) {
			SQL sql = new SQL.Builder().doUpdate(USER_STATUS_TABLE).setColumn(STATUS_COLUMN_NAME)
					.where(USER_ID_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(input.getUser().getUserStatus().getStatus().toString(),
					userId.getId());
			return super.update(sql, parameters);
		} else {
			return new ChangeResult(0);
		}
	}

	private ChangeResult localUpdateUserAddress(UpdateUserInput input, Id userId) throws DaoException {
		if (input.getUser().getUserAddress() != null) {
			SQL sql = new SQL.Builder()
					.doUpdate(USER_ADDRESS_TABLE)
					.setColumns(ADDRESS_COLUMN_NAME, POSTALCODE_COLUMN_NAME, MUNICIPALITY_COLUMN_NAME,
							REGION_COLUMN_NAME, COUNTRY_COLUMN_NAME).where(USER_ID_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(input.getUser().getUserAddress().getAddress(), input
					.getUser().getUserAddress().getPostalCode(), input.getUser().getUserAddress().getMunicipality(),
					input.getUser().getUserAddress().getRegion(), input.getUser().getUserAddress().getCountry(),
					userId.getId());
			return super.update(sql, parameters);
		} else {
			return new ChangeResult(0);
		}
	}

	@Override
	public UpdateUserPasswordOutput updateUserPassword(UpdateUserPasswordInput input) throws DaoException {
		if (input.getUserPassword() != null) {
			SQL sql = new SQL.Builder().doUpdate(USER_PASSWORD_TABLE).setColumn(PASSWORD_COLUMN_NAME)
					.where(USER_ID_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(input.getUserPassword().getPassword(), input.getUserId()
					.getId());
			ChangeResult changeResult = super.update(sql, parameters);
			return new UpdateUserPasswordOutput(changeResult.getRowsAffected());
		} else {
			return new UpdateUserPasswordOutput(0);
		}
	}

	@Override
	public DeleteUserOutput deleteUser(DeleteUserInput input) throws DaoException {
		LOG.info(DAO_NAME + ": Delete user operation invoked.");

		SQL sql = new SQL.Builder().doDelete(USERS_TABLE).where(USERNAME_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(input.getUserName());
		ChangeResult changeResult = super.delete(sql, parameters);
		return new DeleteUserOutput(changeResult.getRowsAffected());
	}

	@Override
	public PurgeUsersOutput purgeUsers(PurgeUsersInput input) throws DaoException {
		LOG.info(DAO_NAME + ": Purge users operation invoked.");

		SQL sql = new SQL.Builder().doDelete(USERS_TABLE).where(STATUS_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(Status.DELETED.toString());

		if (input.isGreedy()) {
			sql.append(" OR (" + STATUS_COLUMN_NAME + " = ? AND " + CREATED_COLUMN_NAME + " >= ?");
			parameters = DaoHelper.generateArray(Status.DELETED.toString(), Status.PENDING.toString(),
					DateTimeUtils.createCalendar(Calendar.DAY_OF_YEAR, -7));
		}

		ChangeResult changeResult = super.delete(sql, parameters);
		return new PurgeUsersOutput(changeResult.getRowsAffected());
	}
}
