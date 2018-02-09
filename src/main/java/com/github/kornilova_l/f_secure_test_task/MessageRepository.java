package com.github.kornilova_l.f_secure_test_task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message, Long> {

}