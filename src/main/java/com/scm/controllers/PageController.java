package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller

public class PageController 
{

    @RequestMapping("/home")
    public String home(Model model)
    {
        System.out.println("Home Page Handler");

        // sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "Madhur Chaturvedi");
        model.addAttribute("githubRepo", "https://github.com/the-madhur/");
        
        return "home";
    }

    // about route

    @RequestMapping("/about")
    public String aboutPage(Model model)
    {
        model.addAttribute("isLogin", true);
        System.out.println("About Page Loading");
        return "about";
    }
    
    // services

    @RequestMapping("/services")
    public String servicesPage()
    {
        System.out.println("services Page Loading");
        return "services";
    }

}

