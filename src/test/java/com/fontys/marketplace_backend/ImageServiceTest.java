package com.fontys.marketplace_backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.fontys.marketplace_backend.service.ImageService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
The name of your test should consist of three parts:

    Name of the method being tested
    Scenario under which the method is being tested
    Expected behavior when the scenario is invoked

The "Arrange, Act, Assert" pattern is a common approach for writing unit tests.
As the name implies, the pattern consists of three main tasks:

    Arrange your objects, create, and configure them as necessary
    Act on an object
    Assert that something is as expected

*/

class ImageServiceTest {
    private ImageService imageService;

    @BeforeEach
    void setUp() {
        imageService = new ImageService();
    }

    @Test
    void testReadImage_FileFound() {
        String filePath = "./images/test-found.png";

        try {
            new File(filePath).createNewFile();
        } catch (IOException e) {
            assertTrue(false);
        }

        Resource resource = imageService.readImage(filePath);

        assertNotNull(resource);
        assertEquals("test-found.png", resource.getFilename());

        new File(filePath).delete();
    }

    @Test
    void testReadImage_FileNotFound() {
        String filePath = "./images/test-not-found.png";

        Resource resource = imageService.readImage(filePath);

        assertNull(resource);
    }

    @Test
    void testWriteImage_CreatesDirectory() throws IOException {
        Integer itemId = 1;
        MultipartFile image = mock(MultipartFile.class);
        when(image.getBytes()).thenReturn("test_image_data".getBytes());

        imageService.writeImage(itemId, image);

        String currentWorkingDirectory = System.getProperty("user.dir");
        String expectedDirectory = currentWorkingDirectory + File.separator + "images";
        File directory = new File(expectedDirectory);
        assertTrue(directory.exists());

        File file = new File(expectedDirectory + File.separator + itemId + ".png");
        file.delete();
    }

    @Test
    void testWriteImage_CreatesFile() throws IOException {
        Integer itemId = 2;
        MultipartFile image = mock(MultipartFile.class);
        when(image.getBytes()).thenReturn("test_image_data".getBytes());

        imageService.writeImage(itemId, image);

        String currentWorkingDirectory = System.getProperty("user.dir");
        String filePath = currentWorkingDirectory + File.separator + "images" + File.separator + itemId + ".png";
        File createdFile = new File(filePath);
        assertTrue(createdFile.exists());

        createdFile.delete();
    }
}
