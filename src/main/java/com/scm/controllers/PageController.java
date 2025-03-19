package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller

public class PageController 
{

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index()
    {
        return "redirect:/home";
    }

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

    // contact page

    @GetMapping("/contact")
    public String contact()
    {
        return new String("contact");
    }

    // Login page

    @GetMapping("/login")
    public String login()
    {
        return new String("login");
    }

    // Register page

    @GetMapping("/register")
    public String register(Model model)
    {
        UserForm userForm = new UserForm();
        // we can also put default data here
        // userForm.setName("Madhur Chaturvedi");
        // userForm.setEmail("jbjfbdkf");
        // userForm.setPassword("123456");
        // userForm.setAbout("I am a developer");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    //processing register

    @RequestMapping(value= "/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session)
    {
        System.out.println("Processing Registration");
        // fetch form data
        // UserForm
        System.out.println(userForm);

        // validate form data
        if(rBindingResult.hasErrors())
        {
            return "register";
        }
        // Todo

        // save to database

        //userservice
        
        // UserForm--> User
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://the-madhur.github.io/images/about-me.png")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://the-madhur.github.io/images/about-me.png");
        


        User savedUser = userService.saveUser(user);

        System.out.println("user saved :");

        // message ="Registration Successfull"

        // add the message

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        
        session.setAttribute("message", message);


        // redirect to login page
        return "redirect:/register";
    }
}

