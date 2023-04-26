package com.talla.backmanager;

import com.talla.backmanager.Configuration.ServiceConfig;
import com.talla.backmanager.Services.Imports.Budgetaire.Imports;
import com.talla.backmanager.Services.Imports.Dette.LireInventaireDette;
import com.talla.backmanager.Services.Imports.FluxFinanciers.Services.LectureCoda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BackManagerApplication implements CommandLineRunner {
    private final LireInventaireDette lireInventaireDette;
    private final Imports imports;
    private final ServiceConfig serviceConfig;

    private final LectureCoda lectureCoda;
    public BackManagerApplication(LireInventaireDette lireInventaireDette, Imports imports, ServiceConfig serviceConfig, LectureCoda lectureCoda) {
        this.lireInventaireDette = lireInventaireDette;
        this.imports = imports;
        this.serviceConfig = serviceConfig;
        this.lectureCoda = lectureCoda;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackManagerApplication.class, args);
    }

    public String test(){
        lireInventaireDette.execution(this.serviceConfig.getcheminDetteInventaire());
        return "OK";
    }
    @Override
    public void run(String... args) throws Exception {
        lectureCoda.setChemin(serviceConfig.getCheminCodaInventaire());

        System.out.println("1-Lecture des Inventaires Dettes");
        log.info(serviceConfig.getcheminDetteInventaire());
        lireInventaireDette.execution(serviceConfig.getcheminDetteInventaire());

        System.out.println("2-Lecture des Coda");
        log.info(serviceConfig.getCheminCodaInventaire());
        lectureCoda.ControleEtImport();
    }

}
