package com.teoware.refapp.dao;

import com.teoware.refapp.dao.dto.CreateUserAddressInput;
import com.teoware.refapp.dao.dto.CreateUserAddressOutput;
import com.teoware.refapp.dao.dto.CreateUserDetailsInput;
import com.teoware.refapp.dao.dto.CreateUserDetailsOutput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.CreateUserPasswordInput;
import com.teoware.refapp.dao.dto.CreateUserPasswordOutput;
import com.teoware.refapp.dao.dto.DeleteUserAddressInput;
import com.teoware.refapp.dao.dto.DeleteUserAddressOutput;
import com.teoware.refapp.dao.dto.DeleteUserDetailsInput;
import com.teoware.refapp.dao.dto.DeleteUserDetailsOutput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserOutput;
import com.teoware.refapp.dao.dto.DeleteUserPasswordInput;
import com.teoware.refapp.dao.dto.DeleteUserPasswordOutput;
import com.teoware.refapp.dao.dto.DeleteUserStatusInput;
import com.teoware.refapp.dao.dto.DeleteUserStatusOutput;
import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.PurgeUsersOutput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.ReadUserPasswordInput;
import com.teoware.refapp.dao.dto.ReadUserPasswordOutput;
import com.teoware.refapp.dao.dto.ReadUsersInput;
import com.teoware.refapp.dao.dto.ReadUsersOutput;
import com.teoware.refapp.dao.dto.UpdateUserAddressInput;
import com.teoware.refapp.dao.dto.UpdateUserAddressOutput;
import com.teoware.refapp.dao.dto.UpdateUserDetailsInput;
import com.teoware.refapp.dao.dto.UpdateUserDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordInput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordOutput;
import com.teoware.refapp.dao.dto.UpdateUserStatusInput;
import com.teoware.refapp.dao.dto.UpdateUserStatusOutput;
import com.teoware.refapp.dao.metadata.JNDI;
import com.teoware.refapp.dao.metadata.Schema;
import com.teoware.refapp.dao.metadata.UserTables;
import com.teoware.refapp.dao.rowmapper.IdRowMapper;
import com.teoware.refapp.dao.rowmapper.UserPasswordRowMapper;
import com.teoware.refapp.dao.rowmapper.UserRowMapper;
import com.teoware.refapp.dao.util.ChangeResult;
import com.teoware.refapp.dao.util.DaoHelper;
import com.teoware.refapp.dao.util.SQL;
import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.time.DateTimeConverter;
import com.teoware.refapp.time.DateTimeUtils;
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

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserDaoBean extends Dao implements UserDao {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(UserDaoBean.class);

    private static final String DAO_NAME = "User DAO";

    public static final String USERS_VIEW = Schema.REFAPP_SCHEMA_NAME + "." + UserTables.USERS_VIEW_NAME;
    public static final String USERS_TABLE = Schema.REFAPP_SCHEMA_NAME + "." + UserTables.USERS_TABLE_NAME;
    public static final String USER_STATUS_TABLE = Schema.REFAPP_SCHEMA_NAME + "." + UserTables.USER_STATUS_TABLE_NAME;
    public static final String USER_DETAILS_TABLE = Schema.REFAPP_SCHEMA_NAME + "."
            + UserTables.USER_DETAILS_TABLE_NAME;
    public static final String USER_ADDRESS_TABLE = Schema.REFAPP_SCHEMA_NAME + "."
            + UserTables.USER_ADDRESS_TABLE_NAME;
    public static final String USER_PASSWORD_TABLE = Schema.REFAPP_SCHEMA_NAME + "."
            + UserTables.USER_PASSWORD_TABLE_NAME;

    @Resource(mappedName = JNDI.REFAPP_DATASOURCE)
    private DataSource dataSource;

    private final IdRowMapper idRowMapper = new IdRowMapper();
    private final UserRowMapper userRowMapper = new UserRowMapper();
    private final UserPasswordRowMapper userPasswordRowMapper = new UserPasswordRowMapper();

    @PostConstruct
    private void initialize() {
        super.initialize(dataSource);
    }

    @Override
    public CreateUserOutput createUser(CreateUserInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Create user operation invoked.");

        SQL sql = new SQL.Builder().doInsert(USERS_TABLE).columnValues(UserTables.USERNAME_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getUsername());
        ChangeResult changeResult = super.create(sql, parameters);
        int rowsAffected = changeResult.getRowsAffected();
        Id userId = changeResult.getAutoGeneratedKey();
        return new CreateUserOutput(userId, rowsAffected);
    }

    @Override
    public CreateUserDetailsOutput createUserDetails(CreateUserDetailsInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Create user info operation invoked.");

        SQL sql = new SQL.Builder()
                .doInsert(USER_DETAILS_TABLE)
                .columnValues(UserTables.USER_ID_COLUMN_NAME, UserTables.FIRSTNAME_COLUMN_NAME,
                        UserTables.LASTNAME_COLUMN_NAME, UserTables.BIRTHDATE_COLUMN_NAME,
                        UserTables.GENDER_COLUMN_NAME, UserTables.EMAIL_COLUMN_NAME, UserTables.PHONE_COLUMN_NAME)
                .build();
        Object[] parameters = DaoHelper.generateArray(input.getId(), input.getUserDetails().getFirstName(), input
                .getUserDetails().getLastName(), DateTimeConverter.toSqlDate(input.getUserDetails().getBirthDate()),
                input.getUserDetails().getGender(), input.getUserDetails().getEmail(), input.getUserDetails()
                .getPhone());
        ChangeResult changeResult = super.create(sql, parameters);
        int rowsAffected = changeResult.getRowsAffected();
        return new CreateUserDetailsOutput(rowsAffected);
    }

    @Override
    public CreateUserAddressOutput createUserAddress(CreateUserAddressInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Create user address operation invoked.");

        if (input.getUserAddress() != null) {
            SQL sql = new SQL.Builder()
                    .doInsert(USER_ADDRESS_TABLE)
                    .columnValues(UserTables.USER_ID_COLUMN_NAME, UserTables.ADDRESS_COLUMN_NAME,
                            UserTables.POSTALCODE_COLUMN_NAME, UserTables.MUNICIPALITY_COLUMN_NAME,
                            UserTables.REGION_COLUMN_NAME, UserTables.COUNTRY_COLUMN_NAME).build();
            Object[] parameters = DaoHelper.generateArray(input.getId(), input.getUserAddress().getAddress(), input
                    .getUserAddress().getPostalCode(), input.getUserAddress().getMunicipality(), input.getUserAddress()
                    .getRegion(), input.getUserAddress().getCountry());
            ChangeResult changeResult = super.create(sql, parameters);
            return new CreateUserAddressOutput(changeResult.getRowsAffected());
        } else {
            return new CreateUserAddressOutput(0);
        }
    }

    @Override
    public CreateUserPasswordOutput createUserPassword(CreateUserPasswordInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Create user password operation invoked.");

        SQL sql = new SQL.Builder()
                .doInsert(USER_PASSWORD_TABLE)
                .columnValues(UserTables.USER_ID_COLUMN_NAME, UserTables.PASSWORD_COLUMN_NAME,
                        UserTables.SALT_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getId(), input.getUserPassword().getPassword(), input
                .getUserPassword().getSalt());
        ChangeResult changeResult = super.create(sql, parameters);
        return new CreateUserPasswordOutput(changeResult.getRowsAffected());
    }

    @Override
    public Id readUserId(Username username) throws DaoException {
        LOG.info(DAO_NAME + ": Read user ID operation invoked.");

        SQL sql = new SQL.Builder().doSelect(UserTables.ID_COLUMN_NAME).from(USERS_TABLE)
                .where(UserTables.USERNAME_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(username);
        List<Id> userList = super.read(sql, idRowMapper, parameters);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public ReadUserOutput readUser(ReadUserInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Read user operation invoked.");

        SQL sql = new SQL.Builder().doSelectAll().from(USERS_VIEW).where(UserTables.USERNAME_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getUsername());
        List<User> userList = super.read(sql, userRowMapper, parameters);
        return new ReadUserOutput(userList);
    }

    @Override
    public ReadUsersOutput readUsers(ReadUsersInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Read users operation invoked.");

        SQL sql = new SQL.Builder().doSelectAll().from(USERS_VIEW).where(UserTables.STATUS_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getStatus());
        List<User> userList = super.read(sql, userRowMapper, parameters);
        return new ReadUsersOutput(userList);
    }

    @Override
    public ReadUserPasswordOutput readUserPassword(ReadUserPasswordInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Read user password operation invoked.");

        SQL sql = new SQL.Builder().doSelectAll().from(USER_PASSWORD_TABLE).where(UserTables.USER_ID_COLUMN_NAME)
                .build();
        Object[] parameters = DaoHelper.generateArray(input.getUserId());
        List<UserPassword> userPasswordList = super.read(sql, userPasswordRowMapper, parameters);
        return new ReadUserPasswordOutput(userPasswordList);
    }

    @Override
    public UpdateUserOutput updateUser(UpdateUserInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Update user operation invoked.");

        if (input.getUsername() != null) {
            SQL sql = new SQL.Builder().doUpdate(USERS_TABLE).setColumn(UserTables.USERNAME_COLUMN_NAME)
                    .where(UserTables.ID_COLUMN_NAME).build();
            Object[] parameters = DaoHelper.generateArray(input.getUsername(), input.getId());
            ChangeResult changeResult = super.update(sql, parameters);
            return new UpdateUserOutput(changeResult.getRowsAffected());
        } else {
            return new UpdateUserOutput(0);
        }
    }

    @Override
    public UpdateUserDetailsOutput updateUserDetails(UpdateUserDetailsInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Update user info operation invoked.");

        if (input.getUserDetails() != null) {
            SQL sql = new SQL.Builder()
                    .doUpdate(USER_DETAILS_TABLE)
                    .setColumns(UserTables.FIRSTNAME_COLUMN_NAME, UserTables.LASTNAME_COLUMN_NAME,
                            UserTables.BIRTHDATE_COLUMN_NAME, UserTables.GENDER_COLUMN_NAME,
                            UserTables.EMAIL_COLUMN_NAME, UserTables.PHONE_COLUMN_NAME)
                    .where(UserTables.USER_ID_COLUMN_NAME).build();
            Object[] parameters = DaoHelper.generateArray(input.getUserDetails().getFirstName(), input.getUserDetails()
                    .getLastName(), DateTimeConverter.toSqlDate(input.getUserDetails().getBirthDate()), input
                    .getUserDetails().getGender().toString(), input.getUserDetails().getEmail(), input.getUserDetails()
                    .getPhone(), input.getId());
            ChangeResult result = super.update(sql, parameters);
            return new UpdateUserDetailsOutput(result.getRowsAffected());
        } else {
            return new UpdateUserDetailsOutput(0);
        }
    }

    @Override
    public UpdateUserStatusOutput updateUserStatus(UpdateUserStatusInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Update user status operation invoked.");

        if (input.getUserStatus() != null) {
            SQL sql = new SQL.Builder().doUpdate(USER_STATUS_TABLE).setColumn(UserTables.STATUS_COLUMN_NAME)
                    .where(UserTables.USER_ID_COLUMN_NAME).build();
            Object[] parameters = DaoHelper.generateArray(input.getUserStatus().getStatus(), input.getId());
            ChangeResult result = super.update(sql, parameters);
            return new UpdateUserStatusOutput(result.getRowsAffected());
        } else {
            return new UpdateUserStatusOutput(0);
        }
    }

    @Override
    public UpdateUserAddressOutput updateUserAddress(UpdateUserAddressInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Update user address operation invoked.");

        if (input.getUserAddress() != null) {
            SQL sql = new SQL.Builder()
                    .doUpdate(USER_ADDRESS_TABLE)
                    .setColumns(UserTables.ADDRESS_COLUMN_NAME, UserTables.POSTALCODE_COLUMN_NAME,
                            UserTables.MUNICIPALITY_COLUMN_NAME, UserTables.REGION_COLUMN_NAME,
                            UserTables.COUNTRY_COLUMN_NAME).where(UserTables.USER_ID_COLUMN_NAME).build();
            Object[] parameters = DaoHelper.generateArray(input.getUserAddress().getAddress(), input.getUserAddress()
                    .getPostalCode(), input.getUserAddress().getMunicipality(), input.getUserAddress().getRegion(),
                    input.getUserAddress().getCountry(), input.getId());
            ChangeResult result = super.update(sql, parameters);
            return new UpdateUserAddressOutput(result.getRowsAffected());
        } else {
            return new UpdateUserAddressOutput(0);
        }
    }

    @Override
    public UpdateUserPasswordOutput updateUserPassword(UpdateUserPasswordInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Update user password operation invoked.");

        if (input.getUserPassword() != null) {
            SQL sql = new SQL.Builder().doUpdate(USER_PASSWORD_TABLE).setColumn(UserTables.PASSWORD_COLUMN_NAME)
                    .where(UserTables.USER_ID_COLUMN_NAME).build();
            Object[] parameters = DaoHelper.generateArray(input.getUserPassword().getPassword(), input.getId());
            ChangeResult changeResult = super.update(sql, parameters);
            return new UpdateUserPasswordOutput(changeResult.getRowsAffected());
        } else {
            return new UpdateUserPasswordOutput(0);
        }
    }

    @Override
    public DeleteUserOutput deleteUser(DeleteUserInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Delete user operation invoked.");

        SQL sql = new SQL.Builder().doDelete(USERS_TABLE).where(UserTables.ID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getId());
        ChangeResult changeResult = super.delete(sql, parameters);
        return new DeleteUserOutput(changeResult.getRowsAffected());
    }

    @Override
    public DeleteUserDetailsOutput deleteUserDetails(DeleteUserDetailsInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Delete user details operation invoked.");

        SQL sql = new SQL.Builder().doDelete(USER_DETAILS_TABLE).where(UserTables.USER_ID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getId());
        ChangeResult changeResult = super.delete(sql, parameters);
        return new DeleteUserDetailsOutput(changeResult.getRowsAffected());
    }

    @Override
    public DeleteUserStatusOutput deleteUserStatus(DeleteUserStatusInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Delete user status operation invoked.");

        SQL sql = new SQL.Builder().doDelete(USER_STATUS_TABLE).where(UserTables.USER_ID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getId());
        ChangeResult changeResult = super.delete(sql, parameters);
        return new DeleteUserStatusOutput(changeResult.getRowsAffected());
    }

    @Override
    public DeleteUserAddressOutput deleteUserAddress(DeleteUserAddressInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Delete user address operation invoked.");

        SQL sql = new SQL.Builder().doDelete(USER_ADDRESS_TABLE).where(UserTables.USER_ID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getId());
        ChangeResult changeResult = super.delete(sql, parameters);
        return new DeleteUserAddressOutput(changeResult.getRowsAffected());
    }

    @Override
    public DeleteUserPasswordOutput deleteUserPassword(DeleteUserPasswordInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Delete user password operation invoked.");

        SQL sql = new SQL.Builder().doDelete(USER_PASSWORD_TABLE).where(UserTables.USER_ID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getId());
        ChangeResult changeResult = super.delete(sql, parameters);
        return new DeleteUserPasswordOutput(changeResult.getRowsAffected());
    }

    @Override
    public PurgeUsersOutput purgeUsers(PurgeUsersInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Purge users operation invoked.");

        SQL sql = new SQL.Builder().doDelete(USERS_TABLE).where(UserTables.STATUS_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(Status.DELETED);
        if (input.isGreedy()) {
            sql.append(" OR (" + UserTables.STATUS_COLUMN_NAME + " = ? AND " + UserTables.CREATED_COLUMN_NAME + " >= ?");
            parameters = DaoHelper.generateArray(Status.DELETED, Status.PENDING,
                    DateTimeUtils.createCalendar(Calendar.DAY_OF_YEAR, -input.getUserActivationPeriod()));
        }
        ChangeResult changeResult = super.delete(sql, parameters);
        return new PurgeUsersOutput(changeResult.getRowsAffected());
    }

    @Override
    public void persistConnection() {
        super.setPersistConnection(Boolean.TRUE);
    }

    @Override
    public void terminateConnection() {
        super.setPersistConnection(Boolean.FALSE);
        super.closeConnection();
    }
}
