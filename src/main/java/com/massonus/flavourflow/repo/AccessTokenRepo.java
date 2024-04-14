package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepo extends JpaRepository<AccessToken, Long> {

}
