package com.teoware.refapp.service.util;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;

public class ServiceBeanFactory {

	public static RegisterAuthorRequest createRegisterAuthorRequestBean() {
		Author author = BeanFactory.createAuthorBean();
		return new RegisterAuthorRequest(author);
	}
}
