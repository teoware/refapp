package com.teoware.refapp.web.ui.author;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.ListAuthorsResponse;
import com.teoware.refapp.web.consumer.AuthorServiceConsumer;
import com.teoware.refapp.web.ui.AbstractPageBean;

@Named("listAuthors")
@RequestScoped
public class ListAuthorsPageBean extends AbstractPageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_TITLE = "List authors";

	private String action;

	private List<Author> authorList;

	@Inject
	AuthorServiceConsumer serviceConsumer;

	@SuppressWarnings("unused")
	private void doLoadAuthorList() throws ServiceException {
		ListAuthorsResponse response = serviceConsumer.listAuthors();
		authorList = response.getAuthorList();
		action = "Size: " + authorList.size();
	}

	private String getStackTrace(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}

	public String getAction() {
		return action;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	@Override
	public String getTitle() {
		return super.getTitle() + PAGE_TITLE;
	}
}
