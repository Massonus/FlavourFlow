package com.massonus.flavourflow.controller;

import com.massonus.flavourflow.dto.ImageResponseDto;
import com.massonus.flavourflow.service.*;
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
    private final BasketObjectService basketObjectService;
    private final WishObjectService wishObjectService;

    @Autowired
    public ImageController(ImageService imageService, ProductService productService, CompanyService companyService, BasketObjectService basketObjectService, WishObjectService wishObjectService) {
        this.imageService = imageService;
        this.productService = productService;
        this.companyService = companyService;
        this.basketObjectService = basketObjectService;
        this.wishObjectService = wishObjectService;
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
        basketObjectService.setBasketObjectDbxImage(productId, upload.getUrl());
        wishObjectService.setWishObjectDbxImage(productId, upload.getUrl());

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
