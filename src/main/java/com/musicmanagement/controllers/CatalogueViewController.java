package com.musicmanagement.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CatalogueViewController {    
    
    @RequestMapping("/catalogue")
    public String catalogue() {
        return "catalogue";
    } 
}
