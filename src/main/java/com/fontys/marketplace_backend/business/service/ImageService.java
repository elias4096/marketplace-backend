package com.fontys.marketplace_backend.business.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    public Resource readImage(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            return new FileSystemResource(file);
        }

        return null;
    }

    public void writeImage(Integer itemId, MultipartFile image) throws IOException {
        String currentWorkingDirectory = System.getProperty("user.dir");
        String directoryPath = currentWorkingDirectory + File.separator + "images";
        String filePath = directoryPath + File.separator + itemId.toString() + ".png";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        try (FileOutputStream fout = new FileOutputStream(filePath)) {
            fout.write(image.getBytes());
        }
    }
}
