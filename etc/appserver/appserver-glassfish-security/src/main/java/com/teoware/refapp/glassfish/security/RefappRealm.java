package com.teoware.refapp.glassfish.security;

import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchRealmException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import com.teoware.refapp.auth.PasswordHandler;
import com.teoware.refapp.glassfish.security.domain.User;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

public class RefappRealm extends AppservRealm {

    private static final Logger LOGGER = Logger.getLogger(RefappRealm.class.getName());
    private static final String REALM_READABLE_NAME = "Refapp Realm";
    private static final String REALM_JAAS_CONTEXT_KEY = "jaas-context";
    private static final String REALM_DATASOURCE_KEY = "data-source";

    public RefappRealm() {
        LOGGER.log(INFO, "RefappRealm constructed");
    }

    @Override
    protected void init(Properties properties) throws BadRealmException, NoSuchRealmException {
        LOGGER.log(INFO, "RefappRealm init()");
        setRealmProperties(properties, REALM_JAAS_CONTEXT_KEY, REALM_DATASOURCE_KEY);
    }

    private void setRealmProperties(Properties properties, String... keys) {
        for (String key : keys) {
            String value = properties.getProperty(key);
            if (value != null) {
                LOGGER.log(INFO, "RefappRealm setting property {0}={1}", new Object[]{key, value});
                setProperty(key, value);
            } else {
                LOGGER.log(SEVERE, "RefappRealm no property found for key {0}", key);
            }
        }
    }

    @Override
    public String getAuthType() {
        return REALM_READABLE_NAME;
    }

    @Override
    public Enumeration<String> getGroupNames(String username) throws InvalidOperationException, NoSuchUserException {
        SecurityStore store = createSecurityStore();
        List<String> groups = store.getUserGroups(username);
        return new Vector<String>(groups).elements();
    }

    public boolean doAuthenticate(String username, String password) {
        SecurityStore store = createSecurityStore();
        User user = store.getUser(username);
        if (user != null) {
            return PasswordHandler.verifyPassword(password, user.getSalt(), user.getPassword());
        }
        return Boolean.FALSE;
    }

    private SecurityStore createSecurityStore() {
        String dataSource = getProperty(REALM_DATASOURCE_KEY);
        return new SecurityStore(dataSource);
    }
}
