package org.example.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.services.repositorySupport.PathManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class LoginRepository {

    private final ObjectMapper mapper;
    private final Path path;

    //Constructor
    public LoginRepository() throws IOException{
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.path = Path.of("src/main/resources/data/login/login.json");
    }

    //Save
    public void save(HashMap<String,Integer> map) throws IOException{
        if(map.isEmpty()){
            throw new IllegalArgumentException("LoginRepository.save(): The map that saves the emails and the ids is empty");
        }
        if(!Files.exists(path.getParent())){
            PathManager.create(path.getParent());
        }
        mapper.writeValue(path.toFile(), map);
    }

    //Email Exits Or Not
    public Integer read(String email) throws IOException{
        if(email.isEmpty()){
            throw new IllegalArgumentException("LoginRepository.read(): The email is null");
        }
        if(!Files.exists(path.getParent())){
            throw new IllegalArgumentException("LoginRepository.read(): The path does not exist");
        }
        HashMap<String, Integer> map = mapper.readValue(path.toFile(), new TypeReference<HashMap<String, Integer>>() {});
        if(map.containsKey(email)){
            return map.get(email);
        }else{
            return null;
        }
    }



}
