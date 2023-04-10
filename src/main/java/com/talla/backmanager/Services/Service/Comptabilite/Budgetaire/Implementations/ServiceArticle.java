package com.talla.backmanager.Services.Service.Comptabilite.Budgetaire.Implementations;

import com.talla.backmanager.Entites.Budgetaire.Article;
import com.talla.backmanager.Entites.Budgetaire.Economique;
import com.talla.backmanager.Entites.Budgetaire.Exercice;
import com.talla.backmanager.Entites.Budgetaire.Fonction;
import com.talla.backmanager.Exceptions.ArticleNonValideException;
import com.talla.backmanager.Repositories.Budgetaire.EconomiqueRepository;
import com.talla.backmanager.Repositories.Budgetaire.FonctionRepository;
import com.talla.backmanager.Services.Service.Comptabilite.Budgetaire.Interfaces.IFServiceArticle;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


//@AllArgsConstructor
//@NoArgsConstructor
public class ServiceArticle implements IFServiceArticle {
     //FonctionRepository fonctionRepository;
     //EconomiqueRepository economiqueRepository;
    public ServiceArticle(){

    }
    @Override
    public  Boolean ValidationArticle(Article article, FonctionRepository fonctionRepository, EconomiqueRepository economiqueRepository) throws ArticleNonValideException {
        Boolean result=true; //true
        String numfon=article.getFonction().getNumfonction();
        String numeco=article.getEconomique().getEco();

        Fonction fonction = null;
        if (fonctionRepository.findByNumfonction(numfon).iterator().hasNext()) {
            fonction=(fonctionRepository.findByNumfonction(numfon)).iterator().next();
        }
        Economique economique=null;
        if (economiqueRepository.findByEco(numeco).iterator().hasNext()) {
            economique=(economiqueRepository.findByEco(numeco)).iterator().next();
        }
        if (fonction==null || economique==null)
        {
            result = false;
            throw new ArticleNonValideException("la fonction ou l'économique est inexistante");
        }

        return  result;
    }

    @Override
    public Article ConstitutionArticle(String article) {
        Article article1 = new Article();

        String detailArticle[] = article.split("/");
        String fonc= detailArticle[0];
        String eco=detailArticle[1];
        String exe=detailArticle[2];

        Fonction fonction = new Fonction();
        fonction.setNumfonction(fonc);

        Economique economique = new Economique();
        economique.setEco(eco);

        Exercice exercice = new Exercice();
        exercice.setNumeroexercice(exe);


        article1.setFonction(fonction);
        article1.setEconomique(economique);
        article1.setExercice(exercice);

        return article1;
    }
}
