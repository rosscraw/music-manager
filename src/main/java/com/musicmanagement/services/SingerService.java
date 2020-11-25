package com.musicmanagement.services;

import java.util.List;

import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.respositories.SingerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingerService {

    @Autowired
    private SingerRepository singerRepo;

    public List<Singer> listAllSingers() {
        return singerRepo.findAll();
    }

    public void saveSinger(Singer singer) {
       singerRepo.save(singer);
    }

    public Singer getSinger(Integer Id) {
        return singerRepo.findById(Id).get();
    }

    public void deleteSinger(Integer Id) {
        singerRepo.deleteById(Id);
    }
    
}
