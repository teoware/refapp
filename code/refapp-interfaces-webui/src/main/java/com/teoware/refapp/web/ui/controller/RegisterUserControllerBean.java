package com.teoware.refapp.web.ui.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;

import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.web.consumer.UserServiceConsumer;
import com.teoware.refapp.web.consumer.vo.RegisterUserRequestVO;

@Named
@RequestScoped
public class RegisterUserControllerBean extends AbstractControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private RegisterUserRequestVO vo;
	private String confirmPassword;

	@Inject
	UserServiceConsumer consumer;

	@PostConstruct
	private void init() {
		User user = BeanFactory.createUserBean();
		UserPassword userPassword = BeanFactory.createUserPasswordBean();
		vo = new RegisterUserRequestVO(user, userPassword);

	}

	public void onClickRegisterButton() {
		setDebug("onClickRegisterButton");
		if (!vo.getUserPassword().getPassword().equals(confirmPassword)) {
			setDebug("Password error!");
		} else {
			processRegisterUserRequest();
			consumer.registerUser(vo);
		}
	}

	private void processRegisterUserRequest() {
		vo.getUser().getUserInfo().setGender(Gender.MALE);
		vo.getUser().getUserInfo().setBirthDate(new DateTime());

		vo.getUser().getUserAddress().setAddress("Abc street 1");
		vo.getUser().getUserAddress().setPostalCode("1234");
		vo.getUser().getUserAddress().setMunicipality("Oslo");
		vo.getUser().getUserAddress().setRegion("Oslo");
		vo.getUser().getUserAddress().setCountry("Norway");
	}

	public RegisterUserRequestVO getVo() {
		return vo;
	}

	public void setVo(RegisterUserRequestVO vo) {
		this.vo = vo;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String getPageTitle() {
		return super.dict("page.register_user.title");
	}
}
