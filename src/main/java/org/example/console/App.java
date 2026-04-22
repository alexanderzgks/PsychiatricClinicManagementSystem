package org.example.console;

import org.example.repo.EmployeeRepository;
import org.example.services.general.UserSession;

import java.io.IOException;

public class App {
    EmployeeRepository repo;
    UserSession session;

    public App() throws IOException {
        this.repo = new EmployeeRepository();
        this.session = Login.logIn(repo);
    }

}
