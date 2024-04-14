package com.massonus.flavourflow.service;

import com.massonus.flavourflow.entity.AccessToken;
import com.massonus.flavourflow.repo.AccessTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessTokenService {

    private final AccessTokenRepo tokenRepo;

    @Autowired
    public AccessTokenService(AccessTokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    public AccessToken saveToken(final String value) {
        tokenRepo.deleteAll();
        AccessToken accessToken = new AccessToken();
        accessToken.setValue(value);
        return tokenRepo.save(accessToken);
    }

    public String getAccessToken() {
        List<AccessToken> all = tokenRepo.findAll();

        if (all.isEmpty()) {
            return "none";
        }
        return all.getFirst().getValue();
    }
}
