package com.massonus.rccnavigator.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.massonus.rccnavigator.dto.ImageResponseDto;
import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.ImageItemType;
import com.massonus.rccnavigator.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;

@Service
public class ImageService {

    private final ImageRepo imageRepo;

    private final AccessTokenService tokenService;

    @Autowired
    public ImageService(ImageRepo imageRepo, AccessTokenService tokenService) {
        this.imageRepo = imageRepo;
        this.tokenService = tokenService;
    }

    public ImageResponseDto upload(final MultipartFile file, final Long id, final String type) {

        ImageResponseDto imageResponseDto = new ImageResponseDto();

        try {
            DbxRequestConfig config;
            config = new DbxRequestConfig("dropbox/RCC Navigator");
            DbxClientV2 client;
            client = new DbxClientV2(config, tokenService.getAccessToken());

            try (InputStream in = new BufferedInputStream(file.getInputStream())) {
                client.files().uploadBuilder("/RCCImages/" + type + "/" + type + id + ".jpg")
                        .uploadAndFinish(in);
                String url = client.sharing().createSharedLinkWithSettings("/RCCImages/" + type + "/" + type + id + ".jpg").getUrl();
                imageResponseDto.setUrl(url + "&raw=1");

            } catch (DbxException ex) {
                System.out.println(ex.getMessage());
                imageResponseDto.setStatus(500);
                return imageResponseDto;
            }

        } catch (Exception e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }

        imageResponseDto.setStatus(200);
        imageResponseDto.setImageName(type + id);
        return imageResponseDto;
    }

    public Image getImageByNameAndImageItemType(final String name, final ImageItemType imageItemType) {

        return imageRepo.findImageByNameAndImageItemType(name, imageItemType);
    }
}
