package com.BrianTorres.service;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadFileService {
    
    
    public String guardarImagen(MultipartFile imagen){

        if (!imagen.isEmpty())
        {
            Path malo=Paths.get("Taller2\\src\\main\\resources\\static\\images//");
            //Path temporal=Paths.get("C:\\Users\\eltio\\OneDrive\\Escritorio\\taller2Final\\Taller2\\src\\main\\resources\\static\\images");
            //Path carpeta=Paths.get("C:\\ImagenesFinalSpring");
            String rutaAbsoluta=malo.toFile().getAbsolutePath();
            //String rutaAbsolutaFunciona=carpeta.toFile().getAbsolutePath();
            System.out.println(malo.toAbsolutePath());

            if (imagen.getContentType().equals("image/jpeg") || imagen.getContentType().equals("image/png"))
            {             
                try {
                    /*byte[] bytesImgFunciona = imagen.getBytes();
                    Path rutacompleta = Paths.get(rutaAbsolutaFunciona+ "//" + imagen.getOriginalFilename());
                    Files.write(rutacompleta, bytesImgFunciona);*/
                    
                    byte[] bytesImgNofunciona = imagen.getBytes();
                    Path rutacompletaNoFuncional = Paths.get(rutaAbsoluta+ "//" + imagen.getOriginalFilename());
                    Files.write(rutacompletaNoFuncional, bytesImgNofunciona);

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
        //String ruta="C:\\Users\\eltio\\Downloads\\Taller2\\Taller2\\images";
        String ruta="Taller2\\src\\main\\resources\\static\\images//";
        File file = new File(ruta+nombre);
        file.delete();
    }

}
