package com.talla.backmanager.Entites.Budgetaire;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Fonction fonction;
    @ManyToOne
    private Economique economique;
    @ManyToOne
    private Projet projet;
    @ManyToOne
    private Exercice exercice;
    @ManyToOne
    private Departement departement;

    private String art="";

    private double allocation=0;


    public static class Builder
    {
            public Builder()
            {

            }

            Article article = new Article();
        private Fonction fonction;
        private Economique economique;
        private Projet projet;
        private Exercice exercice;
        private Departement departement;
        private String autres="";

        Builder WithFonction(Fonction fonction)
        {
            this.fonction=fonction;
            return this;
        }

        Builder WithEconomique(Economique economique)
        {
            this.economique=economique;
            return this;
        }

        Builder WithProjet(Projet projet)
        {
            this.projet=projet;
            return this;
        }

        Builder WithExercice(Exercice exercice)
        {
            this.exercice=exercice;
            return this;
        }

        Builder WithDepartement(Departement departement)
        {
            this.departement = departement;
            return this;
        }
        Article Build()
        {
            article.setFonction(this.fonction);
            article.setDepartement(this.departement);
            article.setEconomique(this.economique);
            article.setProjet(this.projet);
            article.setExercice(this.exercice);
            article.setArt(article.getFonction().getNumfonction() +"/"+article.getEconomique().getEco() + "/" +article.getExercice().getNumeroexercice() +"/" +article.getProjet().getNumeroprojet());
            return article;
        }
    }


}
