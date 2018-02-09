package com.github.kornilova_l.f_secure_test_task;

import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final UrlValidator urlValidator = new UrlValidator();
    @Autowired
    private MessageRepository repository;

    /**
     * Handler for version 1 requests
     * Messages returned by the first version should contain only title, content and sender fields.
     * The first version must not accept any other parameters than the version parameter.
     */
    @RequestMapping(method = RequestMethod.GET, params = {"version=1"})
    public List<Message> findAll(@RequestParam Map<String, String> parameters) {
        if (parameters.size() != 1) { // "must not accept any other parameters than the version parameter"
            System.err.println("GET request for messages. Version 1. This version does not support any parameters except version. Parameters: " + parameters);
            return null;
        }
        return repository.getSenderTitleAndContent();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Message add(@Validated @RequestBody Message message) {
        if (urlValidator.isValid(message.getUrl())) { // validate url
            return repository.saveAndFlush(message);
        }
        return null;
    }
}
