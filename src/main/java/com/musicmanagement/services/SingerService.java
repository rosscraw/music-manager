package com.musicmanagement.services;

import java.util.List;

import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.respositories.SingerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * The Singer service that will handle the CRUD operations.
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
    
}
