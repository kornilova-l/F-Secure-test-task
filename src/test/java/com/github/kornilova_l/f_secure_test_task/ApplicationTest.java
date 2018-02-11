package com.github.kornilova_l.f_secure_test_task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
/* random port is needed to get assigned port (in tests it is not default) */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
    @LocalServerPort
    private int port;

    @Test
    public void applicationTest() throws IOException {
        /* not all fields are provided */
        assertEquals(SC_BAD_REQUEST, sendMessage("{\"sender\": \"Maxim\"}"));
        /* invalid url: */
        assertEquals(SC_BAD_REQUEST, sendMessage("{\"sender\": \"Maxim\", \"title\": \"hello\", \"content\": \"how are you\", \"url\": \"invalid url\"}"));
        /* valid request */
        assertEquals(SC_OK, sendMessage("{\"sender\": \"Maxim\", \"title\": \"hello\", \"content\": \"how are you\", \"url\": \"http://example.com\"}"));

        /* version 1 does not include url */
        assertEquals(
                "[{\"sender\":\"Maxim\",\"title\":\"hello\",\"content\":\"how are you\"}]",
                getMessage(1, null));

        assertEquals(
                "[{\"sender\":\"Maxim\",\"title\":\"hello\",\"content\":\"how are you\",\"url\":\"http://example.com\"}]",
                getMessage(2, "json"));
        assertEquals(
                "<List><item><sender>Maxim</sender><title>hello</title><content>how are you</content><url>http://example.com</url></item></List>",
                getMessage(2, "xml"));
    }

    private String getMessage(int version, String format) throws IOException {
        StringBuilder urlString = new StringBuilder().append("http://localhost:").append(port).append("/messages?version=").append(version);
        if (format != null) {
            urlString.append("&format=").append(format);
        }
        URL url = new URL(urlString.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return reader.readLine();
        }
    }

    private int sendMessage(String json) throws IOException {
        URL url = new URL("http://localhost:" + port + "/messages");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Length", Integer.toString(json.getBytes().length));

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(json.getBytes());
        wr.flush();
        wr.close();

        return connection.getResponseCode();
    }
}