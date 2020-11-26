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
    private UserRepository singerRepo;

    /**
     * @return list of all singers in the database.
     */
    public List<User> listAllUsers() {
        return singerRepo.findAll();
    }

    /**
     * Add singer to database.
     * @param singer the singer to be added to database.
     */
    public void saveUser(User singer) {
       singerRepo.save(singer);
    }

    /**
     * @param id the singer's id.
     * @return the singer.
     */
    public User getUser(Integer id) {
        return singerRepo.findById(id).get();
    }

    /**
     * Delete singer from database.
     * @param id the singer's id.
     */
    public void deleteUser(Integer id) {
        singerRepo.deleteById(id);
    }
    
}

