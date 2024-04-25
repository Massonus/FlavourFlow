package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.AnonymousUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnonymousUserRepo extends JpaRepository<AnonymousUser, Long> {

    AnonymousUser findAnonymousUserById(Long id);

    AnonymousUser findAnonymousUserByIp(String ip);

}
