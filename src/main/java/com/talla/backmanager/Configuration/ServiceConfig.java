package Dette.Configuration;

import Dette.DTO.Mappers.Convert;
import Dette.DTO.Objects.SignaletiqueDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Value("${chemin.dette.inventaire}")
    private String cheminDetteInventaire="";

    public String getcheminDetteInventaire(){
        return cheminDetteInventaire;
    }


    @Bean
    public Convert newConvert(){
        return new Convert();
    }

}
