package org.example.console;

import org.example.model.Employee;
import org.example.repo.EmployeeRepository;
import org.example.services.general.UserSession;
import org.example.services.auth.LoginValidator;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the interactive console login flow and returns the employee session
 * once the credentials are validated.
 */
public class Login {

    /**
     * Repeats the login prompt until a valid email and password pair is
     * provided, then builds the session for the matching employee.
     */
    public static UserSession logIn(EmployeeRepository repo) throws IOException {
        LoginValidator validator = new LoginValidator();
        Scanner scanner = new Scanner(System.in);
        String email;
        String password;
        boolean valid = false;
        while (!valid){
            System.out.println("Psychiatric Clinic Login");
            System.out.print("Email: ");
            email = scanner.nextLine();
            if(validator.loginEmail(email)){
               System.out.print("Password: ");
               password = scanner.nextLine();
               if(validator.login(repo,password)){
                   valid = true;
               }else{
                   System.out.println("Error wrong password");
               }
            }else{
                System.out.println("Error wrong email");
            }
        }
        Employee employee = repo.getEmployee(validator.getId());
        return new UserSession(employee);
    }
}
