package com.hashedin.huSpark.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping(path = "/userhello")
    @PreAuthorize("hasRole('USER')")
    public String helloWorld() {
        logger.info("ENtered user hello world controller");

        return "{\"message\":\"Hello World Java v1\"}";
    }


    @GetMapping(path = "/adminhello")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloWorldbyAdmin() {
        logger.info("Enter ed hello world controller");

        return "{\"message\":\"Hello World Java v1\"}";
    }



    @GetMapping(path = "/helloall")

    public String helloWorldAll() {
        logger.info("Enter ed hello world controller");

        return "{\"message\":\"Hello World Java v1\"}";
    }

}