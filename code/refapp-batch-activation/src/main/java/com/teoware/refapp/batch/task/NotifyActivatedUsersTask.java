package com.teoware.refapp.batch.task;

import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.batch.domain.NotifyActivatedUsersResult;
import com.teoware.refapp.batch.domain.NotifyActivatedUsersSetup;
import com.teoware.refapp.batch.domain.TaskResult;
import com.teoware.refapp.batch.domain.TaskSetup;
import com.teoware.refapp.model.user.User;

public class NotifyActivatedUsersTask extends BatchTask<List<User>> {

    private static final Logger LOG = LoggerFactory.getLogger(NotifyActivatedUsersTask.class);

    @Override
    public TaskSetup setup(Object data) {
        List<User> activatedUsers = convert(data);
        return new NotifyActivatedUsersSetup(activatedUsers);
    }

    @Override
    public TaskResult run(TaskSetup setup) {
        try {
            LOG.info("Notifying activated users");
            List<User> activatedUsers = convert(setup.data());
            Email email = createEmailClient();
            int notifiedUsers = 0;
            for (User activatedUser : activatedUsers) {
                LOG.info("Notifying user '{}'", activatedUser.getUsername().getUsername());
                sendEmailNotification(email, activatedUser);
            }
            return new NotifyActivatedUsersResult(notifiedUsers, Boolean.FALSE);
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
