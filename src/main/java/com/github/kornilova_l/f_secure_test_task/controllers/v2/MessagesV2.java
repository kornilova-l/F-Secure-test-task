package com.github.kornilova_l.f_secure_test_task.controllers.v2;

import com.github.kornilova_l.f_secure_test_task.Message;
import com.github.kornilova_l.f_secure_test_task.controllers.AbstractMessagesController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Handler for version 2 requests
 * Messages returned by the second version should return all 4 fields.
 * The second version also takes a parameter which defines the format in which the response is returned
 * (supported formats could be e.g. JSON and XML).
 */
@RestController
public class MessagesV2 extends AbstractMessagesController {

    @RequestMapping(name = name, method = RequestMethod.GET, params = {"version=2", "format=xml"}, produces = MediaType.APPLICATION_XML_VALUE)
    public List<Message> getXml() {
        return repository.findAll();
    }

    @RequestMapping(name = name, method = RequestMethod.GET, params = {"version=2", "format=json"})
    public List<Message> getJson() {
        return repository.findAll();
    }
}
