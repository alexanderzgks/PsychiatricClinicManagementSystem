package org.example.services.repositorySupport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Centralizes safe directory creation for repository storage paths.
 */
public class PathManager {

    // Creates the target directory tree and reports filesystem errors to stderr.
    public static void create(Path path){
        try{
            if(path == null){
                throw new IllegalArgumentException("PathManager.create(): The path is null");
            }
            Files.createDirectories(path);
        }catch (IOException e){
            System.err.print("PathManager.create(): Failed to create path ->" + e.getMessage());
        }
    }
}
