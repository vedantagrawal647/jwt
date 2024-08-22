package com.durgesh.durgeag13_Lombok.controller;

import com.durgesh.durgeag13_Lombok.model.User;
import com.durgesh.durgeag13_Lombok.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User>  getUser(){
        System.out.println("Getting user");
        return this.userService.getUsers();
    }

    @GetMapping("/currentuser")
    public  String getLoggedInUser(Principal principal)
    {
        return principal.getName();
    }
}
