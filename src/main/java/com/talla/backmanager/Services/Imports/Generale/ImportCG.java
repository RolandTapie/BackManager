package com.talla.backmanager.Services.Imports.Generale;


import com.talla.backmanager.Entites.Generale.Beans.CG;
import com.talla.backmanager.Repositories.Generale.CGRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class ImportCG {

    public ImportCG(CGRepository cgRepository) {
        this.cgRepository = cgRepository;
    }

    final CGRepository cgRepository;

    public void Execution(String source) throws FileNotFoundException {

        String numeroCg="1700";
         String libelle="Emprunts à plus d'un an";
         String catBilan="Passif";
        String rubrique="VIII.A.I";


        Scanner scan = new Scanner(new File(source+"CG.txt"));


        while (scan.hasNextLine()) {
            String ligne=scan.nextLine();

            String[] champs=ligne.split(";");
            numeroCg = champs[0];
            libelle= champs[1];
            catBilan= champs[2];
            rubrique= champs[3];


            CG cg = new CG();

            cg.setNumeroCg(numeroCg);
            cg.setLibelle(libelle);
            cg.setCatBilan(catBilan);
            cg.setRubrique(rubrique);

            //TODO: lier aux entités JPA

            cgRepository.save(cg);

        }
    }
}
