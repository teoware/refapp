package com.teoware.refapp.web.consumer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.service.AuthorService;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.web.consumer.util.TestDataFactory;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class AuthorServiceConsumerTest {

	@InjectMocks
	private AuthorServiceConsumer authorServiceConsumer = new AuthorServiceConsumerBean();

	@Mock
	private AuthorService authorService;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testThatRegisterAuthorIsSuccessful() throws ParseException, ValidationException, ServiceException {
		RegisterAuthorRequest request = new RegisterAuthorRequest(TestDataFactory.createAuthorJohn());

		authorServiceConsumer.registerAuthor(request);

		verify(authorService).registerAuthor(request);
		verifyNoMoreInteractions(authorService);
	}
}
