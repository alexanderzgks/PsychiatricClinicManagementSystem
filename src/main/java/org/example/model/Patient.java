package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a patient record with basic profile and condition data persisted
 * in JSON.
 */
public class Patient {

    private int id;
    private String username;
    private int age;
    private String email;
    private String condition;

    @JsonCreator
    public Patient(@JsonProperty("id") int id,
                   @JsonProperty("username") String username,
                   @JsonProperty("age") int age,
                   @JsonProperty("email") String email,
                   @JsonProperty("condition") String condition){
        this.id = id;
        this.username = username;
        this.age = age;
        this.email = email;
        this.condition = condition;
    }

    //Getters
    public int getId(){ return id; }
    public String getUsername(){ return username; }
    public int getAge(){ return age; }
    public String getEmail(){ return email; }
    public String getCondition(){ return condition; }

    //Setters
    public void setId(int id){ this.id = id; }
    public void setUsername(String username){ this.username = username; }
    public void setAge(int age){ this.age = age; }
    public void setEmail(String email){ this.email = email; }
    public void setCondition(String condition){ this.condition = condition; }



}
