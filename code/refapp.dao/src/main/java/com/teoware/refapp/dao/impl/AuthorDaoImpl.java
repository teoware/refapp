package com.teoware.refapp.dao.impl;

import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.ADDRESS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.AUTHOR_ADDRESS_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.AUTHOR_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.AUTHOR_PASSWORD_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.AUTHOR_STATUS_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.AUTHOR_TABLE_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.AUTHOR_VIEW_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.BIRTHDATE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.COUNTRY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.EMAIL_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.FIRSTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.GENDER_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.LASTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.MODIFIED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.MUNICIPALITY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.PASSWORD_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.PHONE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.POSTALCODE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.REGION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.USERNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.SchemaMetaData.SCHEMA_NAME;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.dao.BaseDao;
import com.teoware.refapp.dao.DaoException;
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
import com.teoware.refapp.util.DateUtils;

@Stateless(mappedName = "AuthorDao")
public class AuthorDaoImpl extends BaseDao implements AuthorDaoLocal {

	private static final long serialVersionUID = 1L;
	private static final String AUTHOR_VIEW = SCHEMA_NAME + "." + AUTHOR_VIEW_NAME;
	private static final String AUTHOR_TABLE = SCHEMA_NAME + "." + AUTHOR_TABLE_NAME;
	private static final String AUTHOR_STATUS_TABLE = SCHEMA_NAME + "." + AUTHOR_STATUS_TABLE_NAME;
	private static final String AUTHOR_ADDRESS_TABLE = SCHEMA_NAME + "." + AUTHOR_ADDRESS_TABLE_NAME;
	private static final String AUTHOR_PASSWORD_TABLE = SCHEMA_NAME + "." + AUTHOR_PASSWORD_TABLE_NAME;
	
	private AuthorRowMapper authorRowMapper = new AuthorRowMapper();
	private AuthorPasswordRowMapper authorPasswordRowMapper = new AuthorPasswordRowMapper();

	@Override
	public InsertAuthorResponse insertAuthor(InsertAuthorRequest request) throws DaoException {
		super.setPersistConnection(true);
		
		int rowsAffected = 0;
		SqlStatement sql;
		Object[] parameters;
		
		if (request.getAuthor().getAuthorId() != null && request.getAuthor().getAuthorInfo() != null) {
			sql = new SqlStatement("INSERT INTO " + AUTHOR_TABLE + " (" +
					USERNAME_COLUMN_NAME + ", " +
					FIRSTNAME_COLUMN_NAME + ", " +
					LASTNAME_COLUMN_NAME + ", " + 
					BIRTHDATE_COLUMN_NAME + ", " +
					GENDER_COLUMN_NAME + ", " +
					EMAIL_COLUMN_NAME + ", " +
					PHONE_COLUMN_NAME +
					") VALUES (?, ?, ?, ?, ?, ?, ?)");
			parameters = DaoHelper.generateArray(
					request.getAuthor().getAuthorId().getUserName(),
					request.getAuthor().getAuthorInfo().getFirstName(),
					request.getAuthor().getAuthorInfo().getLastName(),
					request.getAuthor().getAuthorInfo().getBirthDate(),
					request.getAuthor().getAuthorInfo().getGender().toString(),
					request.getAuthor().getAuthorInfo().getEmail(),
					request.getAuthor().getAuthorInfo().getPhone());
			rowsAffected += super.insert(sql, parameters);
			
			Calendar calendar = Calendar.getInstance();
			sql = new SqlStatement("INSERT INTO " + AUTHOR_STATUS_TABLE + " (" +
					AUTHOR_COLUMN_NAME + ", " +
					STATUS_COLUMN_NAME + ", " +
					CREATED_COLUMN_NAME + ", " +
					MODIFIED_COLUMN_NAME +
					") VALUES (?, ?, ?, ?)");
			parameters = DaoHelper.generateArray(
					request.getAuthor().getAuthorId().getUserName(),
					AuthorStatus.PENDING.toString(),
					calendar,
					calendar);
			rowsAffected += super.insert(sql, parameters);
			
			sql = new SqlStatement("INSERT INTO " + AUTHOR_PASSWORD_TABLE + " (" +
					AUTHOR_COLUMN_NAME + ", " +
					PASSWORD_COLUMN_NAME +
					") VALUES (?, ?)");
			parameters = DaoHelper.generateArray(
					request.getAuthor().getAuthorId().getUserName(),
					request.getAuthorPassword().getPassword());
			rowsAffected += super.insert(sql, parameters);
			
			if (request.getAuthor().getAuthorAddress() != null) {
				sql = new SqlStatement("INSERT INTO " + AUTHOR_ADDRESS_TABLE + " (" +
						AUTHOR_COLUMN_NAME + ", " +
						ADDRESS_COLUMN_NAME + ", " +
						POSTALCODE_COLUMN_NAME + ", " +
						MUNICIPALITY_COLUMN_NAME + ", " +
						REGION_COLUMN_NAME + ", " +
						COUNTRY_COLUMN_NAME +
						") VALUES (?, ?, ?, ?, ?, ?)");
				parameters = DaoHelper.generateArray(
						request.getAuthor().getAuthorId().getUserName(),
						request.getAuthor().getAuthorAddress().getAddress(),
						request.getAuthor().getAuthorAddress().getPostalCode(),
						request.getAuthor().getAuthorAddress().getMunicipality(),
						request.getAuthor().getAuthorAddress().getRegion(),
						request.getAuthor().getAuthorAddress().getCountry());
				rowsAffected += super.insert(sql, parameters);
			}
		}
		
		super.setPersistConnection(false);
		
		return new InsertAuthorResponse(rowsAffected);
	}

