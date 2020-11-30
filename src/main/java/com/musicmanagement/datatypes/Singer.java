package com.musicmanagement.datatypes;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;

@Entity
@Table(name = "singer", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
/**
 * Data structure to represent a music artist.
 */
public class Singer implements Manageable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    // Update this to object?
    @Column(name = "company")
    private String company;

    @Column(name = "dob")
    private int dob;

    @Column(name = "date")
    private Date date;

    /**
     * Default Constructor.
     */
    public Singer() {

    }

    /**
     * Primary constructor.
     *
     * @param name    the singer's name.
     * @param company the company that manages singer.
     */
    public Singer(String name, String company) {
        this.name = name;
        this.company = company;
    }

    /**
     * Secondary Constrcutor
     *
     * @param name    the singer's name.
     * @param company the company that manages the singer.
     * @param dob     the singer's date of birth.
     * @param sex     the singer's sex..
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    public Singer(String name, String company, int dob, String sex) {
        this.name = name;
        this.company = company;
        this.dob = dob;
        this.sex = sex;
        int year = dob / 10000 - 1900;
        int month = (dob % 10000) / 100 - 1;
        int day = dob % 100;
        setDate(new Date(year, month, day));
    }

    
    /**
     * @return the singer's id.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the singer's id.
     * @return this.
     */
    public Singer setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @return the date of birth in date format.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date of birth in date format.
     * @return this.
     */
    public Singer setDate(Date date) {
        this.date = date;
        return this;
    }

    /**
     * @return the singer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the singer's name.
     * @return this.
     */
    public Singer setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return the company that manages the singer.
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company that manages the singer.
     * @return this.
     */
    public Singer setCompany(String company) {
        this.company = company;
        return this;
    }

    /**
     * @return the singer's date of birth.
     */
    public int getDob() {
        return dob;
    }

    /**
     * @param dob the singer's date of birth.
     * @return this.
     */
    public Singer setDob(int dob) {
        this.dob = dob;
        return this;
    }

    /**
     * @return the singer's sex.
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the singer's sex.
     * @return this.
     */
    public Singer setSex(String sex) {
        this.sex = sex;
        return this;
    }
}