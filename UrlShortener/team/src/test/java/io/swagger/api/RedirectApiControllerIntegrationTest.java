package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.RedirectURL;

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
public class RedirectApiControllerIntegrationTest {

    @Autowired
    private RedirectApi api;

    @Test
    public void getShortenedURLTest() throws Exception {
        String sequence = "sequence_example";
        String userAgent = "userAgent_example";
        ResponseEntity<RedirectURL> responseEntity = api.getShortenedURL(sequence, userAgent);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
