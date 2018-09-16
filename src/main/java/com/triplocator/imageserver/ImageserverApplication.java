package com.triplocator.imageserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class ImageserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageserverApplication.class, args);
    }
}
