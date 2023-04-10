package com.talla.backmanager.Entites.Budgetaire;

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
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numeroprojet="12345-678";
    private String departement="Gesfin";
    private String etat="En cours d'execution"; // acceptée ou refusée
}
