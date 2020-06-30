package com.deliver.bank.bank.auth.service;

import com.deliver.bank.bank.auth.security.UserSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSecurityService {

    public static UserSecurity authenticated() {
        try {
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
