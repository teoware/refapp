package com.teoware.refapp.web.consumer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.dto.RegisterAuthorResponse;
import com.teoware.refapp.service.facade.AuthorServiceFacade;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.web.consumer.util.TestDataFactory;
import com.teoware.refapp.web.consumer.vo.RegisterAuthorRequestVO;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class AuthorServiceConsumerTest {

	@InjectMocks
	private AuthorServiceConsumer authorServiceConsumer = new AuthorServiceConsumerBean();

	@Mock
	private AuthorServiceFacade authorServiceFacade;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testThatRegisterAuthorIsSuccessful() throws ParseException, ValidationException, ServiceException {
		Author john = TestDataFactory.createAuthorJohn();
		AuthorPassword johnPassword = TestDataFactory.createAuthorJohnPassword();
		RegisterAuthorRequestVO vo = new RegisterAuthorRequestVO(john, johnPassword);

		when(authorServiceFacade.registerAuthor(any(RegisterAuthorRequest.class))).thenReturn(
				new RegisterAuthorResponse(null, null));

		authorServiceConsumer.registerAuthor(vo);

		verify(authorServiceFacade).registerAuthor(any(RegisterAuthorRequest.class));
		verifyNoMoreInteractions(authorServiceFacade);
	}
}
