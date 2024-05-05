package com.massonus.flavourflow.service;

import com.massonus.flavourflow.entity.AnonymousUser;
import com.massonus.flavourflow.repo.AnonymousUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AnonymousUserService {

    private final AnonymousUserRepo anonymousRepo;

    @Autowired
    public AnonymousUserService(AnonymousUserRepo anonymousRepo) {
        this.anonymousRepo = anonymousRepo;
    }

    public AnonymousUser getAnonymousByIp(final String ip) {
        return anonymousRepo.findAnonymousUserByIp(ip);
    }

    public void saveAnonymous(final String ip) {
        if (Objects.nonNull(getAnonymousByIp(ip))) {
            return;
        }
        AnonymousUser anonymous = new AnonymousUser();
        anonymous.setIp(ip);
        anonymousRepo.save(anonymous);
    }
}
