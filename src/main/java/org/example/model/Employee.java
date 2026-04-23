package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a clinic employee account stored in JSON and used during
 * authentication.
 */
public class Employee {

    private int id;
    private String username;
    private String email;
    private String password;

    @JsonCreator
    public Employee(@JsonProperty("id") int id,
                    @JsonProperty("username") String username,
                    @JsonProperty("email") String email,
                    @JsonProperty("password") String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //Getters
    public int getId(){ return id; }
    public String getUsername(){ return username; }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }

    //Setters
    public void setId(int id){ this.id = id; }
    public void setUsername(String username){ this.username = username; }
    public void setEmail(String email){ this.email = email; }
    public void setPassword(String password){ this.password = password; }
}
