package org.example.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.model.Employee;
import org.example.services.repositorySupport.PathManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class EmployeeRepository {

    private final ObjectMapper mapper;
    private final Path path;
    private HashMap<Integer, Employee> employee;

    //Constructor
    public EmployeeRepository() throws IOException{
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.path = Path.of("src/main/resources/data/employee/employee.json");
    }

    //We suppose that when the JSON files exist it is not empty.
    public boolean isEmpty() throws IOException{
        return Files.exists(path);
    }

    //Save the Fake Employee
    public void save(HashMap<Integer, Employee> map) throws IOException{
        if(map.isEmpty()){
            throw new IllegalArgumentException("EmployeeRepository.save(): The map of the employee is empty");
        }
        if(!Files.exists(path.getParent())){
            PathManager.create(path.getParent());
        }
        mapper.writeValue(path.toFile(), map);
    }

    public Employee getEmployee(Integer id) throws IOException{
        if(id==null){
            throw new IllegalArgumentException("EmployeeRepository.read(): Id is null");
        }
        if(!Files.exists(path)){
            throw new IllegalArgumentException("EmployeeRepository.read(): Path does not exist");
        }
        if(employee.isEmpty()){
            if(!employee.containsKey(id)) {
                employee.clear();
                employee =  mapper.readValue(path.toFile(), new TypeReference<HashMap<Integer, Employee>>() {});
            }
        }else{
            employee =  mapper.readValue(path.toFile(), new TypeReference<HashMap<Integer, Employee>>() {});
        }
        return employee.get(id);
    }

    //Check if the Password
    public boolean readPassword(Integer id, String password) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("EmployeeRepository.read(): Id is null");
        }
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("EmployeeRepository.read(): Path does not exist");
        }
        employee = mapper.readValue(path.toFile(), new TypeReference<HashMap<Integer, Employee>>() {
        });
        String code = employee.get(id).getPassword();
        if (!code.equals(password)) {
            return false;
        }
        return true;
    }

}
