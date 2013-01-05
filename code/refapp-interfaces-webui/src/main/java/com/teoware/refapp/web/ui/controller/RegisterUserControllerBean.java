package com.teoware.refapp.web.ui.controller;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.UserStatus;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.web.consumer.UserServiceConsumer;
import com.teoware.refapp.web.consumer.vo.RegisterUserRequestVO;

@Named
@RequestScoped
public class RegisterUserControllerBean extends AbstractControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_TITLE = "Register user";

	private String username;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String phone;

	@Inject
	UserServiceConsumer consumer;

	public void onClickRegisterButton() {
		setDebug("onClickRegisterButton");
		RegisterUserRequestVO vo = createRegisterUserRequest();
		consumer.registerUser(vo);
	}

	private RegisterUserRequestVO createRegisterUserRequest() {
		User user = BeanFactory.createUserBean();

		user.getUserId().setUserName(username);
		user.getUserId().setStatus(UserStatus.PENDING);
		user.getUserId().setCreated(Calendar.getInstance());

		user.getUserInfo().setFirstName(firstName);
		user.getUserInfo().setLastName(lastName);
		user.getUserInfo().setGender(Gender.MALE);
		user.getUserInfo().setBirthDate(Calendar.getInstance().getTime());
		user.getUserInfo().setEmail(email);
		user.getUserInfo().setPhone(phone);

		user.getUserAddress().setAddress("Abc street 1");
		user.getUserAddress().setPostalCode("1234");
		user.getUserAddress().setMunicipality("Oslo");
		user.getUserAddress().setRegion("Oslo");
		user.getUserAddress().setCountry("Norway");

		UserPassword userPassword = BeanFactory.createUserPasswordBean();
		userPassword.setPassword("myPassword");
		userPassword.setSalt("mySalt");

		return new RegisterUserRequestVO(user, userPassword);
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
		return super.getTitle(PAGE_TITLE);
	}
}
