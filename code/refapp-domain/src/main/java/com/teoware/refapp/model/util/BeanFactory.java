package com.teoware.refapp.model.util;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorAddress;
import com.teoware.refapp.model.author.AuthorId;
import com.teoware.refapp.model.author.AuthorInfo;
import com.teoware.refapp.model.author.AuthorPassword;

public class BeanFactory {

	public static AuthorId createAuthorIdBean() {
		AuthorId authorId = new AuthorId();
		return authorId;
	}

	public static AuthorInfo createAuthorInfoBean() {
		AuthorInfo authorInfo = new AuthorInfo();
		return authorInfo;
	}

	public static AuthorAddress createAuthorAddressBean() {
		AuthorAddress authorAddress = new AuthorAddress();
		return authorAddress;
	}

	public static Author createAuthorBean() {
		Author author = new Author();
		author.setAuthorId(createAuthorIdBean());
		author.setAuthorInfo(createAuthorInfoBean());
		author.setAuthorAddress(createAuthorAddressBean());
		return author;
	}

	public static AuthorPassword createAuthorPasswordBean() {
		return new AuthorPassword();
	}
}
