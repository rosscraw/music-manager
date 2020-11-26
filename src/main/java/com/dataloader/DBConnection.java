package com.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.musicmanagement.datatypes.Album;
import com.musicmanagement.datatypes.Singer;
import com.musicmanagement.datatypes.User;

/**
 * Class that handles connection and creation of tables for the database.
 */
public class DBConnection {
private static String dbhost = "jdbc:mysql://localhost:3306/music_manager";
private static String username = "root";
private static String password = "Moomper2410!";
private static Connection conn;

    public static void main(String[] args) throws Exception {
        createTables();
        
    }


    /**
     * Establish connection to the database.
     * @return connection session with database.
     */
    @SuppressWarnings("finally")
    public static Connection getDBConnection() {
        try  {
            conn = DriverManager.getConnection(
                    dbhost, username, password);
        } catch (SQLException e) {
            System.out.println("Cannot create database connection");
            e.printStackTrace();
        } finally {
            return conn;
        }
    }

    /**
     * Creates the tables for the music management data if they have not already been created.
     * @throws Exception
     */
    public static void createTables() throws Exception{
        try{
            Connection conn = getDBConnection();
            // User Table
            PreparedStatement createUserTable = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS user(" +
                            "id int NOT NULL AUTO_INCREMENT," +
                            " username varchar(255)," +
                            " password varchar(255)," +
                            " role varchar(255)," +
                            " PRIMARY KEY(id))," +
                            " UNIQUE(username)"
            );
            createUserTable.executeUpdate();
            // Singer Table
            PreparedStatement createSingerTable = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS singer(" +
                            "id int NOT NULL AUTO_INCREMENT," +
                            "name varchar(255)," +
                            "dob int," +
                            "sex varchar(255)," +
                            "company varchar(255)," +
                            "PRIMARY KEY(id))," +
                            "UNIQUE(name)"
            );
            createSingerTable.executeUpdate();
            //Album Table
            PreparedStatement createAlbumTable = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS album(" +
                            "id int NOT NULL AUTO_INCREMENT," +
                            "title varchar(255)," +
                            "year int," +
                            "singer varchar(255," +
                            "company varchar(255)," +
                            "PRIMARY KEY(id))," +
                            "UNIQUE(title)"
            );
            createAlbumTable.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally{
            System.out.println("Function completed");
        }
    }

    public static void addUser(User user) throws Exception {    
        
        try{
            Connection conn = getDBConnection();
            PreparedStatement posted = conn.prepareStatement(
                    "INSERT INTO user(username, password, role) VALUES ('" + user.getUsername() +"', '"+ user.getPassword() +"', '"+ user.getRole() +"')"
            );
            posted.executeUpdate();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally{System.out.println("Insert completed");}
    }

    public static void addAlbum(Album album) throws Exception {    
        
        try{
            Connection conn = getDBConnection();
            PreparedStatement posted = conn.prepareStatement(
                    "INSERT INTO album(title, year, singer, company) VALUES ('" + album.getName() +"', '"+ album.getYear() +"', '"+ album.getSinger() +"', '"+ album.getCompany() +"')"
            );
            posted.executeUpdate();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally{System.out.println("Insert completed");}
    }

    public static void addSinger(Singer singer) throws Exception {    
        
        try{
            Connection conn = getDBConnection();
            PreparedStatement posted = conn.prepareStatement(
                    "INSERT INTO singer(name, sex, company, dob) VALUES ('" + singer.getName() +"', '"+ singer.getSex() +"', '"+ singer.getCompany() +"', '"+ singer.getDob() +"')"
            );
            posted.executeUpdate();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally{System.out.println("Insert completed");}
    }
}
