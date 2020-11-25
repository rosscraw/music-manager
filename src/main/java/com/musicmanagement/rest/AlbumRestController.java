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
public class AlbumRestController{
    
    @Autowired
    private AlbumService albumService;

    
    @GetMapping("")
    public List<Album> list() {
        return albumService.listAllAlbums();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> get (@PathVariable Integer id) {
        try{
            Album album = albumService.getAlbum(id);
            return new ResponseEntity<Album>(album, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody Album album) {
        try{albumService.saveAlbum(album);}
        catch (Exception e){
            System.out.println(e);
        }
    }

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        albumService.deleteAlbum(id);
    }
    
}
