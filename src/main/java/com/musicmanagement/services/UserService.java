package com.musicmanagement.services;

import java.util.List;

import com.musicmanagement.datatypes.User;
import com.musicmanagement.respositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * The User service that will handle the CRUD operations.
 */
public class UserService {

    

    @Autowired
    private UserRepository userRepo;

    /**
     * @return list of all users in the database.
     */
    public List<User> listAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Add user to database.
     * @param user the user to be added to database.
     */
    public void saveUser(User user) {
       userRepo.save(user);
    }

    /**
     * @param id the user's id.
     * @return the user.
     */
    public User getUser(Integer id) {
        return userRepo.findById(id).get();
    }

    /**
     * Delete user from database.
     * @param id the user's id.
     */
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }
    
}

