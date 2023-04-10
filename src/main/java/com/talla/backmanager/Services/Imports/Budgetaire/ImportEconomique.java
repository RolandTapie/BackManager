package com.talla.backmanager.Services.Imports.Budgetaire;


import com.talla.backmanager.Entites.Budgetaire.Economique;
import com.talla.backmanager.Repositories.Budgetaire.EconomiqueRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class ImportEconomique {
    final EconomiqueRepository economiqueRepository;
    public ImportEconomique(EconomiqueRepository economiqueRepository) {
        this.economiqueRepository = economiqueRepository;
    }



    public void Execution(String source) throws FileNotFoundException {

        String numeco ="";
        String libelle="";
        String groupe="";
        String type="";
        String service="";
        String cgd="";
        String cgc="";

        Scanner scan = new Scanner(new File(source + "Economiques.txt"));


        while (scan.hasNextLine()) {
            String ligne=scan.nextLine();

            String[] champs=ligne.split(";");
            numeco = champs[0];
            libelle= champs[1];
            groupe= champs[2];
            type= champs[4];
            service= champs[5];
            cgd= champs[6];
            cgc= champs[7];

            Economique economique = new Economique();
            economique.setEco(numeco);
            economique.setLibelle(libelle);
            economique.setGroupe(groupe);
            economique.setType(type);
            economique.setService(service);

            //TODO: lier aux entités JPA
            economique.setCgd(cgd);
            economique.setCgc(cgc);

            economiqueRepository.save(economique);

        }
    }
}
