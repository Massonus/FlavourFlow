package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

    Image findImageById(Long id);

    Image findImageByName(String name);

}
