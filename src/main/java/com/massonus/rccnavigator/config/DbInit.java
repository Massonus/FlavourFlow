package com.massonus.rccnavigator.config;

import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.KitchenCategoryService;
import com.massonus.rccnavigator.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    private final KitchenCategoryService categoryService;
    private final UserService userService;

    @Autowired
    public DbInit(KitchenCategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {

        for (int i = 0; i < 2; i++) {
            categoryService.createAndFillCompanyListForCategory();
        }

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
