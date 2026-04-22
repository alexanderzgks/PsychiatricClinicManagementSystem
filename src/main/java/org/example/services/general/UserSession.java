package org.example.services.general;

import org.example.model.Employee;

public class UserSession {

    private Employee loggedIn;

    public UserSession(Employee employee){
        this.loggedIn = employee;
    }

    public int getId(){ return loggedIn.getId(); }
    public String getUsername(){ return loggedIn.getUsername(); }
    public String getEmail(){ return loggedIn.getEmail(); }
}
