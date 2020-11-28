package com.musicmanagement.respositories;

import com.musicmanagement.datatypes.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
/**
 * User repository.
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query("SELECT u FROM User u WHERE u.username LIKE %?1%")
    public Page<User> findAll(String keyword, Pageable pageable);
    
    User findByUsername(String username);    
    
    
}