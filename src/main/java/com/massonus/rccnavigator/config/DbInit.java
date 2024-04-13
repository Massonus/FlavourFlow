package com.massonus.rccnavigator.config;

import com.massonus.rccnavigator.entity.Role;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DbInit {

    private final UserService userService;

    @Autowired
    public DbInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {

        final User user = new User();
        user.setEmail("user@gmail.com");
        user.setUsername("cat");
        user.setPassword("cat");
        user.setRedactor("system");
        user.setRoles(Collections.singleton(Role.USER));

        userService.saveUser(user);

        final User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRedactor("system");
        admin.setRoles(Collections.singleton(Role.ADMIN));

        userService.saveUser(admin);

    }
}
