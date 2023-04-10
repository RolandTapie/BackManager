package com.talla.backmanager.Services.Imports.Budgetaire;


import com.talla.backmanager.Entites.Budgetaire.Allocation;
import com.talla.backmanager.Entites.Budgetaire.Article;
import com.talla.backmanager.Exceptions.ArticleNonValideException;
import com.talla.backmanager.Repositories.Budgetaire.AllocationRepository;
import com.talla.backmanager.Repositories.Budgetaire.EconomiqueRepository;
import com.talla.backmanager.Repositories.Budgetaire.FonctionRepository;
import com.talla.backmanager.Services.Service.Comptabilite.Budgetaire.Implementations.ServiceArticle;
import com.talla.backmanager.Services.Service.Comptabilite.Budgetaire.Interfaces.IFServiceArticle;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImportAllocation {

    public static void Execution(String source, AllocationRepository allocationRepository, FonctionRepository fonctionRepository, EconomiqueRepository economiqueRepository) throws FileNotFoundException {

        String article ="";
        double initial=0;
        double solde=0;
        String cahier="";
        String date="";


        Scanner scan = new Scanner(new File(source+"Allocations.txt"));

        int ok=0;
        int nonOK=0;

        while (scan.hasNextLine()) {
            String ligne=scan.nextLine();

            String[] champs=ligne.split(";");
            article = champs[0];
            initial= Double.parseDouble(champs[1].replace(",","."));
            solde= Double.parseDouble(champs[2].replace(",","."));
            cahier= champs[3];
            date= champs[4];


            Allocation allocation= new Allocation();

            //TODO: Valider l'article
            IFServiceArticle serviceArticle = (IFServiceArticle) new ServiceArticle();
            Article article1 = serviceArticle.ConstitutionArticle(article);

            try {
                Boolean test = serviceArticle.ValidationArticle(article1, fonctionRepository, economiqueRepository);
                if (test==true)
                {
                    allocation.setArticle(article);
                    allocation.setInitial(initial);
                    allocation.setSolde(solde);
                    allocation.setCahier(cahier);
                    allocation.setDate(date);

                    //TODO: lier aux entités JPA

                    allocationRepository.save(allocation);
                    ok++;
                    //System.out.println("Article enregistré : "+ article);
                }
            } catch (ArticleNonValideException anve)
            {
                nonOK++;
                //System.out.println(anve.getMessage());
                //System.out.println("Article non valide : "+ article);
            }



        }
        System.out.println("   > "+ ok + " articles conformes et allocations enregistrées");
        System.out.println("   > "+ nonOK + " articles NON conformes ; enregistrements impossibles");
    }
}
