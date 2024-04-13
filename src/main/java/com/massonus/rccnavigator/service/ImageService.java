package com.massonus.rccnavigator.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.massonus.rccnavigator.dto.ImageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;

@Service
public class ImageService {

    private final AccessTokenService tokenService;

    @Autowired
    public ImageService(AccessTokenService tokenService) {
        this.tokenService = tokenService;
    }

    public ImageResponseDto uploadImage(final MultipartFile file, final Long id, final String type) {

        ImageResponseDto imageResponseDto = new ImageResponseDto();

        DbxRequestConfig config = new DbxRequestConfig("dropbox/RCC Navigator");
        DbxClientV2 client = new DbxClientV2(config, tokenService.getAccessToken());

        try {

            try (InputStream in = new BufferedInputStream(file.getInputStream())) {
                client.files().uploadBuilder("/RCCImages/" + type + "/" + type + id + ".jpg")
                        .uploadAndFinish(in);
                String url = client.sharing().createSharedLinkWithSettings("/RCCImages/" + type + "/" + type + id + ".jpg").getUrl();
                imageResponseDto.setUrl(url + "&raw=1");

            } catch (DbxException e) {
                System.out.println(e.getMessage());
                imageResponseDto.setStatus(500);
                return imageResponseDto;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        imageResponseDto.setStatus(200);
        imageResponseDto.setImageName(type + id);
        return imageResponseDto;
    }

    public ImageResponseDto deleteImage(final String type, final Long id) {
        ImageResponseDto imageResponseDto = new ImageResponseDto();

        DbxRequestConfig config = new DbxRequestConfig("dropbox/RCC Navigator");
        DbxClientV2 client = new DbxClientV2(config, tokenService.getAccessToken());

        try {
            client.files().deleteV2("/RCCImages/" + type + "/" + type + id + ".jpg");
        } catch (DbxException e) {
            System.out.println(e.getMessage());
            imageResponseDto.setStatus(500);
            return imageResponseDto;
        }

        imageResponseDto.setStatus(200);
        return imageResponseDto;
    }
}
