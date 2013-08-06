package com.teoware.refapp.batch.task;

import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.model.user.User;

public class NotifyActivatedUsersTask extends BatchTask<Integer, List<User>> {

	private static final Logger LOG = LoggerFactory.getLogger(NotifyActivatedUsersTask.class);

	@Override
	public TaskSetup<List<User>> init() {
		return new NotifyActivatedUsersSetup();
	}

	@Override
	public void run() {
		try {
			LOG.info("Notifying activated users");
			List<User> activatedUsers = setup.data();
			Email email = createEmailClient();
			int notifiedUsers = 0;
			for (User activatedUser : activatedUsers) {
				LOG.info("Notifying user '{}'", activatedUser.getUsername().getUsername());
				sendEmailNotification(email, activatedUser);
			}
			result = new NotifyActivatedUsersResult(notifiedUsers, Boolean.FALSE);
		} catch (EmailException e) {
			throw new BatchException("Email error occured when notifying activated users", e);
		}
	}

	private Email createEmailClient() {
		Email email = new SimpleEmail();
		email.setHostName("mail.teoware.com");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator("no-reply@teoware.com", "cesium55"));
		// email.setSSLOnConnect(true);
		return email;
	}

	private void sendEmailNotification(Email email, User user) throws EmailException {
		email.setFrom("no-reply@teoware.com");
		email.setSubject("RefApp User Account Activation");
		email.setMsg("Your RefApp user account has been activated.");
		email.addTo(user.getUserDetails().getEmail());
		email.send();
	}
}
