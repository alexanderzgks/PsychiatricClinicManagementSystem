package org.example.services.repositorySupport;

import org.example.model.Employee;
import org.example.repo.EmployeeRepository;
import org.example.repo.LoginRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class FakeJson {

    public static void createFakeEmployee(EmployeeRepository employee, LoginRepository id) throws IOException {
        HashMap<Integer, Employee> employees = new HashMap<>();
        HashMap<String, Integer> emailAndId = new HashMap<>();
        Random random = new Random();
        for(int i = 1; i<=5; i++){
            Employee empl = new Employee(i,
                    String.format("employee%03d", i),
                    String.format("email%03d@gmail.com", i),
                    String.format("password%03d", i));
            employees.put(i,empl);
            emailAndId.put(String.format("email%03d@gmail.com", i), i);
        }
        employee.save(employees);
        id.save(emailAndId);
    }
}
