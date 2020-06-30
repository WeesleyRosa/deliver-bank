package com.deliver.bank.bank.auth.controller;

import com.deliver.bank.bank.auth.security.JWTUtil;
import com.deliver.bank.bank.auth.security.UserSecurity;
import com.deliver.bank.bank.auth.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private JWTUtil jwtUtil;

    @Autowired
    public AuthController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSecurity user = UserSecurityService.authenticated();
        String token = this.jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
