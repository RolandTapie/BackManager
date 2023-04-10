package com.talla.backmanager.Repositories.Budgetaire;

import com.talla.backmanager.Entites.Budgetaire.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
    public Article findByArt(String article);
}
