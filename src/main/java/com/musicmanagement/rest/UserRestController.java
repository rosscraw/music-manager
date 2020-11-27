package com.musicmanagement.rest;

import java.util.List;
import java.util.NoSuchElementException;

import com.musicmanagement.datatypes.User;
import com.musicmanagement.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
/**
 * A REST controller for managing the users via the user service.
 */
public class UserRestController{
    
    @Autowired
    private UserService userService;

    /**
     * GET method to retrieve all Users.
     * @return list<Users>.
     */
    @GetMapping("")
    public List<User> list() {
        return userService.listAllUsers();
    }

    /**
     * GET method to retrieve a User via their Id.
     * @param id the User's Id.
     * @return User.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> get (@PathVariable Integer id) {
        try{
            User user = userService.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST method to add a new User to the database.
     * @param user the signer to be added to database.
     */
    @PostMapping("/")
    public void add(@RequestBody User user) {
        try{userService.saveUser(user);}
        catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * PUT method to update a user in the database via their Id.
     * @param user the User to be updated
     * @param id the user's Id.
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
        try{
            User existingUser = userService.getUser(id);
            existingUser.setId(id);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE method to remove a user from the database via their Id.
     * @param id the user's Id.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
    
}
