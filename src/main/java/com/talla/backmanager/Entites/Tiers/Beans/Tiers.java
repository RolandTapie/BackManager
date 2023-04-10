package com.talla.backmanager.Entites.Tiers.Beans;


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
public class Tiers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numeroTiers="9377747474747"; //BCE, NN
    private String type="Personne Physique";//PP, PM etc.
    private String nom="TAMBA";
    private String prenom="Jean";
    private String rue="Rue des mineurs";
    private String cp="4000";
    private String Ville="Liege";
    private String pays="Belgique";
    private String adresseMail="tamba.jean@yahoo.fr";
    private String rueFacturation="Rue Feronstree";
    private String cpFacturation="1050";
    private String VilleFacturation="Bruxelles";
    private String paysFacturation="Cameroun";
    private String compte="77";
}
