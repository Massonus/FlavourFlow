package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Message;
import com.massonus.rccnavigator.entity.MessageItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    Message findMessageById(Long id);

    Message findMessageByAuthorId(Long id);

    Set<Message> findMessagesByMessageItemType(MessageItemType messageItemType);

}
