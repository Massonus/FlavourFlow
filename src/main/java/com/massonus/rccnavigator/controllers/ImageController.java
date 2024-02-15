package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/image")
    public String adder() {

        return "menu/image";

    }

    @PostMapping("/addImage")
    public String addImage(@RequestParam("file") MultipartFile multipartFile) {
        try {
            imageService.upload(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/images/{id}.jpg")
    public ResponseEntity<?> showImage(@PathVariable Long id) {

        Image image = imageService.getImageById(id).get();
        return ResponseEntity.ok()
                .header("fileName", image.getName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));

    }

    @GetMapping(value = "/images/{id}")
    public String showImageOnFreemarker(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);

        return "info/image_info";
    }
}
