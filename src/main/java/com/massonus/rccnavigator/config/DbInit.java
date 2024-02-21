package com.massonus.rccnavigator.config;

import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.MainService;
import com.massonus.rccnavigator.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    private final MainService mainService;

    private final UserService userService;

    @Autowired
    public DbInit(MainService mainService, UserService userService) {
        this.mainService = mainService;
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {

        mainService.initialize();

        final User user = new User();
        user.setEmail("user@gmail.com");
        user.setUsername("cat");
        user.setPassword("cat");
        user.setRedactor(1337L);

        userService.saveUser(user, false);

        final User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRedactor(1337L);

        userService.saveUser(admin, true);

    }
}
