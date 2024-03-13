package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.service.CompanyService;
import com.massonus.rccnavigator.service.ImageService;
import com.massonus.rccnavigator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Objects;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final ProductService productService;
    private final CompanyService companyService;

    @Autowired
    public ImageController(ImageService imageService, ProductService productService, CompanyService companyService) {
        this.imageService = imageService;
        this.productService = productService;
        this.companyService = companyService;
    }

    @RequestMapping(value = "/images/{id}.jpg")
    public ResponseEntity<?> showImage(@PathVariable Long id) {

        Image image = imageService.getImageById(id);
        return ResponseEntity.ok()
                .header("fileName", image.getName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/upload-product")
    public ResponseEntity<?> uploadProductImage(@RequestParam("file") MultipartFile file,
                                                @RequestParam(required = false) Long companyId,
                                                @RequestParam(required = false) String title,
                                                @RequestParam(required = false) Long productId) {

        final Image upload = imageService.upload(file);

        if (Objects.isNull(productId)) {
            productService.setProductImage(title, companyId, upload);
        } else {
            productService.setProductImage(productId, upload);
        }

        return ResponseEntity.ok("upload success");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/upload-company")
    public ResponseEntity<?> uploadCompanyImage(@RequestParam("file") MultipartFile file,
                                                @RequestParam(required = false) String title,
                                                @RequestParam(required = false) Long companyId) {

        final Image upload = imageService.upload(file);

        if (Objects.isNull(title)) {
            companyService.setCompanyImage(companyId, upload);
        } else {
            companyService.setCompanyImage(title, upload);
        }

        return ResponseEntity.ok("upload success");
    }
}
