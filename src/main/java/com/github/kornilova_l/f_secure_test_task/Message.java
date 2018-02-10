package com.github.kornilova_l.f_secure_test_task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.kornilova_l.f_secure_test_task.controllers.v1.MessagesV1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL) // do not output null values
public class Message {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(max = 100)
    @JsonView(MessagesV1.class)
    private String sender;
    @NotNull
    @Size(max = 100)
    @JsonView(MessagesV1.class)
    private String title;
    @NotNull
    @Size(max = 1000)
    @JsonView(MessagesV1.class)
    private String content;
    @NotNull
    private String url; /* correctness of url is validated in MessageController */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}