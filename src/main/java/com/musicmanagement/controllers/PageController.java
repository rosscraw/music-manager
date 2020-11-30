package com.musicmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controller to manage home and login pages.
 */
@Controller
public class PageController {

    // @Autowired
    // private UserService userService;

    /**     
     * @return home html page.
     */
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    /**
     * @return login html page.
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * @param model model to add the login error attribute to
     * @return login html page.
     */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }   
}

