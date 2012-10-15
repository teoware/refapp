package com.teoware.refapp.dao;

import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.ADDRESS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.AUTHORS_ADDRESS_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.AUTHORS_PASSWORD_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.AUTHORS_STATUS_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.AUTHORS_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.AUTHORS_VIEW_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.AUTHOR_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.BIRTHDATE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.COUNTRY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.EMAIL_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.FIRSTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.GENDER_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.LASTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.MUNICIPALITY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.PASSWORD_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.PHONE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.POSTALCODE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.REGION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.USERNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.SchemaMetaData.REFAPP_SCHEMA_NAME;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.dao.dto.DeleteAuthorRequest;
import com.teoware.refapp.dao.dto.DeleteAuthorResponse;
import com.teoware.refapp.dao.dto.InsertAuthorRequest;
import com.teoware.refapp.dao.dto.InsertAuthorResponse;
import com.teoware.refapp.dao.dto.PurgeAuthorsRequest;
import com.teoware.refapp.dao.dto.PurgeAuthorsResponse;
import com.teoware.refapp.dao.dto.SelectAuthorPasswordRequest;
import com.teoware.refapp.dao.dto.SelectAuthorPasswordResponse;
import com.teoware.refapp.dao.dto.SelectAuthorRequest;
import com.teoware.refapp.dao.dto.SelectAuthorResponse;
import com.teoware.refapp.dao.dto.UpdateAuthorRequest;
import com.teoware.refapp.dao.dto.UpdateAuthorResponse;
import com.teoware.refapp.dao.rowmapper.AuthorPasswordRowMapper;
import com.teoware.refapp.dao.rowmapper.AuthorRowMapper;
import com.teoware.refapp.dao.sql.SqlStatement;
import com.teoware.refapp.dao.util.DaoHelper;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.util.time.DateTimeUtils;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AuthorDaoBean extends BaseDao implements AuthorDao {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AuthorDaoBean.class);

	private static final String DAO_NAME = "Author DAO";

	public static final String AUTHORS_VIEW = REFAPP_SCHEMA_NAME + "." + AUTHORS_VIEW_NAME;
	public static final String AUTHORS_TABLE = REFAPP_SCHEMA_NAME + "." + AUTHORS_TABLE_NAME;
	public static final String AUTHORS_STATUS_TABLE = REFAPP_SCHEMA_NAME + "." + AUTHORS_STATUS_TABLE_NAME;
	public static final String AUTHORS_ADDRESS_TABLE = REFAPP_SCHEMA_NAME + "." + AUTHORS_ADDRESS_TABLE_NAME;
	public static final String AUTHORS_PASSWORD_TABLE = REFAPP_SCHEMA_NAME + "." + AUTHORS_PASSWORD_TABLE_NAME;

	private AuthorRowMapper authorRowMapper = new AuthorRowMapper();
	private AuthorPasswordRowMapper authorPasswordRowMapper = new AuthorPasswordRowMapper();

	@Override
	public InsertAuthorResponse insertAuthor(InsertAuthorRequest request) throws DaoException {
		LOG.info(DAO_NAME + ": Insert author operation invoked.");

		super.doPersistConnection();

		int rowsAffected = 0;

		if (request.getAuthor().getAuthorId() != null && request.getAuthor().getAuthorInfo() != null) {
			rowsAffected += localInsertAuthor(request);

			rowsAffected += localInsertAuthorPassword(request);

			rowsAffected += localInsertAuthorAddress(request);
		}

		super.doCloseConnection();

		return new InsertAuthorResponse(rowsAffected);
	}

	private int localInsertAuthor(InsertAuthorRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement.Builder()
				.doInsert(AUTHORS_TABLE)
				.columnValues(USERNAME_COLUMN_NAME, FIRSTNAME_COLUMN_NAME, LASTNAME_COLUMN_NAME, BIRTHDATE_COLUMN_NAME,
						GENDER_COLUMN_NAME, EMAIL_COLUMN_NAME, PHONE_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(request.getAuthor().getAuthorId().getUserName(), request
				.getAuthor().getAuthorInfo().getFirstName(), request.getAuthor().getAuthorInfo().getLastName(), request
				.getAuthor().getAuthorInfo().getBirthDate(),
				request.getAuthor().getAuthorInfo().getGender().toString(), request.getAuthor().getAuthorInfo()
						.getEmail(), request.getAuthor().getAuthorInfo().getPhone());
		return super.insert(sql, parameters);
	}

	private int localInsertAuthorPassword(InsertAuthorRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement.Builder().doInsert(AUTHORS_PASSWORD_TABLE)
				.columnValues(AUTHOR_COLUMN_NAME, PASSWORD_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(request.getAuthor().getAuthorId().getUserName(), request
				.getAuthorPassword().getPassword());
		return super.insert(sql, parameters);
	}

	private int localInsertAuthorAddress(InsertAuthorRequest request) throws DaoException {
		if (request.getAuthor().getAuthorAddress() != null) {
			SqlStatement sql = new SqlStatement.Builder()
					.doInsert(AUTHORS_ADDRESS_TABLE)
					.columnValues(AUTHOR_COLUMN_NAME, ADDRESS_COLUMN_NAME, POSTALCODE_COLUMN_NAME,
							MUNICIPALITY_COLUMN_NAME, REGION_COLUMN_NAME, COUNTRY_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(request.getAuthor().getAuthorId().getUserName(), request
					.getAuthor().getAuthorAddress().getAddress(), request.getAuthor().getAuthorAddress()
					.getPostalCode(), request.getAuthor().getAuthorAddress().getMunicipality(), request.getAuthor()
					.getAuthorAddress().getRegion(), request.getAuthor().getAuthorAddress().getCountry());
			return super.insert(sql, parameters);
		} else {
			return 0;
		}
	}

	@Override
	public UpdateAuthorResponse updateAuthor(UpdateAuthorRequest request) throws DaoException {
		super.doPersistConnection();

		int rowsAffected = 0;

		if (request.getAuthor().getAuthorId() != null) {
			rowsAffected += localUpdateAuthor(request);

			rowsAffected += localUpdateAuthorStatus(request);

			rowsAffected += localUpdateAuthorPassword(request);

			rowsAffected += localUpdateAuthorAddress(request);
		}

		super.doCloseConnection();

		return new UpdateAuthorResponse(rowsAffected);
	}

	private int localUpdateAuthor(UpdateAuthorRequest request) throws DaoException {
		if (request.getAuthor().getAuthorInfo() != null) {
			SqlStatement sql = new SqlStatement.Builder()
					.doUpdate(AUTHORS_TABLE)
					.setColumns(FIRSTNAME_COLUMN_NAME, LASTNAME_COLUMN_NAME, BIRTHDATE_COLUMN_NAME,
							GENDER_COLUMN_NAME, EMAIL_COLUMN_NAME, PHONE_COLUMN_NAME).where(USERNAME_COLUMN_NAME)
					.build();
			Object[] parameters = DaoHelper.generateArray(request.getAuthor().getAuthorInfo().getFirstName(), request
					.getAuthor().getAuthorInfo().getLastName(), request.getAuthor().getAuthorInfo().getBirthDate(),
					request.getAuthor().getAuthorInfo().getGender().toString(), request.getAuthor().getAuthorInfo()
							.getEmail(), request.getAuthor().getAuthorInfo().getPhone(), request.getAuthor()
							.getAuthorId().getUserName());
			return super.update(sql, parameters);
		} else {
			return 0;
		}
	}

	private int localUpdateAuthorStatus(UpdateAuthorRequest request) throws DaoException {
		if (request.getAuthor().getAuthorId().getStatus() != null) {
			SqlStatement sql = new SqlStatement.Builder().doUpdate(AUTHORS_STATUS_TABLE)
					.setColumn(STATUS_COLUMN_NAME).where(AUTHOR_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(request.getAuthor().getAuthorId().getStatus().toString(),
					request.getAuthor().getAuthorId().getUserName());
			return super.update(sql, parameters);
		} else {
			return 0;
		}
	}

	private int localUpdateAuthorPassword(UpdateAuthorRequest request) throws DaoException {
		if (request.getAuthorPassword() != null) {
			SqlStatement sql = new SqlStatement.Builder().doUpdate(AUTHORS_PASSWORD_TABLE)
					.setColumn(PASSWORD_COLUMN_NAME).where(AUTHOR_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(request.getAuthorPassword().getPassword(), request
					.getAuthor().getAuthorId().getUserName());
			return super.update(sql, parameters);
		} else {
			return 0;
		}
	}

	private int localUpdateAuthorAddress(UpdateAuthorRequest request) throws DaoException {
		if (request.getAuthor().getAuthorAddress() != null) {
			SqlStatement sql = new SqlStatement.Builder()
					.doUpdate(AUTHORS_ADDRESS_TABLE)
					.setColumns(ADDRESS_COLUMN_NAME, POSTALCODE_COLUMN_NAME, MUNICIPALITY_COLUMN_NAME,
							REGION_COLUMN_NAME, COUNTRY_COLUMN_NAME).where(AUTHOR_COLUMN_NAME).build();
			Object[] parameters = DaoHelper.generateArray(request.getAuthor().getAuthorAddress().getAddress(), request
					.getAuthor().getAuthorAddress().getPostalCode(), request.getAuthor().getAuthorAddress()
					.getMunicipality(), request.getAuthor().getAuthorAddress().getRegion(), request.getAuthor()
					.getAuthorAddress().getCountry(), request.getAuthor().getAuthorId().getUserName());
			return super.update(sql, parameters);
		} else {
			return 0;
		}
	}

	@Override
	public SelectAuthorResponse selectAuthor(SelectAuthorRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement.Builder().doSelect("*").from(AUTHORS_VIEW).where(USERNAME_COLUMN_NAME)
				.build();
		Object[] parameters = DaoHelper.generateArray(request.getUserName());
		List<Author> authorList = super.select(sql, authorRowMapper, parameters);
		return new SelectAuthorResponse(authorList);
	}

	@Override
	public SelectAuthorResponse selectAllAuthors() throws DaoException {
		SqlStatement sql = new SqlStatement.Builder().doSelect("*").from(AUTHORS_VIEW).build();
		List<Author> authorList = super.select(sql, authorRowMapper);
		return new SelectAuthorResponse(authorList);
	}

	@Override
	public SelectAuthorPasswordResponse selectAuthorPassword(SelectAuthorPasswordRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement.Builder().doSelect("*").from(AUTHORS_PASSWORD_TABLE)
				.where(AUTHOR_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(request.getUserName());
		List<AuthorPassword> authorPasswordList = super.select(sql, authorPasswordRowMapper, parameters);
		return new SelectAuthorPasswordResponse(authorPasswordList);
	}

	@Override
	public DeleteAuthorResponse deleteAuthor(DeleteAuthorRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement.Builder().doDelete(AUTHORS_TABLE).where(USERNAME_COLUMN_NAME).build();
		Object[] parameters = DaoHelper.generateArray(request.getUserName());
		int rowsAffected = super.delete(sql, parameters);
		return new DeleteAuthorResponse(rowsAffected);
	}

	@Override
	public PurgeAuthorsResponse purgeAuthors(PurgeAuthorsRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement("DELETE FROM " + AUTHORS_TABLE + " WHERE " + STATUS_COLUMN_NAME + " = ?");
		Object[] parameters = DaoHelper.generateArray(AuthorStatus.DELETED.toString());

		if (request.isGreedy()) {
			sql.append(" AND (" + STATUS_COLUMN_NAME + " = ? AND " + CREATED_COLUMN_NAME + " >= ?");
			parameters = DaoHelper.generateArray(AuthorStatus.DELETED.toString(), AuthorStatus.PENDING.toString(),
					DateTimeUtils.createCalendar(Calendar.DATE, -7));
		}

		int rowsAffected = super.delete(sql, parameters);
		return new PurgeAuthorsResponse(rowsAffected);
	}
}
