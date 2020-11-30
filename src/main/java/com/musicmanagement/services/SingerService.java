package com.musicmanagement.services;

import java.util.List;

import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.respositories.SingerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
/**
 * The Singer service that will handle the Singer CRUD operations.
 */
public class SingerService {

    @Autowired
    private SingerRepository singerRepo;

    /**
     * @return list of all singers in the database.
     */
    public List<Singer> listAllSingers() {
        return singerRepo.findAll();
    }

    /**
     * Add singer to database.
     * @param singer the singer to be added to database.
     */
    public void saveSinger(Singer singer) {
       singerRepo.save(singer);
    }

    /**
     * @param id the singer's id.
     * @return the singer.
     */
    public Singer getSinger(Integer id) {
        return singerRepo.findById(id).get();
    }

    /**
     * Delete singer from database.
     * @param id the singer's id.
     */
    public void deleteSinger(Integer id) {
        singerRepo.deleteById(id);
    }

    /**
     * Get Singers on current page.
     * @param pageNum the current page number
     * @param sortField the current field being sorted
     * @param sortDir the direction the data is being sorted (ascending or descending)
     * @param search the current search filter term.
     * @return Page sublist.
     */
    public Page<Singer> listAll(int pageNum, String sortField, String sortDir, String search) {
        int pageSize = 5;
         
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
            sortDir.equals("asc") ? Sort.by(sortField).ascending()
                                              : Sort.by(sortField).descending()
    );
    if(search !=null) {
        return singerRepo.findAll(search, pageable);
    }         
        return singerRepo.findAll(pageable);
    }
    
}
