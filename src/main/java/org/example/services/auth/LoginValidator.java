package org.example.services.auth;

import org.example.repo.EmployeeRepository;
import org.example.repo.LoginRepository;

import java.io.IOException;

public class LoginValidator {

    private Integer id;

    //It sees if the email exists
    public boolean loginEmail(String email) throws IOException{
        LoginRepository repo= new LoginRepository();
        id = repo.read(email);
        if(id==null){
            return false;
        }
        return true;
    }

    //It is checking if the code is correct
    public boolean login(EmployeeRepository employee,String code) throws IOException {
        return employee.readPassword(id, code);
    }

    public Integer getId(){
        return id;
    }
}
