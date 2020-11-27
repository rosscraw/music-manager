package com.musicmanagement.respositories;

import com.musicmanagement.datatypes.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * User repository.
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsername(String username);    
}