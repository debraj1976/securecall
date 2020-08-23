package com.example.securecall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan("com.example.*")
@SpringBootApplication
public class SecurecallApplication {

	public static void main(String[] args) {

		SpringApplication.run(SecurecallApplication.class, args);

	}

}
