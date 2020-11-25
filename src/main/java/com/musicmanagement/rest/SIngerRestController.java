package com.musicmanagement.rest;

import java.util.List;
import java.util.NoSuchElementException;

import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.services.SingerService;

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
@RequestMapping("/singers")
public class SingerRestController{
    
    @Autowired
    private SingerService singerService;

    
    @GetMapping("")
    public List<Singer> list() {
        return singerService.listAllSingers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Singer> get (@PathVariable Integer id) {
        try{
            Singer singer = singerService.getSinger(id);
            return new ResponseEntity<Singer>(singer, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Singer>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody Singer singer) {
        try{singerService.saveSinger(singer);}
        catch (Exception e){
            System.out.println(e);
        }
    }

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        singerService.deleteSinger(id);
    }
    
}
