package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findByUsername(String nickname);

}

