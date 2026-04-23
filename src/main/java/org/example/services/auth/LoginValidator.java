package org.example.services.auth;

import org.example.repo.EmployeeRepository;
import org.example.repo.LoginRepository;

import java.io.IOException;

/**
 * Coordinates email lookup and password verification for the console login
 * process.
 */
public class LoginValidator {

    private Integer id;

    // Resolves the employee id behind the submitted email and keeps it for the next step.
    public boolean loginEmail(String email) throws IOException{
        LoginRepository repo= new LoginRepository();
        id = repo.read(email);
        if(id==null){
            return false;
        }
        return true;
    }

    // Delegates password validation to the employee repository for the resolved employee id.
    public boolean login(EmployeeRepository employee,String code) throws IOException {
        return employee.readPassword(id, code);
    }

    public Integer getId(){
        return id;
    }
}
