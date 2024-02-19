package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findUserById(Long id);
    User findByUsername(String nickname);

}

