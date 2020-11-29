package com.dataloader;

import java.sql.Connection;
import java.sql.Date;
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
                            " PRIMARY KEY (id)," +
                            " UNIQUE (username))"
            );
            createUserTable.executeUpdate();
            // Singer Table
            PreparedStatement createSingerTable = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS singer(" +
                            "id int NOT NULL AUTO_INCREMENT," +
                            " name varchar(255)," +
                            " dob int," +
                            " sex varchar(255)," +
                            " company varchar(255)," +
                            " date date," +
                            " PRIMARY KEY (id)," +
                            " UNIQUE (name))"
            );
            createSingerTable.executeUpdate();
            //Album Table
            PreparedStatement createAlbumTable = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS album(" +
                            "id int NOT NULL AUTO_INCREMENT," +
                            " title varchar(255)," +
                            " year int," +
                            " singer varchar(255)," +
                            " company varchar(255)," +
                            " PRIMARY KEY (id)," +
                            " UNIQUE (title)," +
                            " FOREIGN KEY (singer) REFERENCES Singer(name) ON DELETE CASCADE)"
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

    /**
     * Inserts a User object into the user table.
     * @param user the user to be inserted.
     * @throws Exception
     */
    public static void addUser(User user) throws Exception {    
        
        try{
            Connection conn = getDBConnection();
            String sql = "INSERT INTO user(username, password, role) VALUES(?, ?, ?)";
            PreparedStatement posted = conn.prepareStatement(sql);
            posted.setString(1, user.getUsername());
            posted.setString(2, user.getPassword());
            posted.setString(3, user.getRole());
            posted.executeUpdate();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally{System.out.println("Insert completed");}
    }

    /**
     * Inserts an album object into the album table
     * @param album the album to be inserted.
     * @throws Exception
     */
    public static void addAlbum(Album album) throws Exception {                
        try{
            Connection conn = getDBConnection();
            String sql = "INSERT INTO album(title, year, singer, company) VALUES (?, ?, ?, ?)"; 
            PreparedStatement posted = conn.prepareStatement(sql);
                posted.setString(1, album.getName());
                posted.setInt(2, album.getYear());
                posted.setString(3, album.getSinger());
                posted.setString(4, album.getCompany());
                posted.executeUpdate();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally{System.out.println("Insert completed");}
    }

    /**
     * Inserts a singer object into the database.
     * @param singer the singer to be inserted.
     * @throws Exception
     */
    public static void addSinger(Singer singer) throws Exception {            
        try{
            Connection conn = getDBConnection();
            String sql = "INSERT INTO singer(name, sex, company, dob, date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement posted = conn.prepareStatement(sql);
            posted.setString(1, singer.getName());
            posted.setString(2, singer.getSex());
            posted.setString(3, singer.getCompany());
            posted.setInt(4, singer.getDob());
            posted.setDate(5, new Date(singer.getDate().getTime()));
            posted.executeUpdate();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally{System.out.println("Insert completed");}
    }
}
