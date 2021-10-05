package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class PersonControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(PersonControllerTest.class);

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void testHello() {
        HttpRequest<String> request = HttpRequest.GET("/person-from-config");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
       // assertEquals("Hello World", body);
    }

   /* @Inject
    @Client("/") RxHttpClient client;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testHelloResponse() {
        final String result = client.toBlocking().retrieve("/hello");
        assertEquals("Hello from Service!", result);
    }

    @Test
    void returnsGermanGreeting() {
        final String result = client.toBlocking().retrieve("/hello/de");
        assertEquals("Hallo", result);
    }

    @Test
    void returnsEnglishGreeting() {
        final String result = client.toBlocking().retrieve("/hello/en");
        assertEquals("Hello", result);
    }

    @Test
    void returnsGreetingAsJson() {
        final ObjectNode result = client.toBlocking().retrieve("/hello/json", ObjectNode.class);
        LOG.debug(result.toString());
    }*/
}
