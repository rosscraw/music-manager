package com.musicmanagement.respositories;

import com.musicmanagement.datatypes.Album;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Album repository.
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    
    @Query("SELECT a FROM Album a WHERE a.title LIKE %?1%"
            + " OR a.singer LIKE %?1%"
            + " OR a.company LIKE %?1%"
            + " OR CONCAT(a.year, '') LIKE %?1%")
    public Page<Album> findAll(String keyword, Pageable pageable);

    
    // @Query("SELECT a FROM Album a"
    //         + " INNER JOIN Singer s"
    //         + " ON a.singer = s.name")
    // public Page<Album> findAllJoin(Pageable pageable);
    
}
