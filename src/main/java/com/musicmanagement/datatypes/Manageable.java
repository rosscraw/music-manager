package com.musicmanagement.datatypes;

/**
 * Interface to be implemented by any entities that are manageable.
 */
public interface Manageable {

    /**
     * @return name of entity.
     */
    public String getName();

    /**
     * @param name the entity's name
     * @return the entity.
     */
    public Object setName(String name);

    /**
     * @return the company that manages the entity.
     */
    public String getCompany();

    /**
     * @param company the company that manages the entity.
     * @return the entity.
     */
    public Object setCompany(String company);  
    
    
}
