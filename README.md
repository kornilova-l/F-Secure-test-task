# F-Secure test task
Liudmila Kornilova

## Description
Small RESTful spring web service that stores messages.  
Service allows to upload a message and to get list of all messages.  
The app uses an embedded [H2 database](http://www.h2database.com/html/main.html).

## Uploading message
Class [Message](src/main/java/com/github/kornilova_l/f_secure_test_task/Message.java) is an Entity that contains title, content, sender and url fields.  
`@NotNull` and `@Size` annotations are used to ensure that fields are valid. Correctness of url is checked in [PostMessagesController](src/main/java/com/github/kornilova_l/f_secure_test_task/controllers/PostMessagesController.java).

## Getting messages
Application supports two response versions:
* [MessagesV1](src/main/java/com/github/kornilova_l/f_secure_test_task/controllers/v1/MessagesV1.java)
* [MessagesV2](src/main/java/com/github/kornilova_l/f_secure_test_task/controllers/v2/MessagesV2.java) 

To add support for new version one should create new RestController that inherits from [AbstractMessagesController](src/main/java/com/github/kornilova_l/f_secure_test_task/controllers/AbstractMessagesController.java).
Methods of new controller should be annotated with `@RequestMapping(name = name, method = ..., params = {"version=<supported version>"} ...)`.


## Tests
Tests are here: [ApplicationTest](src/test/java/com/github/kornilova_l/f_secure_test_task/ApplicationTest.java)
```
./mvnw test
```

## Usage
```
# start the server
./mvnw spring-boot:run 

# upload messages
curl -i -X POST -H "Content-Type:application/json" -d '{"title" : "Hello", "content": "hi", "sender": "Maxim", "url": "https://example.com"}' localhost:8080/messages
curl -i -X POST -H "Content-Type:application/json" -d '{"title" : "How are you?", "content": "hi", "sender": "Misha", "url": "https://example.com"}' localhost:8080/messages

# get messages
curl "localhost:8080/messages?version=1"
curl "localhost:8080/messages?version=2&format=xml"
curl "localhost:8080/messages?version=2&format=json"
```

## Building war
```
./mvnw clean package
```