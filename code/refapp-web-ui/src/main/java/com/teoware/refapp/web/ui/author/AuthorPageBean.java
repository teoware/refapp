package com.teoware.refapp.web.ui.author;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.service.dto.ListAuthorsResponse;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.web.consumer.AuthorServiceConsumer;

@ManagedBean(name="author")
@RequestScoped
public class AuthorPageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String action;
	
//	@Inject
//	AuthorServiceConsumer consumer;
	
	public void onClickRegisterButton() {
		action = "onClickRegisterButton";
//		RegisterAuthorRequest req = createRegisterAuthorRequest();
//		consumer.registerAuthor(req);
	}
	
	public void onClickListButton() {
		action = "onClickListButton";
//		ListAuthorsResponse res = consumer.listAuthors();
//		List<Author> list = res.getAuthorList();
//		for (Author author : list) {
//		}
	}
	
	public String getAction() {
		return action;
	}

	private RegisterAuthorRequest createRegisterAuthorRequest() {
		Author author = BeanFactory.createAuthorBean();

		author.getAuthorId().setUserName("thomas");
		author.getAuthorId().setStatus(AuthorStatus.PENDING);
		author.getAuthorId().setCreated(Calendar.getInstance());

		author.getAuthorInfo().setFirstName("Thomas");
		author.getAuthorInfo().setLastName("Johansen");
		author.getAuthorInfo().setGender(Gender.MALE);
		author.getAuthorInfo().setBirthDate(Calendar.getInstance().getTime());
		author.getAuthorInfo().setEmail("thomas@tow.no");
		author.getAuthorInfo().setPhone("12345678");

		author.getAuthorAddress().setAddress("Abc street 1");
		author.getAuthorAddress().setPostalCode("1234");
		author.getAuthorAddress().setMunicipality("Oslo");
		author.getAuthorAddress().setRegion("Oslo");
		author.getAuthorAddress().setCountry("Norway");

		AuthorPassword authorPassword = BeanFactory.createAuthorPasswordBean();
		authorPassword.setPassword("myPassword");

		return new RegisterAuthorRequest(author, authorPassword);
	}
	
	private String getStackTrace(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}
}
