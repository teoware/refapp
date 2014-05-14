package com.teoware.refapp.glassfish.security;

import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchRealmException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;

import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;

public class RefappRealm extends AppservRealm {

    private static final String REALM_NAME = "RefappRealm";
    private static final String REALM_READABLE_NAME = "Refapp Realm";
    private static final String REALM_JDBC_JNDI = "jdbc/refapp";
    private static final String REALM_JAAS_CONTEXT_KEY = "jaas-context";
    private static final String REALM_DATASOURCE_KEY = "dataSource";

    private String jaasContexName;
    private String dataSource;

    @Override
    protected void init(Properties props) throws BadRealmException, NoSuchRealmException {
        _logger.fine("init()");
        jaasContexName = props.getProperty(REALM_JAAS_CONTEXT_KEY, REALM_NAME);
        dataSource = props.getProperty(REALM_DATASOURCE_KEY, REALM_JDBC_JNDI);
    }

    @Override
    public String getAuthType() {
        return REALM_READABLE_NAME;
    }

    @Override
    public synchronized String getJAASContext() {
        return jaasContexName;
    }

    @Override
    public Enumeration<?> getGroupNames(String string) throws InvalidOperationException, NoSuchUserException {
        return null;
    }

    public String[] authenticate(String username, String password) throws Exception {
        SecurityStore store = new SecurityStore(dataSource);

        String salt = store.getSalt(username);

        String[] result = null;

        if (salt != null) {
            byte[] saltBytes = Base64.decode(salt);

            byte[] passwordBytes = Digest.digest(password, saltBytes);

            String passwordHash = Base64.encode(passwordBytes);
            _logger.log(Level.FINE, "PWD Generated {0}", passwordHash);

            if (store.validateUser(username, passwordHash)) {
                result = new String[1];
                result[0] = "ValidUser";
            }
        }
        return result;
    }
}
