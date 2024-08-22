package com.durgesh.durgeag13_Lombok.controller;

import com.durgesh.durgeag13_Lombok.model.JwtRequest;
import com.durgesh.durgeag13_Lombok.model.JwtResponse;
import com.durgesh.durgeag13_Lombok.security.JwtHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> login(@ModelAttribute("jwtRequest") JwtRequest request, HttpServletResponse response) {

        System.out.println("--------------------------------");
        this.doAuthenticate(request.getUsername(), request.getPassword());
        System.out.println("hello");
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String token = this.helper.generateToken(userDetails);

        Cookie cookie = new Cookie("jwt",token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);


        JwtResponse jwtResponse = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        System.out.println(token);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }



    private  void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}