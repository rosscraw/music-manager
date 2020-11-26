package com.dataloader;

import java.io.File;
import java.util.Scanner;
import com.musicmanagement.datatypes.Album;
import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.datatypes.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Data type to sort through a text file and update database.
 * Contains the driver to insert the data into MySQL database.
 */
public class DataLoader {
    

    public static void main(String[] args) throws Exception {
        
        DataLoader dataL = new DataLoader();
        DBConnection.createTables();
        File file = new File("data\\ng_music_data.txt");
        dataL.separateDataFromTextFile(file);
    }

    /**
     * Separated the rows in a text file and creates an object depending on its type.
     * 
     * @param file the file containing artist, singer and user records to be separated and parsed.
     * @throws Exception
     */
    public void separateDataFromTextFile(File file) throws Exception {
        Scanner input = new Scanner(file);
        // Separate input by line.
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
        String[] params = userRow.trim().split("\\|");
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

        String username = params[1].trim();
        String password = params[2].trim();

        User user = new User(username);
        user.setPassword(encode.encode(password));
        
        DBConnection.addUser(user);
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
        DBConnection.addSinger(singer);
    }

}
