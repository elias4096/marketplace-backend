package com.fontys.marketplace_backend.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fontys.marketplace_backend.exceptions.ImageWriteException;

@Service
public class ImageService {
    public Resource readImage(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            return new FileSystemResource(file);
        }

        return null;
    }

    public void writeImage(Integer itemId, MultipartFile image) {
        // Calculate paths
        String currentWorkingDirectory = System.getProperty("user.dir");
        String directoryPath = currentWorkingDirectory + File.separator + "images";
        String filePath = directoryPath + File.separator + itemId.toString() + ".png";

        // Create folder if it does not exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Write image bytes to file
        try (FileOutputStream fout = new FileOutputStream(filePath)) {
            fout.write(image.getBytes());
        } catch (Exception e) {
            throw new ImageWriteException("Could not use image");
        }
    }
}
