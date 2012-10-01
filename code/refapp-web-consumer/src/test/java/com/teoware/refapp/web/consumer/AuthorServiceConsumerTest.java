package com.teoware.refapp.web.consumer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

import java.text.ParseException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.service.AuthorService;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.web.consumer.util.TestDataFactory;

public class AuthorServiceConsumerTest {

	@InjectMocks
	private AuthorServiceConsumer authorServiceConsumer = new AuthorServiceConsumerBean();

	@Mock
	private AuthorService authorService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testThatRegisterAuthorIsSuccessful() throws ParseException, ValidationException, ServiceException {
		RegisterAuthorRequest request = new RegisterAuthorRequest(TestDataFactory.createAuthorJohn());

		authorServiceConsumer.registerAuthor(request);

		verify(authorService).registerAuthor(request);
		verifyNoMoreInteractions(authorService);
	}
}
