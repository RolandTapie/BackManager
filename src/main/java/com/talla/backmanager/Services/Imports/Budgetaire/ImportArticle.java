package com.talla.backmanager.Services.Imports.Budgetaire;


import com.talla.backmanager.Entites.Budgetaire.*;
import com.talla.backmanager.Repositories.Budgetaire.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ImportArticle {

    public static void Execution(ArticleRepository articleRepository, FonctionRepository fonctionRepository, EconomiqueRepository economiqueRepository, ExerciceRepository exerciceRepository, ProjetRepository projetRepository, DepartementRepository departementRepository) throws FileNotFoundException {
        List<Fonction> fonction;
        List<Economique> economique;
        Exercice exercice;
        Departement departement;
        Projet projet;

        String lfonction ="";
        String leconomique="";
        String lexercice="";
        String ldepartement="";
        String lprojet="";
        String allocation="";
        Scanner scan = new Scanner(new File("ZFiles/articles.txt"));
        String ligne=scan.nextLine();

        while (scan.hasNextLine()) {
        String[] champs=ligne.split(";");
            lfonction = champs[0];
            leconomique= champs[1];
            lexercice= champs[2];
            lprojet = champs[3];
            ldepartement= champs[4];
            allocation= champs[5];

            Article article = new Article();
            fonction = fonctionRepository.findByNumfonction(lfonction);
            //article.setFonction(fonction);
            economique = economiqueRepository.findByEco(leconomique);
            //article.setEconomique(economique);
            exercice = exerciceRepository.findByNumeroexercice(lexercice);
            article.setExercice(exercice);
            projet = projetRepository.findByNumeroprojet(lprojet);
            article.setProjet(projet);
            departement=departementRepository.findByNom(ldepartement);
            article.setDepartement(departement);
            article.setAllocation(Double.parseDouble(allocation));

            articleRepository.save(article);

            ligne=scan.nextLine();
        }
    }
}
