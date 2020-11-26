package com.dataloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.musicmanagement.datatypes.Album;
import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.datatypes.User;

import org.springframework.security.crypto.bcrypt.BCrypt;


/**
 * Data type to sort through a text file and update database.
 */
public class DataLoader {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File("data\\ng_music_data.txt"));
        input.useDelimiter("\n");
        ArrayList<String> albumsa = new ArrayList<>();
        ArrayList<String> singersa = new ArrayList<>();
        ArrayList<String> usersa = new ArrayList<>();

        
        while(input.hasNext()) {
            String str = input.next().trim();
            char lineStartChar = str.charAt(0);
            if (Character.toString(lineStartChar).equals("A")) {
            //input.useDelimiter("\\|");
            albumsa.add(str);
            }          
            else if (Character.toString(lineStartChar).equals("U")) {
                //input.useDelimiter("\\|");
                usersa.add(str);
                }   
            else if (Character.toString(lineStartChar).equals("S")) {
                String[] params = str.trim().split("\\|\\S + \\| + \\|\s");
                
                System.out.println(Arrays.toString(params));
                singersa.add(str);
                }   
        }
        System.out.println(albumsa.get(3));
        System.out.println(singersa.get(3));
        System.out.println(usersa.get(3));
    }
    
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayList<Singer> singers = new ArrayList<>();


    
}
