package com.teoware.refapp.web.ui.author;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.ListAuthorsResponse;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.web.consumer.AuthorServiceConsumer;

@Named("author")
@RequestScoped
public class AuthorPageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String action;

	private String username;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String phone;

	@Inject
	AuthorServiceConsumer serviceConsumer;

	public void onClickRegisterButton() {
		action = "onClickRegisterButton";
		RegisterAuthorRequest req = createRegisterAuthorRequest();
		try {
			serviceConsumer.registerAuthor(req);
		} catch (Exception e) {
			action = getStackTrace(e);
		}
	}

	public void onClickListButton() {
		action = "onClickListButton";
		try {
			ListAuthorsResponse res = serviceConsumer.listAuthors();
			List<Author> list = res.getAuthorList();

			action = "";
			for (Author author : list) {
				action += getAuthorString(author) + "<br />\n";
			}
		} catch (ServiceException e) {
			action = getStackTrace(e);
		}
	}

	public String getAction() {
		return action;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private RegisterAuthorRequest createRegisterAuthorRequest() {
		Author author = BeanFactory.createAuthorBean();

		author.getAuthorId().setUserName(username);
		author.getAuthorId().setStatus(AuthorStatus.PENDING);
		author.getAuthorId().setCreated(Calendar.getInstance());

		author.getAuthorInfo().setFirstName(firstName);
		author.getAuthorInfo().setLastName(lastName);
		author.getAuthorInfo().setGender(Gender.MALE);
		author.getAuthorInfo().setBirthDate(Calendar.getInstance().getTime());
		author.getAuthorInfo().setEmail(email);
		author.getAuthorInfo().setPhone(phone);

		author.getAuthorAddress().setAddress("Abc street 1");
		author.getAuthorAddress().setPostalCode("1234");
		author.getAuthorAddress().setMunicipality("Oslo");
		author.getAuthorAddress().setRegion("Oslo");
		author.getAuthorAddress().setCountry("Norway");

		AuthorPassword authorPassword = BeanFactory.createAuthorPasswordBean();
		authorPassword.setPassword("myPassword");

		return new RegisterAuthorRequest(author, authorPassword);
	}

	private String getAuthorString(Author author) {
		return author.getAuthorId().getUserName();
	}

	private String getStackTrace(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}
}
