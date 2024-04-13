package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.ImageItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

    Image findImageByNameAndImageItemType(String name, ImageItemType imageItemType);
}
