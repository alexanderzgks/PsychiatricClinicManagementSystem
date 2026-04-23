package org.example.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.model.Patient;
import org.example.services.repositorySupport.PathManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class PatientRepository {

    private final ObjectMapper mapper;
    private final Path path;
    private HashMap<Integer, Patient> patient;

    public PatientRepository() throws IOException{
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.path = Path.of("src/main/resources/data/patient/patient.json");
    }

    //We suppose that when the JSON files exist it is not empty.
    public boolean isEmpty(){
        return Files.exists(path);
    }

    //Save Fake Patient
    public void save(HashMap<Integer, Patient> map) throws IOException{
        if(map.isEmpty()){
            throw new IllegalArgumentException("PatientRepository.save(): The map of patient is empty");
        }
        if(!Files.exists(path.getParent())){
            PathManager.create(path.getParent());
        }
        mapper.writeValue(path.toFile(), map);
    }

    //Get one Patient
    public Patient getPatient(Integer id) throws IOException{
        if(id == null){
            throw new IllegalArgumentException("PatientRepository.getEmployee(): The id is null");
        }
        if(!Files.exists(path.getParent())){
            throw new IllegalArgumentException("PatientRepositoy.getEmployee(): The path does not exist");
        }
        if(patient.isEmpty()){
            patient = read();
        }
        return patient.getOrDefault(id, null);
    }

    //Get all Patient
    public HashMap<Integer, Patient> read() throws IOException{
        return mapper.readValue(path.toFile(), new TypeReference<HashMap<Integer, Patient>>() {});
    }

}