	@Override
	public UpdateAuthorResponse updateAuthor(UpdateAuthorRequest request) throws DaoException {
		super.setPersistConnection(true);
		
		int rowsAffected = 0;
		SqlStatement sql;
		Object[] parameters;
		
		if (request.getAuthor().getAuthorId() != null) {
			if (request.getAuthor().getAuthorInfo() != null) {
				sql = new SqlStatement("UPDATE " + AUTHOR_TABLE + " SET " +
						FIRSTNAME_COLUMN_NAME + " = ?, " +
						LASTNAME_COLUMN_NAME + " = ?, " + 
						BIRTHDATE_COLUMN_NAME + " = ?, " +
						GENDER_COLUMN_NAME + " = ?, " +
						EMAIL_COLUMN_NAME + " = ?, " +
						PHONE_COLUMN_NAME + " = ? WHERE " +
						USERNAME_COLUMN_NAME + " = ?");
				parameters = DaoHelper.generateArray(
						request.getAuthor().getAuthorInfo().getFirstName(),
						request.getAuthor().getAuthorInfo().getLastName(),
						request.getAuthor().getAuthorInfo().getBirthDate(),
						request.getAuthor().getAuthorInfo().getGender().toString(),
						request.getAuthor().getAuthorInfo().getEmail(),
						request.getAuthor().getAuthorInfo().getPhone(),
						request.getAuthor().getAuthorId().getUserName());
				rowsAffected += super.update(sql, parameters);
			}
			
			Calendar calendar = Calendar.getInstance();
			sql = new SqlStatement("UPDATE " + AUTHOR_STATUS_TABLE + " SET " +
					STATUS_COLUMN_NAME + " = ?, " +
					MODIFIED_COLUMN_NAME + " = ? WHERE " +
					AUTHOR_COLUMN_NAME + " = ?");
			parameters = DaoHelper.generateArray(
					request.getAuthor().getAuthorId().getStatus().toString(),
					calendar,
					request.getAuthor().getAuthorId().getUserName());
			rowsAffected += super.update(sql, parameters);
			
			if (request.getAuthorPassword() != null) {
				sql = new SqlStatement("UPDATE " + AUTHOR_PASSWORD_TABLE + " SET " +
						PASSWORD_COLUMN_NAME + " = ? WHERE " +
						AUTHOR_COLUMN_NAME + " = ?");
				parameters = DaoHelper.generateArray(
						request.getAuthorPassword().getPassword(),
						request.getAuthor().getAuthorId().getUserName());
				rowsAffected += super.update(sql, parameters);
			}
			
			if (request.getAuthor().getAuthorAddress() != null) {
				sql = new SqlStatement("UPDATE " + AUTHOR_ADDRESS_TABLE + " SET " +
						ADDRESS_COLUMN_NAME + " = ?, " +
						POSTALCODE_COLUMN_NAME + " = ?, " +
						MUNICIPALITY_COLUMN_NAME + " = ?, " +
						REGION_COLUMN_NAME + " = ?, " +
						COUNTRY_COLUMN_NAME + " = ? WHERE " +
						AUTHOR_COLUMN_NAME + " = ?");
				parameters = DaoHelper.generateArray(
						request.getAuthor().getAuthorAddress().getAddress(),
						request.getAuthor().getAuthorAddress().getPostalCode(),
						request.getAuthor().getAuthorAddress().getMunicipality(),
						request.getAuthor().getAuthorAddress().getRegion(),
						request.getAuthor().getAuthorAddress().getCountry(),
						request.getAuthor().getAuthorId().getUserName());
				rowsAffected += super.update(sql, parameters);
			}
		}
		
		super.setPersistConnection(false);
		
		return new UpdateAuthorResponse(rowsAffected);
	}

