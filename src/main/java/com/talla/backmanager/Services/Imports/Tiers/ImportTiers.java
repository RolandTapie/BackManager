package com.talla.backmanager.Services.Imports.Tiers;



import com.talla.backmanager.Entites.Tiers.Beans.Tiers;
import com.talla.backmanager.Repositories.Tiers.TiersRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class ImportTiers {
    final TiersRepository tiersRepository ;
    public ImportTiers(TiersRepository tiersRepository) {
        this.tiersRepository = tiersRepository;
    }



    public void execution(String source) throws FileNotFoundException {

         String numeroTiers="9377747474747"; //BCE, NN
         String type="Personne Physique";//PP, PM etc.
         String nom="TAMBA";
         String prenom="Jean";
         String rue="Rue des mineurs";
         String cp="4000";
         String Ville="Liege";
         String pays="Belgique";
         String adresseMail="tamba.jean@yahoo.fr";
         String rueFacturation="Rue Feronstree";
         String cpFacturation="1050";
         String VilleFacturation="Bruxelles";
         String paysFacturation="Cameroun";
         String compte="77";


        Scanner scan = new Scanner(new File(source+"Tiers.txt"));

        int i =0;
        while (scan.hasNextLine()) {
            String ligne=scan.nextLine();
            i++;

            String[] champs=ligne.split(";");

            if (champs.length>=14)
            {
                numeroTiers=champs[0];
                type=champs[1];
                nom=champs[2];
                prenom=champs[3];
                rue=champs[4];
                cp=champs[5];
                Ville=champs[6];
                pays=champs[7];
                adresseMail=champs[8];
                rueFacturation=champs[9];
                cpFacturation=champs[10];
                VilleFacturation=champs[11];
                paysFacturation=champs[12];
                compte=champs[13];


                Tiers tiers = new Tiers();
                tiers.setNumeroTiers(numeroTiers);
                tiers.setType(type);
                tiers.setNom(nom);
                tiers.setPrenom(prenom);
                tiers.setRue(rue);
                tiers.setCp(cp);
                tiers.setVille(Ville);
                tiers.setPays(pays);
                tiers.setAdresseMail(adresseMail);
                tiers.setRueFacturation(rueFacturation);
                tiers.setCpFacturation(cpFacturation);
                tiers.setVilleFacturation(VilleFacturation);
                tiers.setPaysFacturation(paysFacturation);
                tiers.setCompte(compte);

                //TODO: lier aux entités JPA

                tiersRepository.save(tiers);
            }
            else
            {
                //System.out.println(i + " : taille du tableau inférieur à 14");
            }


        }
    }
}
