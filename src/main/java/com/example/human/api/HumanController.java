package com.example.human.api;

import com.example.human.dto.Human;
import com.example.human.services.HumanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping(value = "/v1/humans")
@Api(value = "/v1/humans", description = "Operations on a user")
public class HumanController {


    private static final Logger logger = LoggerFactory.getLogger(HumanController.class);
    private HumanService humanService;


    @Autowired
    public HumanController(HumanService humanService){
        this.humanService=humanService;
    }


    @GetMapping(value = "/health")
    @ApiResponses(value = {  @ApiResponse(code = 200, message = "Success")
    })
    @ApiOperation(value = "get health Check",notes = "health Check api")
    public ResponseEntity healthCheck() {
        try {
            return new ResponseEntity("Human Service Health is OK", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getCause(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization header", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {  @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal server error"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Authorization failed"),
    })
    @ApiOperation(value = "Add new Human",notes = "create new entity")
    public ResponseEntity addHuman(@RequestBody Human human) {
        try {
            humanService.addHuman(human);
            logger.info("Human has been added successfully");
            return new ResponseEntity(human,HttpStatus.CREATED);
        }catch (Exception e){
            logger.error("Failed to add new Human",e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping
    @ApiResponses(value = {  @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal server error"),
        @ApiResponse(code = 404, message = "Not Found"),
    })
    @ApiOperation(value = "Get all Humans",notes = "fetch all humans")
    public ResponseEntity getAllHumans() {
        try {
            List<Human> humans = humanService.getAllHumans();
            if (humans==null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(humans);
        }catch (Exception e){
            logger.error("Failed to find all humans",e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Update Human",notes = "Update Human by ID")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization header", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {  @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal server error"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Authorization failed"),
    })
    public ResponseEntity updatePerson(@PathVariable Long id , @RequestBody Human human) {
        try {
            logger.info("Update Human : "+ human);
            Boolean isUpdated = humanService.updateHuman(human,id);
            if(!isUpdated){
                logger.warn("The human not exists and can not be updated");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            logger.error("Failed to update human",e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @DeleteMapping("/{id}")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization header", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {  @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal server error"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Authorization failed"),
    })
    @ApiOperation(value = "Delete Human",notes = "Delete Human by ID")
    public ResponseEntity deleteHuman(@PathVariable Long id) {
        try {
            logger.info("Delete Human by Id: "+ id);
            Boolean isRemoved = humanService.removeHuman(id);
            if(!isRemoved){
                logger.warn("The human not exists and can not be deleted");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            logger.error("Failed to delete human",e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
