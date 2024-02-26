package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    Message findMessageById(Long id);

    Message findMessageByAuthorId(Long id);

    Set<Message> findMessagesByCompanyId(Long id);

    Set<Message> findMessagesByProductId(Long id);

}
