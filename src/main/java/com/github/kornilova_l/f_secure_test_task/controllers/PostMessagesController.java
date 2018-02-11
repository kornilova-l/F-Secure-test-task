package com.github.kornilova_l.f_secure_test_task.controllers;

import com.github.kornilova_l.f_secure_test_task.Message;
import org.apache.commons.validator.UrlValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * This class is needed to validate url of uploaded messages {@link Message}
 */
@RestController
public class PostMessagesController extends AbstractMessagesController {
    private final UrlValidator urlValidator = new UrlValidator();

    @RequestMapping(name = name, method = RequestMethod.POST)
    public Message add(@Validated @RequestBody Message message, HttpServletResponse response) {
        if (urlValidator.isValid(message.getUrl())) { // validate url
            return repository.saveAndFlush(message);
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }
}
