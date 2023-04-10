package com.talla.backmanager.Services.Imports.Budgetaire;



import com.talla.backmanager.Entites.Budgetaire.Article;
import com.talla.backmanager.Entites.Budgetaire.Depenses.Engagement;
import com.talla.backmanager.Entites.Budgetaire.Depenses.FactureIn;
import com.talla.backmanager.Repositories.Budgetaire.EngagementRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class ImportEngagement {
  final EngagementRepository engagementRepository;

    public ImportEngagement(EngagementRepository engagementRepository) {
        this.engagementRepository = engagementRepository;
    }

    public  void execution(String source) throws FileNotFoundException {

        FactureIn factureIn;

        Article article;
        String libelle="Engagemende la depense";
         double montantEng=1233444;
         String statutEng="Definitif";
         String college="89";
         double soldeEng=950;

        Scanner scan = new Scanner(new File(source +"Engagements.txt"));


        while (scan.hasNextLine()) {
            String ligne=scan.nextLine();

            String[] champs=ligne.split(";");

            libelle=champs[1];
            montantEng=Double.parseDouble(champs[2].replace(",","."));
            statutEng=champs[3];
            college=champs[4];
            soldeEng=Double.parseDouble(champs[5].replace(",","."));

            Engagement engagement = new Engagement();

            engagement.setLibelle(libelle);
            engagement.setMontantEng(montantEng);
            engagement.setStatutEng(statutEng);
            engagement.setCollege(college);
            engagement.setSoldeEng(soldeEng);

            engagementRepository.save(engagement);

        }
    }
}
