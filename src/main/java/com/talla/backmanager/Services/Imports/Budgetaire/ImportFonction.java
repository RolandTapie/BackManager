package com.talla.backmanager.Services.Imports.Budgetaire;



import com.talla.backmanager.Entites.Budgetaire.Fonction;
import com.talla.backmanager.Repositories.Budgetaire.FonctionRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class ImportFonction {

    final FonctionRepository fonctionRepositoryry ;

    public ImportFonction(FonctionRepository fonctionRepositoryry) {
        this.fonctionRepositoryry = fonctionRepositoryry;
    }

    public void execution( String source ) throws FileNotFoundException {

        String numfonction ="";
        String libelle="";

        Scanner scan = new Scanner(new File(source + "Fonctions.txt"));


        while (scan.hasNextLine()) {
            String ligne=scan.nextLine();

            String[] champs=ligne.split(";");
            numfonction = champs[0];
           libelle= champs[1];

           Fonction fonction = new Fonction();
           fonction.setNumfonction(numfonction);
           fonction.setLibelle(libelle);

           fonctionRepositoryry.save(fonction);

        }
    }
}
