package com.BrianTorres.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
    private String carpeta="images//";

    
    public String guardarImagen(MultipartFile file){
        try{
            if (!file.isEmpty()) {
                byte [] bytes=file.getBytes();
                Path path =  Paths.get(carpeta+file.getOriginalFilename());
                System.out.println(path.toAbsolutePath());
                Files.write(path, bytes);
                return file.getOriginalFilename();
            } 
        }catch(IOException e){
            System.out.println("error: "+e);
        }

        return "default.jpg";
    }
    public void borrarImagen(String nombre){
        String ruta="images//";
        File file = new File(ruta+nombre);
        file.delete();
    }

}
