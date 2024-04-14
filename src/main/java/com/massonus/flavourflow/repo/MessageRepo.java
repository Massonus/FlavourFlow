package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    Message findMessageById(Long id);

    Set<Message> findMessagesByItemId(Long itemId);

}
