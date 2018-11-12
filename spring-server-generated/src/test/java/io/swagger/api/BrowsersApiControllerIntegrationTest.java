package io.swagger.api;

import io.swagger.model.Browser;

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
public class BrowsersApiControllerIntegrationTest {

    @Autowired
    private BrowsersApi api;

    @Test
    public void getSupportedBrowsersTest() throws Exception {
        ResponseEntity<List<Browser>> responseEntity = api.getSupportedBrowsers();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
