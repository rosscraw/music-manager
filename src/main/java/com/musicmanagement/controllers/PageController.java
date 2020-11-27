package com.musicmanagement.controllers;

import java.util.List;

import com.musicmanagement.datatypes.Album;
import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.datatypes.User;
import com.musicmanagement.rest.SingerRestController;
import com.musicmanagement.services.AlbumService;
import com.musicmanagement.services.SingerService;
import com.musicmanagement.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    // @RequestMapping("/album-list")
    // public String listAlbums(Model model) {
    //     List<Album> albumsList = albumService.listAllAlbums();
    //     model.addAttribute("albumsList", albumsList);
    //     return "albumlist";
    // }

//     @RequestMapping("/singer-list")
//     public String listSingers(Model model) {
//         List<Singer> singerList = singerService.listAllSingers();
//         model.addAttribute("singersList", singerList);
//         return "singerlist";
//     }

//     @RequestMapping("/singer-list/new-singer")
//     public String showNewProductPage(Model model) {
//         Singer singer = new Singer();
//         model.addAttribute("singer", singer);
        
//         return "new-singer";
//     }

//     @RequestMapping(value = "/save-singer", method = RequestMethod.POST)
//     public String saveSinger(@ModelAttribute("singer") Singer singer) {
//         singerService.saveSinger(singer);;
        
//         return "redirect:/";
// }
}
