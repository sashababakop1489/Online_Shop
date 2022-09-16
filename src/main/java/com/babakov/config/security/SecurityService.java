package com.babakov.config.security;

public interface SecurityService {

    boolean isAuthenticated();
    void autoLogin(String username, String password);
    boolean existsByEmail(String email);
}
