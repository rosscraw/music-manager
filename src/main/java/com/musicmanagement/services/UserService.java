package com.musicmanagement.services;

import java.util.List;

import com.musicmanagement.datatypes.User;
import com.musicmanagement.respositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
     * Add user to database and encrypts their password.
     * 
     * @param user the user to be added to database.
     */
    public void saveUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
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
     * 
     * @param id the user's id.
     */
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

     /**
     * Get Users on current page.
     * @param pageNum the current page number
     * @param sortField the current field being sorted
     * @param sortDir the direction the data is being sorted (ascending or descending)
     * @param search the current search filter term.
     * @return Page sublist.
     */
    public Page<User> listAll(int pageNum, String sortField, String sortDir, String search) {
        int pageSize = 5;
         
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
            sortDir.equals("asc") ? Sort.by(sortField).ascending()
                                              : Sort.by(sortField).descending()
    );
    if(search !=null) {
        return userRepo.findAll(search, pageable);
    }         
        return userRepo.findAll(pageable);
    }

}
