package com.dataloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicmanagement.datatypes.Album;
import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.datatypes.User;
import com.musicmanagement.respositories.AlbumRepository;
import com.musicmanagement.respositories.UserRepository;
import com.musicmanagement.rest.UserRestController;
import com.musicmanagement.services.AlbumService;
import com.musicmanagement.services.SingerService;
import com.musicmanagement.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Data type to sort through a text file and update database.
 */
public class DataLoader {
    

    public static void main(String[] args) throws Exception {
        
        DataLoader dataL = new DataLoader();
        DBConnection.createTables();
        File file = new File("data\\ng_music_data.txt");
        dataL.separateDataFromTextFile(file);

        // User user = new User("John13245");
        // user.setPassword("etryuio");
        // user.setId(1234354678);
        // DBConnection.addUser(user);
        // //dataL.addUserToDB(user);


    }

    //private DBConnection db = new DBConnection();

    @Autowired
    private SingerService singerService;
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumRepository albumRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserRestController userRest;


    /**
     * Separated the rows in a text file and creates an object depending on its
     * type.
     * 
     * @param file the file containing artist, singer and user records to be
     *             separated and parsed.
     * @throws Exception
     */
    public void separateDataFromTextFile(File file) throws Exception {
        Scanner input = new Scanner(file);
        input.useDelimiter("\n");

        while (input.hasNext()) {
            String row = input.next().trim();
            char lineStartChar = row.charAt(0);
            // If row is an album
            if (Character.toString(lineStartChar).equals("A")) {
                mapAlbum(row);
                // If row is a user
            } else if (Character.toString(lineStartChar).equals("U")) {
                mapUser(row);
                // If row is a singer
            } else if (Character.toString(lineStartChar).equals("S")) {
                mapSinger(row);
            }
        }

        System.out.print("Complete!");
        input.close();

    }

    /**
     * Parses row from text document and assigns values to user attributes. Encodes
     * the password for database storage using BCrpyt.
     * 
     * @param userRow row containing user information.
     * @throws Exception
     */
    public void mapUser(String userRow) throws Exception {
        String url = "http://localhost:8080/users/";
        
        String[] params = userRow.trim().split("\\|");
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

        //System.out.println(Arrays.toString(params));

        String username = params[1].trim();
        String password = params[2].trim();

        User user = new User(username);
        user.setPassword(encode.encode(password));
        
        DBConnection.addUser(user);
        //addUserToDB(user);

    }

    /**
     * Parses row from text document and assigns values to album attributes.
     * 
     * @param albumRow row containg album information.
     * @throws Exception
     */
    public void mapAlbum(String albumRow) throws Exception {
        String[] params = albumRow.trim().split("\\|");

        String singer = params[1].trim();
        String title = params[2].trim();
        int year = Integer.parseInt(params[3].trim());        
        String company = params[4].trim();


        Album album = new Album(title, company, year, singer);

        DBConnection.addAlbum(album);
        // System.out.println(album.toString());
        // addAlbumToDB(album);

    }

    /**
     * Parses row from text document and assigns values to singer attributes.
     * 
     * @param singerRow row containing song information.
     * @throws Exception
     */
    public void mapSinger(String singerRow) throws Exception {
        String[] params = singerRow.trim().split("\\|");

        String name = params[1].trim();
        int dob = Integer.parseInt(params[2].trim());
        String sex = params[3].trim();
        String company = params[4].trim();

        Singer singer = new Singer(name, company, dob, sex);
        // addSingerToDB(singer);
        DBConnection.addSinger(singer);
    }

    

    // public void addSingerToDB(Singer singer) {
    //     singerService.saveSinger(singer);
    // }

    // // public void addUserToDB(User user) {
    // //     userService.saveUser(user);
    // //     //userRepo.save(user);
    // //     //userRest.add(user);
    // // }

    // public void addAlbumToDB(Album album) {
    //     albumService.saveAlbum(album);
    // }

}
