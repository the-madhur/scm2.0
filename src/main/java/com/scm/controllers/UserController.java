package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController 
{
    // user dashboard page

    @RequestMapping(value = "/dashboard")
    public String userDashboard()
    {
        System.out.println("User Dashboard Page");
        return "user/dashboard";
    }

    // user profile page

    @RequestMapping(value = "/profile")
    public String userProfile()
    {
        System.out.println("User profile Page");
        return "user/profile";
    }


    // user add contacts page 
    
    // usr view contact 

    // user edit contact 

    // user delete contact 

    // user search contact 

}
