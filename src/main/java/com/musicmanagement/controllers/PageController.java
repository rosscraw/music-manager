package com.musicmanagement.controllers;

import java.util.List;

import com.musicmanagement.datatypes.Album;
import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.datatypes.User;
import com.musicmanagement.services.AlbumService;
import com.musicmanagement.services.SingerService;
import com.musicmanagement.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private SingerService singerService;
    
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "insert";
    }

    @RequestMapping("/user-list")
    public String listUsers(Model model) {
        List<User> usersList = userService.listAllUsers();
        model.addAttribute("usersList", usersList);
        return "userlist";
    }

    @RequestMapping("/album-list")
    public String listAlbums(Model model) {
        List<Album> albumsList = albumService.listAllAlbums();
        model.addAttribute("albumsList", albumsList);
        return "albumlist";
    }

    @RequestMapping("/singer-list")
    public String listSingers(Model model) {
        List<Singer> singerList = singerService.listAllSingers();
        model.addAttribute("singersList", singerList);
        return "singerlist";
    }
}
