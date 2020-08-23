package com.example.securecall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value="/get/{id}")
    public String handleGetRequest(@PathVariable("id") String id) {

       // final String uri = "http://...";
        //String result = restTemplate.getForObject(uri, String.class);
        //return result;
        return "123";
    }
}
