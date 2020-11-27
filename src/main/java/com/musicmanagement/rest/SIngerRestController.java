package com.musicmanagement.rest;

import java.util.List;
import java.util.NoSuchElementException;

import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.services.SingerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/singer")
/**
 * A REST controller for managing the singers via the singer service.
 */
public class SingerRestController{
    
    @Autowired
    private SingerService singerService;

    /**
     * GET method to retrieve all Singers.
     * @return list<Singers>.
     */
    @GetMapping("")
    public List<Singer> list() {
        return singerService.listAllSingers();
    }

    /**
     * GET method to retrieve a Singer via their Id.
     * @param id the Singer's Id.
     * @return Singer.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Singer> get (@PathVariable Integer id) {
        try{
            Singer singer = singerService.getSinger(id);
            return new ResponseEntity<Singer>(singer, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Singer>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST method to add a new Singer to the database.
     * @param singer the signer to be added to database.
     */
    @PostMapping("/")
    public void add(@RequestBody Singer singer) {
        try{singerService.saveSinger(singer);}
        catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * PUT method to update a singer in the database via their Id.
     * @param singer the Singer to be updated
     * @param id the singer's Id.
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Singer singer, @PathVariable Integer id) {
        try{
            Singer existingSinger = singerService.getSinger(id);
            existingSinger.setId(id);
            singerService.saveSinger(singer);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<Singer>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE method to remove a singer from the database via their Id.
     * @param id the singer's Id.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        singerService.deleteSinger(id);
    }

}
