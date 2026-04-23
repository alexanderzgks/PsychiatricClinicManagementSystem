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

/**
 * Persists patient records and provides JSON-backed read access by id or as a
 * full collection.
 */
public class PatientRepository {

    private final ObjectMapper mapper;
    private final Path path;
    private HashMap<Integer, Patient> patient;

    // Prepares the JSON mapper and the storage location for patient data.
    public PatientRepository() throws IOException{
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.path = Path.of("src/main/resources/data/patient/patient.json");
    }

    // Uses file existence as a simple repository initialization check.
    public boolean isEmpty(){
        return Files.exists(path);
    }

    // Persists the provided patient records, creating the data folder if required.
    public void save(HashMap<Integer, Patient> map) throws IOException{
        if(isEmpty()){
            throw new IllegalArgumentException("PatientRepository.save(): The map of patient is empty");
        }
        if(!Files.exists(path.getParent())){
            PathManager.create(path.getParent());
        }
        mapper.writeValue(path.toFile(), map);
    }

    // Loads one patient by id, initializing the in-memory cache on first use.
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

    // Reads the entire patient dataset from disk.
    public HashMap<Integer, Patient> read() throws IOException{
        return mapper.readValue(path.toFile(), new TypeReference<HashMap<Integer, Patient>>() {});
    }

}
