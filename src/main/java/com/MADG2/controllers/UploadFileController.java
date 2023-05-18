package com.MADG2.controllers;

import com.MADG2.common.Constants;
import com.MADG2.service.uploads.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/uploads")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class UploadFileController {

    @Autowired
    UploadFileService uploadFileService;
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> showImage(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(recurso);
    }

    @DeleteMapping("/{filename:.+}")
    public void eliminarFoto(@PathVariable String filename){
        uploadFileService.delete(filename);
    }

    @PostMapping
    public String guardarFoto(@RequestParam("file") MultipartFile foto) throws IOException {
        String rutaAbsolutaExterna = Constants.UPLOADS_FOLDER;
        byte[] imageBytes = foto.getBytes();
        String nombre = foto.getOriginalFilename();
        Path rutacompletaExterna = Paths.get(rutaAbsolutaExterna + "//" + nombre);
        //Files.write(rutacompletaExterna, imageBytes);

        String uniqueFilename = null;
        try {
            uniqueFilename = uploadFileService.copy(foto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  uniqueFilename;
    }
}
