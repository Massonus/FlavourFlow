package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepo imageRepo;

    @Autowired
    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public Image upload(final MultipartFile resource) throws IOException {
        Image createdFile = new Image();
        createdFile.setBytes(resource.getBytes());
        createdFile.setName(resource.getOriginalFilename());
        createdFile.setSize(resource.getSize());
        createdFile.setContentType(resource.getContentType());

        imageRepo.save(createdFile);
        return createdFile;
    }

    public Image getImageById(final Long id) {

        return imageRepo.findImageById(id);

    }
}
