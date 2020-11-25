package com.musicmanagement.rest;

import java.util.List;
import java.util.NoSuchElementException;

import com.musicmanagement.datatypes.Album;
import com.musicmanagement.services.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albums")
/**
 * A REST controller for managing the albums via the album service.
 */
public class AlbumRestController{
    
    @Autowired
    private AlbumService albumService;

    /**
     * GET method to retrieve all Albums.
     * @return list<Albums>.
     */
    @GetMapping("")
    public List<Album> list() {
        return albumService.listAllAlbums();
    }

    /**
     * GET method to retrieve a Album via their Id.
     * @param id the Album's Id.
     * @return Album.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Album> get (@PathVariable Integer id) {
        try{
            Album album = albumService.getAlbum(id);
            return new ResponseEntity<Album>(album, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST method to add a new Album to the database.
     * @param album the signer to be added to database.
     */
    @PostMapping("/")
    public void add(@RequestBody Album album) {
        try{albumService.saveAlbum(album);}
        catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * PUT method to update a album in the database via their Id.
     * @param album the Album to be updated
     * @param id the album's Id.
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Album album, @PathVariable Integer id) {
        try{
            Album existingAlbum = albumService.getAlbum(id);
            existingAlbum.setId(id);
            albumService.saveAlbum(album);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE method to remove a album from the database via their Id.
     * @param id the album's Id.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        albumService.deleteAlbum(id);
    }
    
}
