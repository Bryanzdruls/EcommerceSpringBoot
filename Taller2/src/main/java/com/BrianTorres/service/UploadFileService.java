package com.BrianTorres.service;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.BrianTorres.model.Producto;


@Service
public class UploadFileService {
    
    
    public String guardarImagen(MultipartFile imagen, Producto producto){

        if (!imagen.isEmpty())
        {
            String folder ="Taller2/src/main/resources/static/images/";
    

            if (imagen.getContentType().equals("image/jpeg") || imagen.getContentType().equals("image/png"))
            {             

                try {
                    byte [] bytes=imagen.getBytes();
                    Path path =Paths.get(folder+imagen.getOriginalFilename());
                    System.out.println(path.toAbsolutePath());
                    Files.write(path, bytes);

                    

                    return imagen.getOriginalFilename();
                } catch (Exception e) {
                    System.out.println("error con ruta dentro de proyecto: "+e);
                }
            } else {
                System.out.println("archivo no compatible ah si");
                
            }
        }
        return "default.jpg";
    }
    public void borrarImagen(String nombre){
        String ruta="Taller2/src/main/resources/static/images/";
        File file = new File(ruta+nombre);
        file.delete();
    }

}
