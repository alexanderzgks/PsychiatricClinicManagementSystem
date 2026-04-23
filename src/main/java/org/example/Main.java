package org.example;

import org.example.console.App;
import org.example.repo.EmployeeRepository;
import org.example.repo.LoginRepository;
import org.example.services.repositorySupport.FakeJson;


import java.io.IOException;

/**
 * Bootstraps the application and ensures the demo login data exists before the
 * console flow starts.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        LoginRepository loginRepository = new LoginRepository();
        if(!employeeRepository.isEmpty()){
            FakeJson.createFakeEmployee(employeeRepository, loginRepository);
        }
        App app = new App();
    }
}
