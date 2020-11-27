package com.musicmanagement.services;

import java.util.List;

import com.musicmanagement.datatypes.Album;
import com.musicmanagement.respositories.AlbumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
/**
 * The Album service that will handle the CRUD operations.
 */
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepo;
    
    /**
     * @return list of all albums in the database.
     */
    public List<Album> listAllAlbums() {
        return albumRepo.findAll();
    }

    /**
     * Add album to database.
     * @param album the album to be added to database.
     */
    public void saveAlbum(Album album) {
       try {albumRepo.save(album);}
       catch(Exception e) {
           System.out.println(e);
       }
    }

    /**
     * @param id the album's id.
     * @return the album.
     */
    public Album getAlbum(Integer Id) {
        return albumRepo.findById(Id).get();
    }

    /**
     * Delete album from database.
     * @param id the album's id.
     */
    public void deleteAlbum(Integer Id) {
        albumRepo.deleteById(Id);
    }

    public Page<Album> listAll(int pageNum, String sortField, String sortDir, String search) {
        int pageSize = 5;
         
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
            sortDir.equals("asc") ? Sort.by(sortField).ascending()
                                              : Sort.by(sortField).descending()
    );
    if(search !=null) {
        return albumRepo.findAll(search, pageable);
        //return albumRepo.search(search, pageable);
    }         
        return albumRepo.findAll(pageable);
    }

}
