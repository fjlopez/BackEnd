/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ClickStat;
import io.swagger.model.Error;
import io.swagger.model.Stats;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-12T11:58:42.466Z[GMT]")

@Api(value = "stats", description = "the stats API")
public interface StatsApi {

    @ApiOperation(value = "Get the stats of the shortened URL", nickname = "getGlobalStats", notes = "", response = ClickStat.class, responseContainer = "List", tags={ "stats", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Success", response = ClickStat.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad input parameter", response = Error.class),
        @ApiResponse(code = 404, message = "not found", response = Error.class) })
    @RequestMapping(value = "/stats/global/{sequence}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ClickStat>> getGlobalStats(@ApiParam(value = "Shortened URL sequence code",required=true) @PathVariable("sequence") String sequence);


    @ApiOperation(value = "Get the stats of the shortened URL", nickname = "getStats", notes = "", response = Stats.class, responseContainer = "List", tags={ "stats", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Success", response = Stats.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad input parameter", response = Error.class),
        @ApiResponse(code = 404, message = "not found", response = Error.class) })
    @RequestMapping(value = "/stats/daily/{sequence}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Stats>> getStats(@ApiParam(value = "Shortened URL sequence code",required=true) @PathVariable("sequence") String sequence);

}