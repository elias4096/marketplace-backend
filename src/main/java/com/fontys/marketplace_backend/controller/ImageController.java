package com.fontys.marketplace_backend.controller;

import java.io.File;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fontys.marketplace_backend.exceptions.NotFoundException;
import com.fontys.marketplace_backend.service.ImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost" })
@RestController
public class ImageController {
    private final ImageService imageService;

    @GetMapping("images/{imageName:.+}")
    public ResponseEntity<Resource> get(@PathVariable("imageName") String imageName) {
        String filePath = System.getProperty("user.dir") + "/images" + File.separator + imageName;

        Resource resource = imageService.readImage(filePath);

        if (resource == null) {
            throw new NotFoundException("Image does not exist");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imageName + ".png" + "\"")
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }

    @PostMapping("/images")
    public ResponseEntity<String> post(
            @RequestParam("itemId") Integer itemId,
            @RequestParam("image") MultipartFile image) {
        imageService.writeImage(itemId, image);
        return ResponseEntity.ok().body("Image uploaded successfully");
    }
}
