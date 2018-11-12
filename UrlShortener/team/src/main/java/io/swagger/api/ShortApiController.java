package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.URL;
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
public class ShortApiController implements ShortApi {

    private static final Logger log = LoggerFactory.getLogger(ShortApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ShortApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<URL> postCreateShortenedURL(@NotNull @ApiParam(value = "URL to be shortened", required = true) @Valid @RequestParam(value = "headURL", required = true) String headURL,@ApiParam(value = "Intersticial URL") @Valid @RequestParam(value = "intersticialURL", required = false) String intersticialURL) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<URL>(HttpStatus.NOT_IMPLEMENTED);
    }

}
