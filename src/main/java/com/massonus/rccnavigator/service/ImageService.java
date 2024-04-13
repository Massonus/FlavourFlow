package com.massonus.rccnavigator.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.massonus.rccnavigator.dto.ImageResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;

@Service
public class ImageService {

    private static final String ACCESS_TOKEN = "sl.BzTyKRK_wu8ph0bbVEgxBvc5dGRE-FzsU48Fqvxm8SZNCSSE5_dskOy_kWUB0GpDUPSTCLwJxEfeJqv9rZdt3l7i60gyZT5DeS8bzanprxHwmZ2DOKDnXsVDpgDImoC09dknoKntJ-W3aAgSZIj9";

    public ImageResponseDto upload(final MultipartFile file, final Long id, final String type) {

        ImageResponseDto imageResponseDto = new ImageResponseDto();

        try {
            DbxRequestConfig config;
            config = new DbxRequestConfig("dropbox/RCC Navigator");
            DbxClientV2 client;
            client = new DbxClientV2(config, ACCESS_TOKEN);

            try (InputStream in =  new BufferedInputStream(file.getInputStream())) {
                client.files().uploadBuilder("/RCCImages/" + type + id + ".jpg")
                        .uploadAndFinish(in);
                String url = client.sharing().createSharedLinkWithSettings("/RCCImages/" + type + id + ".jpg").getUrl();
                imageResponseDto.setUrl(url + "&raw=1");
            }
            catch (DbxException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (Exception e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }

        return imageResponseDto;
    }
}
