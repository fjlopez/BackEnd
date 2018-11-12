package io.swagger.api;

import io.swagger.model.ClickStat;
import io.swagger.model.Error;
import io.swagger.model.Stats;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-12T11:58:42.466Z[GMT]")

@Controller
public class StatsApiController implements StatsApi {

    private static final Logger log = LoggerFactory.getLogger(StatsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public StatsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<ClickStat>> getGlobalStats(@ApiParam(value = "Shortened URL sequence code",required=true) @PathVariable("sequence") String sequence) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<ClickStat>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Stats>> getStats(@ApiParam(value = "Shortened URL sequence code",required=true) @PathVariable("sequence") String sequence) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Stats>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
