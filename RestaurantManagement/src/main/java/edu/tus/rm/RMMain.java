package edu.tus.rm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication //(scanBasePackages = {"edu.tus.rm"})
public class RMMain {
    public static void main(String[] args) {
        SpringApplication.run(RMMain.class, args);
    }

}
