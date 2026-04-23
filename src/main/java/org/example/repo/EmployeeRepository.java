package org.example.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.model.Employee;
import org.example.model.Patient;
import org.example.services.repositorySupport.PathManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Persists and retrieves employee records from the JSON storage used by the
 * application.
 */
public class EmployeeRepository {

    private final ObjectMapper mapper;
    private final Path path;
    private HashMap<Integer, Employee> employee;

    // Prepares the JSON mapper and points the repository to the employee data file.
    public EmployeeRepository() throws IOException{
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.path = Path.of("src/main/resources/data/employee/employee.json");
    }

    // Uses the presence of the file as the repository initialization check.
    public boolean isEmpty(){
        return Files.exists(path);
    }

    // Writes all employee records to disk, creating the folder structure when needed.
    public void save(HashMap<Integer, Employee> map) throws IOException{
        if(isEmpty()){
            throw new IllegalArgumentException("EmployeeRepository.save(): The map of the employee is empty");
        }
        if(!Files.exists(path.getParent())){
            PathManager.create(path.getParent());
        }
        mapper.writeValue(path.toFile(), map);
    }

    // Loads a single employee by id, refreshing the in-memory cache on first access.
    public Employee getEmployee(Integer id) throws IOException{
        if(id==null){
            throw new IllegalArgumentException("EmployeeRepository.read(): Id is null");
        }
        if(!Files.exists(path)){
            throw new IllegalArgumentException("EmployeeRepository.read(): Path does not exist");
        }
        if(employee.isEmpty()){
            employee =  read();
        }
        return employee.getOrDefault(id, null);
    }

    // Validates that the stored password for the given employee matches the input.
    public boolean readPassword(Integer id, String password) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("EmployeeRepository.read(): Id is null");
        }
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("EmployeeRepository.read(): Path does not exist");
        }
        employee = read();
        String code = employee.get(id).getPassword();
        if (!code.equals(password)) {
            return false;
        }
        return true;
    }

    private HashMap<Integer, Employee> read() throws IOException{
        return mapper.readValue(path.toFile(), new TypeReference<HashMap<Integer, Employee>>() {});
    }

}
