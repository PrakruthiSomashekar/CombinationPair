package src.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import src.main.entity.History;
import src.main.entity.Request;
import src.main.service.CombinationService;

import java.util.List;


@RestController
@RequestMapping(path = "/combination")
public class CombinationController {

    private static final Logger logger = LoggerFactory.getLogger(CombinationController.class);

    @Autowired
    private CombinationService service;


    @GetMapping("/history")
    public List<History> getHistory(){
        logger.info("Getting request history");
        return service.getHistory();
    }

    @PostMapping(value = "/pair")
    public String getCombinationPair(@RequestBody Request request) {

        if(request.getInput() == null) {
            logger.error("Mandatory request parameter input array is null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mandatory request parameter input array is null");
        }

        if(request.getSum() == null) {
            logger.error("Mandatory request parameter sum is null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mandatory request parameter sum is null");
        }

        try {
            return service.generatePair(request.getInput(), request.getSum());

        }catch (Exception e){
            logger.error("Internal server exception, exceptionClass={}, errorMsg={}", e.getClass().getName(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
