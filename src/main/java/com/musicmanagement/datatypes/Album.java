package com.musicmanagement.datatypes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
@Entity
@Table(name = "album", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Album implements Manageable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "company")
    private String company;

    @Column(name = "year")
    private int year;

    @Column(name = "singer")
    private String singer;

    /**
     * Primary Constructor
     *
     * @param title the album's title.
     * @param company the company that manages the album.
     */
    public Album(String title, String company) {
    }

    /**
     * Secondary Constrcutor
     *
     * @param title the album's title.
     * @param company the company that manages the album.
     * @param year the year the album was released.
     * @param singer the {@link Singer} of the album.
     */
    public Album(String title, String company, int year, String singer) {
        
        this.year = year;
        this.singer = singer;
    }

     /**
     * @return the album's name.
     */
    public String getName() {
        return title;
    }

    /**
     * @param name the album's name.
     * @return this.
     */
    public Album setName(String title) {
        this.title = title;
        return this;
    }

    /**
     * @return the company that manages the Album.
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company that manages the Album.
     * @return this.
     */
    public Album setCompany(String company) {
        this.company = company;
        return this;
    }


    /**
     * @return the Singer of the album.
     */
    public String getSinger() {
        return singer;
    }

    /**
     * @param singer the Singer of the album.
     * @return this.
     */
    public Album setSinger(String singer) {
        this.singer = singer;
        return this;
    }

    /**
     * @return the year the album was released.
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year the album was released.
     * @return this.
     */
    public Album setYear(int year) {
        this.year = year;
        return this;
    }

}
