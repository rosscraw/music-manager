package com.musicmanagement.datatypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
/**
 * Data Structure to represent a User of the system.
 */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    // Currently no different roles.
    @Column(name = "role")
    private String role = "ROLE_USER";

    /**
     * Default constructor.
     */
    public User() {
    }    

    /**
     * Primary constructor.
     *
     * @param username the user's username
     */

    public User(String username) { this.username = username;}

    /**
     * @return the user's id.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the user's id.
     * @return this.
     */
    public User setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @return the user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the user's username.
     * @return this.
     */
    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * @return the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the user's password.
     * @return this.
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * @return the user's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the user's role.
     * @return this.
     */
    public User setRole(String role) {
        this.role = role;
        return this;
    }

}