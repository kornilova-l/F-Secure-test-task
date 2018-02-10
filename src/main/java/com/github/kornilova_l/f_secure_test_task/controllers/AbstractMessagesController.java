package com.github.kornilova_l.f_secure_test_task.controllers;

import com.github.kornilova_l.f_secure_test_task.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Contains repository {@link MessageRepository} which is common for all messages controllers
 */
abstract public class AbstractMessagesController {
    @Autowired
    protected MessageRepository repository;
    protected final String name = "/messages";
}
