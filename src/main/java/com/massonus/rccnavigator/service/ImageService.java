package com.massonus.rccnavigator.service;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.massonus.rccnavigator.dto.ImageResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

@Service
public class ImageService {

    private static final String ACCESS_TOKEN = "sl.BzN4zPKwWTp4mhZUeScmIMH3vkFuQpLfamZKZhP3HeazwHGPJzxjxlelq2bULKMRFHdp3RrGoPopglNHnmZBHA7f-ACuVlJH420LqIUos-FN7VlFS7nIJ_6MYQU56VJm5_gVDvO6lO3Cv9OWn3aj";

    public ImageResponseDto upload(final MultipartFile file) {

        ImageResponseDto imageResponseDto = new ImageResponseDto();

        try {
            DbxRequestConfig config;
            config = new DbxRequestConfig("dropbox/RCC Navigator");
            DbxClientV2 client;
            client = new DbxClientV2(config, ACCESS_TOKEN);

            try (InputStream in =  new BufferedInputStream(file.getInputStream())) {
                FileMetadata metadata = client.files().uploadBuilder("/RCCImages/" + file.getOriginalFilename())
                        .uploadAndFinish(in);
                String url = client.sharing().createSharedLinkWithSettings("/RCCImages/" + file.getOriginalFilename()).getUrl();
                imageResponseDto.setUrl(url + "&raw=1");
            }
            catch (DbxException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (Exception ex1) {
            ex1.printStackTrace();
        }

        return imageResponseDto;
    }
}
