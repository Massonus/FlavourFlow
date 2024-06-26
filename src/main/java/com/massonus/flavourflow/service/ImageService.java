package com.massonus.flavourflow.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.massonus.flavourflow.dto.ImageResponseDto;
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

        DbxRequestConfig config = new DbxRequestConfig("dropbox/Flavour Flow");
        DbxClientV2 client = new DbxClientV2(config, tokenService.getAccessToken());

        try {

            try (InputStream in = new BufferedInputStream(file.getInputStream())) {
                client.files().uploadBuilder("/FlowImages/" + type + "/" + type + id + ".jpg")
                        .uploadAndFinish(in);
                String url = client.sharing().createSharedLinkWithSettings("/FlowImages/" + type + "/" + type + id + ".jpg").getUrl();
                StringBuilder stringBuilder = new StringBuilder(url);
                String replacedLink = stringBuilder.replace(8, 23, "dl.dropboxusercontent.com").toString();
                imageResponseDto.setUrl(replacedLink);

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

        DbxRequestConfig config = new DbxRequestConfig("dropbox/Flavour Flow");
        DbxClientV2 client = new DbxClientV2(config, tokenService.getAccessToken());

        try {
            client.files().deleteV2("/FlowImages/" + type + "/" + type + id + ".jpg");
        } catch (DbxException e) {
            System.out.println(e.getMessage());
            imageResponseDto.setStatus(500);
            return imageResponseDto;
        }

        imageResponseDto.setStatus(200);
        return imageResponseDto;
    }
}
