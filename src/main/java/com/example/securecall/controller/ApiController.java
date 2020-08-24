package com.example.securecall.controller;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value="/get/{id}")
    public String handleGetRequest(@PathVariable("id") String id) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        RestTemplate restTemplate = new RestTemplate();
        final String uri = "https://google.com";
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

}
