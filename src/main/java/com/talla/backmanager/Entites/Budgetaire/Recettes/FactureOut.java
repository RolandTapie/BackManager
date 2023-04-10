package com.talla.backmanager.Entites.Budgetaire.Recettes;

import com.talla.backmanager.Entites.Budgetaire.Article;
import com.talla.backmanager.Entites.Tiers.Beans.Tiers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class FactureOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dateFacture="19/03/2022";

    @ManyToOne
    private Tiers tiers;

    //TODO: Vérifier la relation
    @ManyToOne
    private Article article;
    private double montant=7520;
    private String libelle="Prestations: Amenagement des combles";
    private String departement="Gesfin";
    private String etat="Droit à recette"; // comptabilisée ou pas
    private String source="P000001234"; // si la facture est une facture fille
    private String expedition="Attendre arrêt du rôle";
}
