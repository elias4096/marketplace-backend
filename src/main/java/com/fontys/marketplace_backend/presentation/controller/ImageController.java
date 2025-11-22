package com.fontys.marketplace_backend.presentation.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/images")
public class ImageController {
    @GetMapping("/{imageName:.+}")
    public ResponseEntity<Resource> get(@PathVariable("imageName") String imageName) {
        // Todo: check type and size of image.

        String filePath = System.getProperty("user.dir") + "/images" + File.separator + imageName;
        File file = new File(filePath);

        if (file.exists()) {
            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> post(
            @RequestParam("itemId") Integer itemId,
            @RequestParam("image") MultipartFile image) {
        String fileName = itemId.toString() + ".png";
        String filePath = System.getProperty("user.dir") + "/images" + File.separator + fileName;

        try {
            FileOutputStream fout = new FileOutputStream(filePath);
            fout.write(image.getBytes());
            fout.close();

            return ResponseEntity.ok().body("File Uploaded Successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
