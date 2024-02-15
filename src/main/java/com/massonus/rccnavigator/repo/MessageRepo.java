package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    Message findMessageByAuthorId(Long id);

}
