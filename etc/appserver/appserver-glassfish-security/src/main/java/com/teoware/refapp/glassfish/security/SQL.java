package com.teoware.refapp.glassfish.security;

public interface SQL {
    String ADD_USER = "INSERT INTO users VALUES(?,?,?);";
    String SALT_FOR_USER = "SELECT salt FROM users u WHERE username = ?;";
    String VERIFY_USER = "SELECT username FROM users u WHERE username = ? AND password = ?;";
}
