package com.github.kornilova_l.f_secure_test_task.controllers.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.kornilova_l.f_secure_test_task.Message;
import com.github.kornilova_l.f_secure_test_task.controllers.AbstractMessagesController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Handler for version 1 requests
 * Messages returned by the first version should contain only title, content and sender fields.
 * The first version must not accept any other parameters than the version parameter.
 */
@RestController
public class MessagesV1 extends AbstractMessagesController {

    @RequestMapping(name = name, method = RequestMethod.GET, params = {"version=1"})
    @JsonView(MessagesV1.class) // accepts only 'sender', 'title' and 'content' fields
    public List<Message> getAll(@RequestParam Map<String, String> parameters) {
        if (parameters.size() != 1) { // "must not accept any other parameters than the version parameter"
            System.err.println("GET request for messages. Version 1. This version does not support any parameters except version. Parameters: " + parameters);
            return null;
        }
        return repository.findAll();
    }
}
