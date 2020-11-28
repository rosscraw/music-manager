package com.musicmanagement.controllers;

import java.util.List;

import com.musicmanagement.datatypes.User;
import com.musicmanagement.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
/**
 * Controller to handle views relating to the User Page. Includes viewing a
 * table of users, being able to add a new user, updating a user's
 * details.
 */
public class UserViewController {

    @Autowired
    private UserService userService;

    /**
     * 
     * @param model
     * @return the user list html file to display.
     */
    @RequestMapping("user-list")
    public String listUsers(Model model, @Param("search") String search) {
        // List<User> userList = userService.listAllUsers();
        // model.addAttribute("usersList", userList);
        return viewPage(model, search, 1, "username", "asc");
    }

    /**
     * 
     * @param model
     * @return the new user html file to display.
     */
    @RequestMapping("user-list/new-user")
    public String showNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "user/new-user";
    }

    /**
     * @param user the user to add to the database
     * @return the user list html page to display.
     */
    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/user-list";
    }

    /**
     * 
     * @param id the user's id.
     * @return edit page for the user.
     */
    @RequestMapping("user-list/edit-user/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("user/edit-user");
        User user = userService.getUser(id);
        mav.addObject("user", user);

        return mav;
    }

    /**
     * Page numbering.
     * 
     * @param model
     * @param pageNum
     * @return userlist page.
     */
    @RequestMapping("user-list/page/{pageNum}")
    public String viewPage(Model model, @Param("search") String search, @PathVariable(name = "pageNum") int pageNum,
            @Param("sortField") String sortField, @Param("sortDir") String sortDir) {

        Page<User> page = userService.listAll(pageNum, sortField, sortDir, search);

        List<User> listUser = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("search", search);

        model.addAttribute("usersList", listUser);

        return "user/userlist";
    }

    /**
     * Delete a user.
     * 
     * @param id the user's id to be deleted.
     * @return the signer list page.
     */
    @RequestMapping("user-list/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/user-list";
    }
}
