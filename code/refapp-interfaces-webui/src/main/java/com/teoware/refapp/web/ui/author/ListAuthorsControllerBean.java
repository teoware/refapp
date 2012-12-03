package com.teoware.refapp.web.ui.author;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.web.consumer.AuthorServiceConsumer;
import com.teoware.refapp.web.consumer.vo.AuthorListVO;
import com.teoware.refapp.web.ui.AbstractControllerBean;

@Named("listAuthors")
@RequestScoped
public class ListAuthorsControllerBean extends AbstractControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ListAuthorsControllerBean.class);

	private static final String PAGE_TITLE = "List authors";

	private String action = "Init";

	private List<Author> authorList;

	@Inject
	AuthorServiceConsumer consumer;

	@PostConstruct
	private void doLoadAuthorList() {
		LOG.info("Loading author list");
		AuthorListVO vo = consumer.listAuthors();
		authorList = vo.getAuthorList();
		action = "Size: " + authorList.size();
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
