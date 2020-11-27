package com.musicmanagement.respositories;

import java.util.List;

import com.musicmanagement.datatypes.Singer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Singer repository.
 */
public interface SingerRepository extends JpaRepository<Singer, Integer>{
    
    @Query("SELECT s FROM Singer s WHERE s.name LIKE %?1%"
            + " OR s.sex LIKE %?1%"
            + " OR s.company LIKE %?1%"
            + " OR CONCAT(s.dob, '') LIKE %?1%")
    public Page<Singer> findAll(String keyword, Pageable pageable);

}
