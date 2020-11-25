package com.musicmanagement.services;

import java.util.List;

import com.musicmanagement.datatypes.Album;
import com.musicmanagement.respositories.AlbumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepo;
    
    public List<Album> listAllAlbums() {
        return albumRepo.findAll();
    }

    public void saveAlbum(Album album) {
       albumRepo.save(album);
    }

    public Album getAlbum(Integer Id) {
        return albumRepo.findById(Id).get();
    }

    public void deleteAlbum(Integer Id) {
        albumRepo.deleteById(Id);
    }


}
