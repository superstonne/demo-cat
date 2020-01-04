package com.example.servicec.servicec;

import com.example.servicec.servicec.context.RestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@EnableHystrix
@EnableCircuitBreaker
@SpringBootApplication
public class ServiceCApplication {

    @Autowired
    private RestInterceptor restInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(ServiceCApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(restInterceptor));
        return new RestTemplate();
    }
}
