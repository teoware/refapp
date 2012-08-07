package com.teoware.refapp.dao.impl;

import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.*;
import static com.teoware.refapp.dao.metadata.SchemaMetaData.SCHEMA_NAME;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.dao.BaseDao;
import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.message.DeleteAuthorRequest;
import com.teoware.refapp.dao.message.DeleteAuthorResponse;
import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.dao.message.InsertAuthorResponse;
import com.teoware.refapp.dao.message.PurgeAuthorRequest;
import com.teoware.refapp.dao.message.PurgeAuthorResponse;
import com.teoware.refapp.dao.message.SelectAuthorRequest;
import com.teoware.refapp.dao.message.SelectAuthorResponse;
import com.teoware.refapp.dao.message.UpdateAuthorRequest;
import com.teoware.refapp.dao.message.UpdateAuthorResponse;
import com.teoware.refapp.dao.rowmapper.AuthorRowMapper;
import com.teoware.refapp.dao.sql.SqlStatement;
import com.teoware.refapp.dao.util.DaoHelper;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.enums.AuthorStatus;

@Stateless(mappedName = "AuthorDao")
public class AuthorDaoImpl extends BaseDao<Author> implements AuthorDaoLocal {

	private static final long serialVersionUID = 1L;
	private static final String AUTHOR_VIEW = SCHEMA_NAME + "." + AUTHOR_VIEW_NAME;
	private static final String AUTHOR_TABLE = SCHEMA_NAME + "." + AUTHOR_TABLE_NAME;
	private static final String AUTHOR_STATUS_TABLE = SCHEMA_NAME + "." + AUTHOR_STATUS_TABLE_NAME;
	private static final String AUTHOR_ADDRESS_TABLE = SCHEMA_NAME + "." + AUTHOR_ADDRESS_TABLE_NAME;
	private static final String AUTHOR_PASSWORD_TABLE = SCHEMA_NAME + "." + AUTHOR_PASSWORD_TABLE_NAME;
	
	private AuthorRowMapper rowMapper = new AuthorRowMapper();

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectAuthorResponse selectAuthor(SelectAuthorRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement("SELECT * FROM " + AUTHOR_VIEW + " WHERE " + USERNAME_COLUMN_NAME + " = ?");
		Object[] parameters = DaoHelper.generateArray(request.getUserName());
		List<Author> authorList = super.select(sql, rowMapper, parameters);
		return new SelectAuthorResponse(authorList);
	}

	@Override
	public SelectAuthorResponse selectAuthor() throws DaoException {
		SqlStatement sql = new SqlStatement("SELECT * FROM " + AUTHOR_VIEW);
		List<Author> authorList = super.select(sql, rowMapper);
		return new SelectAuthorResponse(authorList);
	}

	@Override
	public DeleteAuthorResponse deleteAuthor(DeleteAuthorRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement("UPDATE " + AUTHOR_STATUS_TABLE + " SET " + STATUS_COLUMN_NAME +
				" = ? WHERE " + AUTHOR_COLUMN_NAME + " = ?");
		Object[] parameters = DaoHelper.generateArray(AuthorStatus.DELETED.toString(), request.getUserName());
		int rowsAffected = super.update(sql, parameters);
		return new DeleteAuthorResponse(rowsAffected);
	}

	@Override
	public PurgeAuthorResponse purgeAuthor(PurgeAuthorRequest request) throws DaoException {
		SqlStatement sql = new SqlStatement("DELETE FROM " + AUTHOR_TABLE + " WHERE " + USERNAME_COLUMN_NAME + " = ?");
		Object[] parameters = DaoHelper.generateArray(request.getUserName());
		int rowsAffected = super.delete(sql, parameters);
		return new PurgeAuthorResponse(rowsAffected);
	}
}
