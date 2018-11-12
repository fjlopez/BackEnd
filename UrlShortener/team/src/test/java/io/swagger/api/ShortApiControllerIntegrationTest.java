package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.URL;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortApiControllerIntegrationTest {

    @Autowired
    private ShortApi api;

    @Test
    public void postCreateShortenedURLTest() throws Exception {
        String headURL = "headURL_example";
        String intersticialURL = "intersticialURL_example";
        ResponseEntity<URL> responseEntity = api.postCreateShortenedURL(headURL, intersticialURL);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
