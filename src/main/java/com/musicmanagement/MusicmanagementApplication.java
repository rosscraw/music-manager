package com.musicmanagement;

import java.io.File;

import com.dataloader.DBConnection;
import com.dataloader.DataLoader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application
 * Also intialises the MySQL database with data from a text file.
 */
@SpringBootApplication
public class MusicmanagementApplication {

	public static void main(String[] args) throws Exception {
		DataLoader dataL = new DataLoader();
        DBConnection.createTables();
        File file = new File("data\\ng_music_data.txt");
        dataL.separateDataFromTextFile(file);
		SpringApplication.run(MusicmanagementApplication.class, args);
	}

}
