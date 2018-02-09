package com.github.kornilova_l.f_secure_test_task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select new Message(m.sender, m.title, m.content) from Message m" )
    List<Message> getSenderTitleAndContent();
}