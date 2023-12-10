package com.example.firstsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FirstSbApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSbApplication.class, args);

        System.out.println("sb,start!");
    }

}
