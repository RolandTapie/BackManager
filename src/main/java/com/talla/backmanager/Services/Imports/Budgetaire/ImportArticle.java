package com.talla.backmanager.Services.Imports.Budgetaire;


import com.talla.backmanager.Entites.Budgetaire.*;
import com.talla.backmanager.Repositories.Budgetaire.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@Service
public class ImportArticle {

    private  String SOURCE="C:\\Users\\Liege\\IdeaProjects\\BackManager\\src\\main\\java\\com\\talla\\backmanager\\Zfiles\\articles.txt";
    private final ArticleRepository articleRepository;
    private final  FonctionRepository fonctionRepository;
    private final  EconomiqueRepository economiqueRepository;
    private final  ExerciceRepository exerciceRepository;
    private final ProjetRepository projetRepository;
    private final DepartementRepository departementRepository;

    public ImportArticle(ArticleRepository articleRepository, FonctionRepository fonctionRepository, EconomiqueRepository economiqueRepository, ExerciceRepository exerciceRepository, ProjetRepository projetRepository, DepartementRepository departementRepository) {
        this.articleRepository = articleRepository;
        this.fonctionRepository = fonctionRepository;
        this.economiqueRepository = economiqueRepository;
        this.exerciceRepository = exerciceRepository;
        this.projetRepository = projetRepository;
        this.departementRepository = departementRepository;
    }

    public  String Execution() throws FileNotFoundException {
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
        Scanner scan = new Scanner(new File(SOURCE));
        if (!scan.hasNextLine())
            return "fichier vide";

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

        return "OK";
    }
}
