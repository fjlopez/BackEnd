package io.swagger.api;

import io.swagger.model.ClickStat;
import io.swagger.model.Error;
import io.swagger.model.Stats;

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
public class StatsApiControllerIntegrationTest {

    @Autowired
    private StatsApi api;

    @Test
    public void getGlobalStatsTest() throws Exception {
        String sequence = "sequence_example";
        ResponseEntity<List<ClickStat>> responseEntity = api.getGlobalStats(sequence);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getStatsTest() throws Exception {
        String sequence = "sequence_example";
        ResponseEntity<List<Stats>> responseEntity = api.getStats(sequence);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
