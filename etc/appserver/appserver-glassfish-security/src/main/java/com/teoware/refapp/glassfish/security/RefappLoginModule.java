package com.teoware.refapp.glassfish.security;

import com.sun.appserv.security.AppservPasswordLoginModule;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import com.sun.enterprise.security.auth.realm.Realm;

import javax.security.auth.login.LoginException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class RefappLoginModule extends AppservPasswordLoginModule {

    private static final Logger LOGGER = Logger.getLogger(RefappRealm.class.getName());

    @Override
    protected void authenticateUser() throws LoginException {
        Realm currentRealm = getCurrentRealm();
        String username = getUsername();
        char[] passwordChar = getPasswordChar();
        String password = new String(passwordChar);
        LOGGER.log(INFO, "RefappLoginModule authenticateUser(), username: {0}, password: {1}, currentrealm: {2}", new Object[]{username, password, currentRealm});

        if (!(currentRealm instanceof RefappRealm)) {
            throw new LoginException("Realm not RefappRealm. Check 'login.conf'");
        }
        RefappRealm realm = (RefappRealm) currentRealm;

        if (!realm.doAuthenticate(username, password)) {
            throw new LoginException("RefappLoginModule: Login Failed for user " + username);
        }

        LOGGER.log(INFO, "RefappLoginModule: Login Succeded for user {0}", username);

        try {
            Enumeration<String> enumeration = realm.getGroupNames(username);
            List<String> groups = Collections.list(enumeration);
            String[] authenticatedGroups = groups.toArray(new String[groups.size()]);
            commitUserAuthentication(authenticatedGroups);
        } catch (InvalidOperationException e) {
            throw new LoginException("InvalidOperationException was thrown for getGroupNames() on RefappLoginModule");
        } catch (NoSuchUserException e) {
            throw new LoginException("NoSuchUserException was thrown for getGroupNames() on RefappLoginModule");
        }
    }
}