	@Override
	public SelectAuthorResponse selectAuthor(SelectAuthorRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement("SELECT * FROM " + AUTHOR_VIEW + " WHERE " + USERNAME_COLUMN_NAME + " = ?");
		Object[] parameters = DaoHelper.generateArray(request.getUserName());
		List<Author> authorList = super.select(sql, authorRowMapper, parameters);
		return new SelectAuthorResponse(authorList);
	}

	@Override
	public SelectAuthorResponse selectAuthor() throws DaoException {
		SqlStatement sql = new SqlStatement("SELECT * FROM " + AUTHOR_VIEW);
		List<Author> authorList = super.select(sql, authorRowMapper);
		return new SelectAuthorResponse(authorList);
	}

	@Override
	public SelectAuthorPasswordResponse selectAuthorPassword(SelectAuthorPasswordRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement("SELECT * FROM " + AUTHOR_PASSWORD_TABLE);
		List<AuthorPassword> authorPasswordList = super.select(sql, authorPasswordRowMapper);
		return new SelectAuthorPasswordResponse(authorPasswordList);
	}

	@Override
	public DeleteAuthorResponse deleteAuthor(DeleteAuthorRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement("DELETE FROM " + AUTHOR_TABLE + " WHERE " + USERNAME_COLUMN_NAME + " = ?");
		Object[] parameters = DaoHelper.generateArray(request.getUserName());
		int rowsAffected = super.delete(sql, parameters);
		return new DeleteAuthorResponse(rowsAffected);
	}

	@Override
	public PurgeAuthorsResponse purgeAuthors(PurgeAuthorsRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement("DELETE FROM " + AUTHOR_TABLE + " WHERE " + STATUS_COLUMN_NAME + " = ?");
		Object[] parameters = DaoHelper.generateArray(AuthorStatus.DELETED.toString());
		
		if (request.isGreedy()) {
			sql.appendStatement(" AND (" + STATUS_COLUMN_NAME + " = ? AND " + CREATED_COLUMN_NAME + " >= ?");
			parameters = DaoHelper.generateArray(
					AuthorStatus.DELETED.toString(),
					AuthorStatus.PENDING.toString(),
					DateUtils.createCalendar(Calendar.DATE, -7));
		}
		
		int rowsAffected = super.delete(sql, parameters);
		return new PurgeAuthorsResponse(rowsAffected);
	}
}
