package com.massonus.rccnavigator.config;

import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.CompanyService;
import com.massonus.rccnavigator.service.ProductService;
import com.massonus.rccnavigator.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    private final CompanyService companyService;
    private final UserService userService;

    public DbInit(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {

        companyService.createElementAuto();

        final User user = new User();
        user.setEmail("user@gmail.com");
        user.setUsername("cat");
        user.setPassword("cat");

        userService.addUser(user, false);

        final User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");

        userService.addUser(admin, true);
    }
}
