package com.talla.backmanager.Entites.Budgetaire.Depenses;


import com.talla.backmanager.Entites.Tiers.Beans.Tiers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import jakarta.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class FactureIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dateFacture="19/03/2022";

    //TODO: Vérifier la relation
   @ManyToOne
    private Tiers tiers;

    @OneToMany (mappedBy = "factureIn")
    private List<Engagement> engagement;
    private double montant=366747.56;
    private String libelle="Materiel informatique";
    private String departement="Gesfin";
    private String etat="Encours de validation"; // acceptée ou refusée
    private String source="1234567"; // si la facture est une facture fille

}
