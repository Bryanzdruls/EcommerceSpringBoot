package com.BrianTorres.service;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.BrianTorres.model.Producto;


@Service
public class UploadFileService {
    
    @Autowired
    private IProductoService productoService;
    
    public String guardarImagen(MultipartFile imagen, Producto producto){

        if (!imagen.isEmpty())
        {
            //Path malo=Paths.get("src/main/resources/static/images");
            //Path temporal=Paths.get("Taller2/images");
            //Path carpeta=Paths.get("images");
            //String rutaAbsoluta=temporal.toFile().getAbsolutePath();
            String folder ="Taller2/images//";
    
            if (imagen.getContentType().equals("image/jpeg") || imagen.getContentType().equals("image/png"))
            {             
                //String NombreOriginal=imagen.getOriginalFilename();
                //String extension = NombreOriginal.substring(NombreOriginal.lastIndexOf("."));
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
        //String ruta="C:\\Users\\eltio\\Downloads\\Taller2\\Taller2\\images";
        String ruta="Taller2//images";
        File file = new File(ruta+nombre);
        file.delete();
    }

}
