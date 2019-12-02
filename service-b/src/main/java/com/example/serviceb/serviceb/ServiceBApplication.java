package com.example.serviceb.serviceb;

import com.example.serviceb.serviceb.context.RestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class ServiceBApplication {


    public static void main(String[] args) {
        SpringApplication.run(ServiceBApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(new RestInterceptor()));
        return restTemplate;
    }

}
