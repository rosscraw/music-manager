package com.musicmanagement.datatypes;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;

@Entity
@Table(name = "singer", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Singer implements Manageable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name = "name")
private String name;

@Column(name = "sex")
private String sex;

// Update this to object
@Column(name = "company")
private String company;

@Column(name = "dob")
private Date dob;

/**
     * Primary constructor.
     *
     * @param name the singer's name.
     * @param company the company that manages singer.
     */
    public Singer(String name, String company) {
        this.name = name;
        this.company = company;
    }

    /**
     * Secondary Constrcutor
     *
     * @param name the singer's name.
     * @param company the company that manages the singer.
     * @param dob the singer's date of birth.
     * @param sex the singer's sex..
     */
    public Singer(String name, String company, Date dob, String sex) {
        this.name = name;
        this.company = company;
        this.dob = dob;
        this.sex = sex;
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
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the singer's date of birth.
     * @return this.
     */
    public Singer setDob(Date dob) {
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