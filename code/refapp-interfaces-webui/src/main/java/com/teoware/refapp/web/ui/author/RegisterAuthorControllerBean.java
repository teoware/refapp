package com.teoware.refapp.web.ui.author;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.web.consumer.AuthorServiceConsumer;
import com.teoware.refapp.web.consumer.vo.RegisterAuthorRequestVO;
import com.teoware.refapp.web.ui.AbstractControllerBean;

@Named("registerAuthor")
@RequestScoped
public class RegisterAuthorControllerBean extends AbstractControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_TITLE = "Register author";

	private String action;

	private String username;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String phone;

	@Inject
	AuthorServiceConsumer consumer;

	public void onClickRegisterButton() {
		action = "onClickRegisterButton";
		RegisterAuthorRequestVO vo = createRegisterAuthorRequest();
		try {
			consumer.registerAuthor(vo);
		} catch (Exception e) {
			action = getStackTrace(e);
		}
	}

	private RegisterAuthorRequestVO createRegisterAuthorRequest() {
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

		return new RegisterAuthorRequestVO(author, authorPassword);
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

	@Override
	public String getTitle() {
		return super.getTitle() + PAGE_TITLE;
	}
}
