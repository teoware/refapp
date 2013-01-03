package com.teoware.refapp.dao;

import static com.teoware.refapp.dao.metadata.SchemaMetaData.REFAPP_SCHEMA_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.ADDRESS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.BIRTHDATE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.COUNTRY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.EMAIL_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.FIRSTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.GENDER_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.ID_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.LASTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.MUNICIPALITY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.PASSWORD_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.PHONE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.POSTALCODE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.REGION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.SALT_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.USERNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.USERS_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.USERS_VIEW_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.USER_ADDRESS_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.USER_ID_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.USER_INFO_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.USER_PASSWORD_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.USER_STATUS_TABLE_NAME;

import java.math.BigInteger;
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

import com.teoware.refapp.dao.dto.DeleteUserRequest;
import com.teoware.refapp.dao.dto.DeleteUserResponse;
import com.teoware.refapp.dao.dto.InsertUserRequest;
import com.teoware.refapp.dao.dto.InsertUserResponse;
import com.teoware.refapp.dao.dto.PurgeUsersRequest;
import com.teoware.refapp.dao.dto.PurgeUsersResponse;
import com.teoware.refapp.dao.dto.SelectUserPasswordRequest;
import com.teoware.refapp.dao.dto.SelectUserPasswordResponse;
import com.teoware.refapp.dao.dto.SelectUserRequest;
import com.teoware.refapp.dao.dto.SelectUserResponse;
import com.teoware.refapp.dao.dto.UpdateUserRequest;
import com.teoware.refapp.dao.dto.UpdateUserResponse;
import com.teoware.refapp.dao.metadata.JNDI;
import com.teoware.refapp.dao.rowmapper.UserIdRowMapper;
import com.teoware.refapp.dao.rowmapper.UserPasswordRowMapper;
import com.teoware.refapp.dao.rowmapper.UserRowMapper;
import com.teoware.refapp.dao.util.ChangeResult;
import com.teoware.refapp.dao.util.DaoHelper;
import com.teoware.refapp.dao.util.SqlStatement;
import com.teoware.refapp.model.enums.UserStatus;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
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
	public InsertUserResponse insertUser(InsertUserRequest request) throws DaoException {
		LOG.info(DAO_NAME + ": Insert user operation invoked.");

		super.doPersistConnection();

		int rowsAffected = 0;
		ChangeResult changeResult;

		if (request.getUser().getUserId() != null && request.getUser().getUserInfo() != null) {
			changeResult = localInsertUser(request);
			rowsAffected += changeResult.getRowsAffected();
			BigInteger userId = changeResult.getAutoGeneratedKey();

			changeResult = localInsertUserInfo(request, userId);
			rowsAffected += changeResult.getRowsAffected();

			changeResult = localInsertUserPassword(request, userId);
			rowsAffected += changeResult.getRowsAffected();

			changeResult = localInsertUserAddress(request, userId);
			rowsAffected += changeResult.getRowsAffected();
		}

		super.doCloseConnection();

		return new InsertUserResponse(rowsAffected);
	}

	private ChangeResult localInsertUser(InsertUserRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement.Builder().doInsert(USERS_TABLE).columnValues(USERNAME_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(request.getUser().getUserId().getUserName());
		return super.insert(sql, parameters);
	}

	private ChangeResult localInsertUserInfo(InsertUserRequest request, BigInteger userId) throws DaoException {
		SqlStatement sql = new SqlStatement.Builder()
				.doInsert(USER_INFO_TABLE)
				.columnValues(USER_ID_COLUMN_NAME, FIRSTNAME_COLUMN_NAME, LASTNAME_COLUMN_NAME, BIRTHDATE_COLUMN_NAME,
						GENDER_COLUMN_NAME, EMAIL_COLUMN_NAME, PHONE_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(userId, request.getUser().getUserInfo().getFirstName(), request
				.getUser().getUserInfo().getLastName(), request.getUser().getUserInfo().getBirthDate(), request
				.getUser().getUserInfo().getGender().toString(), request.getUser().getUserInfo().getEmail(), request
				.getUser().getUserInfo().getPhone());
		return super.insert(sql, parameters);
	}

	private ChangeResult localInsertUserPassword(InsertUserRequest request, BigInteger userId) throws DaoException {
		SqlStatement sql = new SqlStatement.Builder().doInsert(USER_PASSWORD_TABLE)
				.columnValues(USER_ID_COLUMN_NAME, PASSWORD_COLUMN_NAME, SALT_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(userId, request.getUserPassword().getPassword(), request
				.getUserPassword().getSalt());
		return super.insert(sql, parameters);
	}

	private ChangeResult localInsertUserAddress(InsertUserRequest request, BigInteger userId) throws DaoException {
		if (request.getUser().getUserAddress() != null) {
			SqlStatement sql = new SqlStatement.Builder()
					.doInsert(USER_ADDRESS_TABLE)
					.columnValues(USER_ID_COLUMN_NAME, ADDRESS_COLUMN_NAME, POSTALCODE_COLUMN_NAME,
							MUNICIPALITY_COLUMN_NAME, REGION_COLUMN_NAME, COUNTRY_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(userId, request.getUser().getUserAddress().getAddress(),
					request.getUser().getUserAddress().getPostalCode(), request.getUser().getUserAddress()
							.getMunicipality(), request.getUser().getUserAddress().getRegion(), request.getUser()
							.getUserAddress().getCountry());
			return super.insert(sql, parameters);
		} else {
			return new ChangeResult(0);
		}
	}

	@Override
	public UpdateUserResponse updateUser(UpdateUserRequest request) throws DaoException {
		LOG.info(DAO_NAME + ": Update user operation invoked.");

		super.doPersistConnection();

		int rowsAffected = 0;
		ChangeResult changeResult;

		if (request.getUser().getUserId() != null) {
			BigInteger userId = localSelectUserId(request.getUser().getUserId().getUserName());

			changeResult = localUpdateUserInfo(request, userId);
			rowsAffected += changeResult.getRowsAffected();

			changeResult = localUpdateUserStatus(request, userId);
			rowsAffected += changeResult.getRowsAffected();

			changeResult = localUpdateUserPassword(request, userId);
			rowsAffected += changeResult.getRowsAffected();

			changeResult = localUpdateUserAddress(request, userId);
			rowsAffected += changeResult.getRowsAffected();
		}

		super.doCloseConnection();

		return new UpdateUserResponse(rowsAffected);
	}

	private BigInteger localSelectUserId(String username) throws DaoException {
		SqlStatement sql = new SqlStatement.Builder().doSelect(ID_COLUMN_NAME).from(USERS_TABLE)
				.where(USERNAME_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(username);
		return super.select(sql, userIdRowMapper, parameters).get(0);
	}

	private ChangeResult localUpdateUserInfo(UpdateUserRequest request, BigInteger userId) throws DaoException {
		if (request.getUser().getUserInfo() != null) {
			SqlStatement sql = new SqlStatement.Builder()
					.doUpdate(USER_INFO_TABLE)
					.setColumns(FIRSTNAME_COLUMN_NAME, LASTNAME_COLUMN_NAME, BIRTHDATE_COLUMN_NAME, GENDER_COLUMN_NAME,
							EMAIL_COLUMN_NAME, PHONE_COLUMN_NAME).where(USER_ID_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(request.getUser().getUserInfo().getFirstName(), request
					.getUser().getUserInfo().getLastName(), request.getUser().getUserInfo().getBirthDate(), request
					.getUser().getUserInfo().getGender().toString(), request.getUser().getUserInfo().getEmail(),
					request.getUser().getUserInfo().getPhone(), userId);
			return super.update(sql, parameters);
		} else {
			return new ChangeResult(0);
		}
	}

	private ChangeResult localUpdateUserStatus(UpdateUserRequest request, BigInteger userId) throws DaoException {
		if (request.getUser().getUserId().getStatus() != null) {
			SqlStatement sql = new SqlStatement.Builder().doUpdate(USER_STATUS_TABLE).setColumn(STATUS_COLUMN_NAME)
					.where(USER_ID_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(request.getUser().getUserId().getStatus().toString(), userId);
			return super.update(sql, parameters);
		} else {
			return new ChangeResult(0);
		}
	}

	private ChangeResult localUpdateUserPassword(UpdateUserRequest request, BigInteger userId) throws DaoException {
		if (request.getUserPassword() != null) {
			SqlStatement sql = new SqlStatement.Builder().doUpdate(USER_PASSWORD_TABLE).setColumn(PASSWORD_COLUMN_NAME)
					.where(USER_ID_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(request.getUserPassword().getPassword(), userId);
			return super.update(sql, parameters);
		} else {
			return new ChangeResult(0);
		}
	}

	private ChangeResult localUpdateUserAddress(UpdateUserRequest request, BigInteger userId) throws DaoException {
		if (request.getUser().getUserAddress() != null) {
			SqlStatement sql = new SqlStatement.Builder()
					.doUpdate(USER_ADDRESS_TABLE)
					.setColumns(ADDRESS_COLUMN_NAME, POSTALCODE_COLUMN_NAME, MUNICIPALITY_COLUMN_NAME,
							REGION_COLUMN_NAME, COUNTRY_COLUMN_NAME).where(USER_ID_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(request.getUser().getUserAddress().getAddress(), request
					.getUser().getUserAddress().getPostalCode(), request.getUser().getUserAddress().getMunicipality(),
					request.getUser().getUserAddress().getRegion(), request.getUser().getUserAddress().getCountry(),
					userId);
			return super.update(sql, parameters);
		} else {
			return new ChangeResult(0);
		}
	}

	@Override
	public SelectUserResponse selectUser(SelectUserRequest request) throws DaoException {
		LOG.info(DAO_NAME + ": Select user operation invoked.");

		SqlStatement sql = new SqlStatement.Builder().doSelect("*").from(USERS_VIEW).where(USERNAME_COLUMN_NAME)
				.build();
		Object[] parameters = DaoHelper.generateArray(request.getUsername().getUsername());
		List<User> UserList = super.select(sql, userRowMapper, parameters);
		return new SelectUserResponse(UserList);
	}

	@Override
	public SelectUserResponse selectAllUsers() throws DaoException {
		LOG.info(DAO_NAME + ": Select all users operation invoked.");

		super.doPersistConnection();

		SqlStatement sql = new SqlStatement.Builder().doSelectAll().from(USERS_VIEW).build();
		List<User> UserList = super.select(sql, userRowMapper);
		return new SelectUserResponse(UserList);
	}

	@Override
	public SelectUserPasswordResponse selectUserPassword(SelectUserPasswordRequest request) throws DaoException {
		LOG.info(DAO_NAME + ": Select user password operation invoked.");

		BigInteger userId = localSelectUserId(request.getUserName());

		SqlStatement sql = new SqlStatement.Builder().doSelect("*").from(USER_PASSWORD_TABLE)
				.where(USER_ID_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(userId);
		List<UserPassword> UserPasswordList = super.select(sql, userPasswordRowMapper, parameters);
		return new SelectUserPasswordResponse(UserPasswordList);
	}

	@Override
	public DeleteUserResponse deleteUser(DeleteUserRequest request) throws DaoException {
		LOG.info(DAO_NAME + ": Delete user operation invoked.");

		SqlStatement sql = new SqlStatement.Builder().doDelete(USERS_TABLE).where(USERNAME_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(request.getUserName());
		ChangeResult changeResult = super.delete(sql, parameters);
		return new DeleteUserResponse(changeResult.getRowsAffected());
	}

	@Override
	public PurgeUsersResponse purgeUsers(PurgeUsersRequest request) throws DaoException {
		LOG.info(DAO_NAME + ": Purge users operation invoked.");

		SqlStatement sql = new SqlStatement.Builder().doDelete(USERS_TABLE).where(STATUS_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(UserStatus.DELETED.toString());

		if (request.isGreedy()) {
			sql.append(" OR (" + STATUS_COLUMN_NAME + " = ? AND " + CREATED_COLUMN_NAME + " >= ?");
			parameters = DaoHelper.generateArray(UserStatus.DELETED.toString(), UserStatus.PENDING.toString(),
					DateTimeUtils.createCalendar(Calendar.DAY_OF_YEAR, -7));
		}

		ChangeResult changeResult = super.delete(sql, parameters);
		return new PurgeUsersResponse(changeResult.getRowsAffected());
	}

}
