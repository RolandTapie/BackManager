package com.talla.backmanager;

import com.talla.backmanager.Configuration.ServiceConfig;
import com.talla.backmanager.Services.Service.LireInventaireDette;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BackManagerApplication implements CommandLineRunner {
    @Autowired
    LireInventaireDette lireInventaireDette;
    @Autowired
    ServiceConfig serviceConfig;
    public static void main(String[] args) {
        SpringApplication.run(BackManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("2-Lecture des Inventaires Dettes");
        log.info(serviceConfig.getcheminDetteInventaire());
//        lireInventaireDette.execution(serviceConfig.getcheminDetteInventaire());
    }

}
