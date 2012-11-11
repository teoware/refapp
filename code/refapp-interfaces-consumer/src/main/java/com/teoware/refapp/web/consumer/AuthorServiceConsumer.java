package com.teoware.refapp.web.consumer;

import com.teoware.refapp.web.consumer.vo.AuthorListVO;
import com.teoware.refapp.web.consumer.vo.AuthorVO;
import com.teoware.refapp.web.consumer.vo.FindAuthorRequestVO;
import com.teoware.refapp.web.consumer.vo.RegisterAuthorRequestVO;
import com.teoware.refapp.web.consumer.vo.RegisterAuthorResponseVO;

public interface AuthorServiceConsumer {

	public RegisterAuthorResponseVO registerAuthor(RegisterAuthorRequestVO vo);

	public AuthorVO findAuthor(FindAuthorRequestVO vo);

	public AuthorListVO listAuthors();
}
