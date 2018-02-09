package com.github.kornilova_l.f_secure_test_task;

import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final UrlValidator urlValidator = new UrlValidator();
    @Autowired
    private MessageRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Message> findAll() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Message add(@Validated @RequestBody Message message) {
        if (urlValidator.isValid(message.getUrl())) { // validate url
            return repository.saveAndFlush(message);
        }
        return null;
    }
}
