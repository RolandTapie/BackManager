package com.talla.backmanager.Services.Service.Comptabilite.Budgetaire.Interfaces;


import com.talla.backmanager.Entites.Budgetaire.Article;
import com.talla.backmanager.Exceptions.ArticleNonValideException;
import com.talla.backmanager.Repositories.Budgetaire.EconomiqueRepository;
import com.talla.backmanager.Repositories.Budgetaire.FonctionRepository;

public interface IFServiceArticle {

     Boolean ValidationArticle(Article article) throws ArticleNonValideException;
    Article ConstitutionArticle(String article);
}
