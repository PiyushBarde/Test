package com.bridgelabz.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
@Slf4j
public class UserApplication {
    private static final Logger log = LoggerFactory.getLogger(UserApplication.class);

    public UserApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        log.info("User From Insurance App started");
    }
}