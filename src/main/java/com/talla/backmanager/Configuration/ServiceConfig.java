package com.talla.backmanager.Configuration;


import com.talla.backmanager.DTO.Mappers.Convert;
import com.talla.backmanager.Services.Imports.Dette.LireInventaireDette;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ServiceConfig {
    @Value("${chemin.dette.inventaire}")
    private  String cheminDetteInventaire="";

    public  String getcheminDetteInventaire(){
        return cheminDetteInventaire;
    }

    @Bean
    public Convert newConvert(){
        return new Convert();
    }


}
