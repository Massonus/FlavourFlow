package com.massonus.flavourflow.controller;

import com.massonus.flavourflow.dto.ImageResponseDto;
import com.massonus.flavourflow.service.CompanyService;
import com.massonus.flavourflow.service.ImageService;
import com.massonus.flavourflow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/upload-product")
    @ResponseBody
    public ImageResponseDto uploadProductImage(@RequestParam("file") MultipartFile file,
                                               @RequestParam Long productId) {

        ImageResponseDto upload = imageService.uploadImage(file, productId, "product".toUpperCase());

        if (upload.getStatus() == 500) {
            return upload;
        }

        productService.setProductImage(productId, upload);


        return upload;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/upload-company")
    @ResponseBody
    public ImageResponseDto uploadCompanyImage(@RequestParam("file") MultipartFile file,
                                               @RequestParam Long companyId) {

        ImageResponseDto upload = imageService.uploadImage(file, companyId, "company".toUpperCase());

        if (upload.getStatus() == 500) {
            return upload;
        }

        companyService.setCompanyImage(companyId, upload);

        return upload;
    }
}