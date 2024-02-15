package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepo extends JpaRepository<Tree, Long> {
}
