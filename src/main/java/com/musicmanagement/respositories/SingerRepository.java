package com.musicmanagement.respositories;

import com.musicmanagement.datatypes.Singer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Integer>{
    
}
